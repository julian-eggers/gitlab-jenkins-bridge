package com.itelg.devops.gjb.service;

import java.util.List;

import com.itelg.devops.gjb.domain.Job;
import com.itelg.devops.gjb.domain.Project;
import com.itelg.devops.gjb.domain.WebHook;

public interface WebHookService
{
	public boolean addWebHookIfNotExists(Project project, Job job);
	
	public void insertWebHook(WebHook webHook);
	
	public List<WebHook> getWebHooksByProject(Project project);
}