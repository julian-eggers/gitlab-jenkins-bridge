package com.itelg.devops.gjb.service.impl;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jmx.export.annotation.ManagedOperation;
import org.springframework.jmx.export.annotation.ManagedResource;
import org.springframework.stereotype.Service;

import com.itelg.devops.gjb.domain.Job;
import com.itelg.devops.gjb.service.JobService;
import com.itelg.devops.gjb.service.ManagementService;

@Service
@ManagedResource
public class DefaultManagementService implements ManagementService
{
	private static final Logger log = LoggerFactory.getLogger(DefaultManagementService.class);
	
	@Autowired
	private JobService jobService;
	
	@Override
	public void updateAllJobs()
	{
		log.info("Start updating all jobs");
		
		for (Job job : jobService.getAllJobs())
		{
			// TODO implement
		}
		
		log.info("Finished updating all jobs");
	}
	
	@Override
	@ManagedOperation
	public void deleteAllJobs()
	{
		log.info("Start deleting all jobs");
		
		for (Job job : jobService.getAllJobs())
		{
			jobService.deleteJob(job);
		}
		
		log.info("Finished deleting all jobs");
	}
}