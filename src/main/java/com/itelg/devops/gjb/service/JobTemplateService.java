package com.itelg.devops.gjb.service;

import java.util.List;

import com.itelg.devops.gjb.domain.JobTemplate;
import com.itelg.devops.gjb.domain.Project;

public interface JobTemplateService
{
	public List<JobTemplate> getMatchingJobTemplates(Project project);
}