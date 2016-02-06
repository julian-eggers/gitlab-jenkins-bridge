package com.itelg.devops.gjb.service.impl;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.io.IOUtils;
import org.hibernate.validator.constraints.NotBlank;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.ClassPathResource;
import org.springframework.stereotype.Service;

import com.itelg.devops.gjb.domain.JobTemplate;
import com.itelg.devops.gjb.domain.Project;
import com.itelg.devops.gjb.service.JobTemplateService;
import com.itelg.devops.gjb.service.ProjectFileService;

@Service
public class DefaultJobTemplateService implements JobTemplateService
{
	private static final Logger log = LoggerFactory.getLogger(DefaultJobTemplateService.class);
	
	@Autowired
	private ProjectFileService projectFileService;
	
	@NotBlank
	@Value("${jenkins.job.name.template}")
	private String jenkinsJobNameTemplate;
	
	@Override
	public List<JobTemplate> getMatchingJobTemplates(Project project)
	{
		List<JobTemplate> jobTemplates = new ArrayList<>();
		createGitlabJenkinsTemplate(jobTemplates, project);
		
		return jobTemplates;
	}
	
	private void createGitlabJenkinsTemplate(List<JobTemplate> jobTemplates, Project project)
	{
		byte[] file = projectFileService.getProjectFileContent(project, "pom.xml");
		
		if (file != null)
		{
			JobTemplate jobTemplate = new JobTemplate();
			jobTemplate.setJobName(jenkinsJobNameTemplate);
			jobTemplate.setFileName("jenkins_gitlab_template.xml");
			jobTemplate.setContent(loadJobTemplateContent(jobTemplate.getFileName()));
			jobTemplates.add(jobTemplate);
			log.debug("JobTemplate (" + jobTemplate + ") matched with Project (" + project + ")");
		}
	}
	
	private byte[] loadJobTemplateContent(String fileName)
	{
		try
		{
			return IOUtils.toByteArray(new ClassPathResource(fileName).getInputStream());
		}
		catch (IOException e)
		{
			throw new RuntimeException(e);
		}
	}
}