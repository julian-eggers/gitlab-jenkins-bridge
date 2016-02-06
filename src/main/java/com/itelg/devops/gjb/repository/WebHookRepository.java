package com.itelg.devops.gjb.repository;

import java.util.List;

import com.itelg.devops.gjb.domain.Project;
import com.itelg.devops.gjb.domain.WebHook;

public interface WebHookRepository
{
	public void insertWebHook(WebHook webHook);
	
	public List<WebHook> getWebHooksByProject(Project project);
}