package com.itelg.devops.gjb.service.impl;

import java.util.List;

import org.hibernate.validator.constraints.NotBlank;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.itelg.devops.gjb.domain.Job;
import com.itelg.devops.gjb.domain.Project;
import com.itelg.devops.gjb.domain.WebHook;
import com.itelg.devops.gjb.helper.UrlHelper;
import com.itelg.devops.gjb.repository.WebHookRepository;
import com.itelg.devops.gjb.service.WebHookService;

@Service
public class DefaultWebHookService implements WebHookService
{
	private static final Logger log = LoggerFactory.getLogger(DefaultWebHookService.class);
	
	@Autowired
	private WebHookRepository webHookRepository;

	@NotBlank
	@Value("${jenkins.url}")
	private String jenkinsUrl;
	
	@Override
	public boolean addWebHookIfNotExists(Project project, Job job)
	{
		String jenkinsProjectUrl = UrlHelper.clearJenkinsUrl(jenkinsUrl + "project/" + job.getName());
		
		for (WebHook webHook : getWebHooksByProject(project))
		{
			if (webHook.getUrl().equals(jenkinsProjectUrl))
			{
				log.debug("WebHook already exists (" + webHook + ")");
				return false;
			}
		}
		
		WebHook webHook = new WebHook();
		webHook.setUrl(jenkinsProjectUrl);
		webHook.setProjectId(project.getId());
		insertWebHook(webHook);
		
		return true;
	}
	
	@Override
	public void insertWebHook(WebHook webHook)
	{
		webHookRepository.insertWebHook(webHook);
		log.info("WebHook inserted (" + webHook + ")");
	}
	
	@Override
	public List<WebHook> getWebHooksByProject(Project project)
	{
		return webHookRepository.getWebHooksByProject(project);
	}
}