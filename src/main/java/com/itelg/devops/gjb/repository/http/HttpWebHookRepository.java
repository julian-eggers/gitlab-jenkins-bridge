package com.itelg.devops.gjb.repository.http;

import java.util.ArrayList;
import java.util.List;

import org.gitlab.api.models.GitlabProject;
import org.gitlab.api.models.GitlabProjectHook;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;

import com.itelg.devops.gjb.domain.Project;
import com.itelg.devops.gjb.domain.WebHook;
import com.itelg.devops.gjb.repository.WebHookRepository;

@Repository
public class HttpWebHookRepository extends AbstractGitlabHttpRepository implements WebHookRepository
{
	private static final Logger log = LoggerFactory.getLogger(HttpWebHookRepository.class);
	
	@Override
	public void insertWebHook(WebHook webHook)
	{
		try
		{
			gitlabAPI.addProjectHook(Long.valueOf(webHook.getProjectId()), webHook.getUrl(), true, false, true, false);
		}
		catch (Exception e)
		{
			log.error(e.getMessage(), e);
		}
	}

	@Override
	public List<WebHook> getWebHooksByProject(Project project)
	{
		List<WebHook> webHooks = new ArrayList<>();

		try
		{
			GitlabProject gitlabProject = new GitlabProject();
			gitlabProject.setId(toInteger(project.getId()));
			
			for (GitlabProjectHook gitlabProjectHook : gitlabAPI.getProjectHooks(gitlabProject))
			{
				WebHook webHook = new WebHook();
				webHook.setId(Long.parseLong(gitlabProjectHook.getId()));
				webHook.setProjectId(gitlabProjectHook.getProjectId().longValue());
				webHook.setUrl(gitlabProjectHook.getUrl());
				webHooks.add(webHook);
			}
		}
		catch (Exception e)
		{
			log.error(e.getMessage(), e);
		}

		return webHooks;
	}
}