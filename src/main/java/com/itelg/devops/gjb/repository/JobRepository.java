package com.itelg.devops.gjb.repository;

import java.util.List;

import com.itelg.devops.gjb.domain.Job;

public interface JobRepository
{
	public void insertJob(Job job);
	public void updateJob(Job job);
	public void deleteJob(Job job);
	
	public List<Job> getAllJobs();
}