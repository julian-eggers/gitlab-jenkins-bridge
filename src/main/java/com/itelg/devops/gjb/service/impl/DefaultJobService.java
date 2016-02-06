package com.itelg.devops.gjb.service.impl;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.itelg.devops.gjb.domain.Job;
import com.itelg.devops.gjb.repository.JobRepository;
import com.itelg.devops.gjb.service.JobService;

@Service
public class DefaultJobService implements JobService
{
	private static final Logger log = LoggerFactory.getLogger(DefaultJobService.class);
	
	@Autowired
	private JobRepository jobRepository;
	
	@Override
	public void insertJob(Job job)
	{
		jobRepository.insertJob(job);
		log.info("Job inserted (" + job + ")");
	}
	
	@Override
	public void updateJob(Job job)
	{
		jobRepository.updateJob(job);
		log.info("Job updated (" + job + ")");
	}

	@Override
	public void deleteJob(Job job)
	{
		jobRepository.deleteJob(job);
		log.info("Job deleted (" + job + ")");
	}

	@Override
	public List<Job> getAllJobs()
	{
		return jobRepository.getAllJobs();
	}
}