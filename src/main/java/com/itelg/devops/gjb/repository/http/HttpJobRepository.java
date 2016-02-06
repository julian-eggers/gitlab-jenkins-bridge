package com.itelg.devops.gjb.repository.http;

import java.nio.charset.Charset;
import java.util.List;
import java.util.concurrent.TimeUnit;

import javax.annotation.PostConstruct;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.config.RequestConfig;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.impl.conn.PoolingHttpClientConnectionManager;
import org.apache.http.util.EntityUtils;
import org.hibernate.validator.constraints.NotBlank;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Repository;
import org.springframework.util.StringUtils;

import com.itelg.devops.gjb.domain.Job;
import com.itelg.devops.gjb.helper.UrlHelper;
import com.itelg.devops.gjb.repository.JobRepository;
import com.itelg.devops.gjb.repository.http.parser.JobsParser;

@Repository
public class HttpJobRepository implements JobRepository
{
	private static final Logger log = LoggerFactory.getLogger(HttpJobRepository.class);
	private static final int MAX_CONNECTION_IDLE_TIME = 20;
	private PoolingHttpClientConnectionManager connectionManager = new PoolingHttpClientConnectionManager();
	private HttpClient httpClient;
	
	@Autowired
	private JobsParser jobsParser;
	
	@NotBlank
	@Value("${jenkins.url}")
	private String jenkinsUrl;

	@PostConstruct
	public void init()
	{
		RequestConfig.Builder requestConfigBuilder = RequestConfig.custom();
		requestConfigBuilder.setSocketTimeout(5000);
		requestConfigBuilder.setConnectTimeout(5000);
		requestConfigBuilder.setConnectionRequestTimeout(5000);
		RequestConfig requestConfig = requestConfigBuilder.build();

		connectionManager.setDefaultMaxPerRoute(30);
		connectionManager.setMaxTotal(30);

		HttpClientBuilder httpClientBuilder = HttpClientBuilder.create();
		httpClientBuilder.setConnectionManager(connectionManager);
		httpClientBuilder.setDefaultRequestConfig(requestConfig);
		httpClient = httpClientBuilder.build();
	}
	
	@Override
	public void insertJob(Job job)
	{
		String jobXml = new String(job.getTemplate().getContent());
		jobXml = StringUtils.replace(jobXml, "$[projectNamespace]", job.getProject().getNamespace());
		jobXml = StringUtils.replace(jobXml, "$[projectPath]", job.getProject().getPath());
		
		HttpEntity entity = null;
		
		try
		{
			String url = jenkinsUrl + "createItem?name=" + job.getName();
			HttpPost httpPost = new HttpPost(UrlHelper.clearJenkinsUrl(url));
			httpPost.setHeader("Content-Type", "application/xml");
			httpPost.setEntity(new StringEntity(jobXml));
			HttpResponse response = httpClient.execute(httpPost);
			entity = response.getEntity();
			
			if (response.getStatusLine().getStatusCode() != 200)
			{
				String content = EntityUtils.toString(entity, Charset.forName("UTF-8"));
				System.err.println(response.getStatusLine().getStatusCode());
				System.err.println(content);
			}
		}
		catch (Exception e)
		{
			log.error(e.getMessage(), e);
		}
		finally
		{
			connectionManager.closeIdleConnections(MAX_CONNECTION_IDLE_TIME, TimeUnit.SECONDS);
			connectionManager.closeExpiredConnections();
			EntityUtils.consumeQuietly(entity);
		}
	}
	
	@Override
	public void updateJob(Job job)
	{
		String jobXml = new String(job.getTemplate().getContent());
		jobXml = StringUtils.replace(jobXml, "$[projectNamespace]", job.getProject().getNamespace());
		jobXml = StringUtils.replace(jobXml, "$[projectPath]", job.getProject().getPath());
		
		HttpEntity entity = null;
		
		try
		{
			String url = jenkinsUrl + "job/" + job.getName() + "/config.xml";
			HttpPost httpPost = new HttpPost(UrlHelper.clearJenkinsUrl(url));
			httpPost.setHeader("Content-Type", "application/xml");
			httpPost.setEntity(new StringEntity(jobXml));
			HttpResponse response = httpClient.execute(httpPost);
			entity = response.getEntity();
			
			if (response.getStatusLine().getStatusCode() != 200)
			{
				String content = EntityUtils.toString(entity, Charset.forName("UTF-8"));
				System.err.println(response.getStatusLine().getStatusCode());
				System.err.println(content);
			}
		}
		catch (Exception e)
		{
			log.error(e.getMessage(), e);
		}
		finally
		{
			connectionManager.closeIdleConnections(MAX_CONNECTION_IDLE_TIME, TimeUnit.SECONDS);
			connectionManager.closeExpiredConnections();
			EntityUtils.consumeQuietly(entity);
		}
	}
	
	@Override
	public void deleteJob(Job job)
	{
		HttpEntity entity = null;
		
		try
		{
			String url = jenkinsUrl + "job/" + job.getName() + "/doDelete";
			HttpPost httpPost = new HttpPost(UrlHelper.clearJenkinsUrl(url));
			httpPost.setHeader("Content-Type", "application/xml");
			HttpResponse response = httpClient.execute(httpPost);
			entity = response.getEntity();
			
			if (response.getStatusLine().getStatusCode() > 404)
			{
				String content = EntityUtils.toString(entity, Charset.forName("UTF-8"));
				System.err.println(response.getStatusLine().getStatusCode());
				System.err.println(content);
			}
		}
		catch (Exception e)
		{
			log.error(e.getMessage(), e);
		}
		finally
		{
			connectionManager.closeIdleConnections(MAX_CONNECTION_IDLE_TIME, TimeUnit.SECONDS);
			connectionManager.closeExpiredConnections();
			EntityUtils.consumeQuietly(entity);
		}		
	}
	
	@Override
	public List<Job> getAllJobs()
	{
		HttpEntity entity = null;
		
		try
		{
			HttpPost httpPost = new HttpPost(jenkinsUrl + "api/xml");
			httpPost.setHeader("Content-Type", "application/xml");
			HttpResponse response = httpClient.execute(httpPost);
			entity = response.getEntity();
			String content = EntityUtils.toString(entity, Charset.forName("UTF-8"));
			return jobsParser.parse(content);
		}
		catch (Exception e)
		{
			log.error(e.getMessage(), e);
		}
		finally
		{
			connectionManager.closeIdleConnections(MAX_CONNECTION_IDLE_TIME, TimeUnit.SECONDS);
			connectionManager.closeExpiredConnections();
			EntityUtils.consumeQuietly(entity);
		}
		
		return null;
	}
}