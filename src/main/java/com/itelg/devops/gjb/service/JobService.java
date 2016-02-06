package com.itelg.devops.gjb.service;

import java.util.List;

import com.itelg.devops.gjb.domain.Job;

public interface JobService
{
	public void insertJob(Job job);
	public void updateJob(Job job);
	public void deleteJob(Job job);

	public List<Job> getAllJobs();
}