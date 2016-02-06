package com.itelg.devops.gjb.task;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jmx.export.annotation.ManagedOperation;
import org.springframework.jmx.export.annotation.ManagedResource;
import org.springframework.scheduling.annotation.Async;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import com.itelg.devops.gjb.domain.Job;
import com.itelg.devops.gjb.domain.JobTemplate;
import com.itelg.devops.gjb.domain.Project;
import com.itelg.devops.gjb.service.JobService;
import com.itelg.devops.gjb.service.JobTemplateService;
import com.itelg.devops.gjb.service.ProjectMemberService;
import com.itelg.devops.gjb.service.ProjectService;
import com.itelg.devops.gjb.service.WebHookService;

@Component
@ManagedResource
public class WebHooksCreateTask
{
	private static final Logger log = LoggerFactory.getLogger(WebHooksCreateTask.class);
	
	@Autowired
	private ProjectService projectService;
	
	@Autowired
	private WebHookService webHookService;
	
	@Autowired
	private JobService jobService;
	
	@Autowired
	private ProjectMemberService projectMemberService;
	
	@Autowired
	private JobTemplateService jobTemplateService;

	@Scheduled(fixedDelayString = "${gitlab.task.delay}")
	@ManagedOperation
	@Async
	public void doJob()
	{
		log.info("WebHooksCreateTask started");
		
		for (Project project : projectService.getAllProjects())
		{
			log.debug("Start checking Project (" + project + ")");
			projectMemberService.addJenkinsProjectMemberIfNotExists(project);
			
			for (JobTemplate jobTemplate : jobTemplateService.getMatchingJobTemplates(project))
			{
				Job job = new Job();
				job.setName(jobTemplate.getJobName().replace("$[projectPath]", project.getPath()));
				job.setProject(project);
				job.setTemplate(jobTemplate);
				
				if (webHookService.addWebHookIfNotExists(project, job))
				{
					jobService.insertJob(job);
				}
				else
				{
					jobService.updateJob(job);
				}
			}
			
			log.debug("Finished checking Project (" + project + ")");
		}
		
		log.info("WebHooksCreateTask finished");
	}
}