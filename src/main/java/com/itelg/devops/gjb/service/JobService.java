package com.itelg.devops.gjb.service;

import java.util.List;

import com.itelg.devops.gjb.domain.Job;

public interface JobService
{
	void insertJob(Job job);
	void updateJob(Job job);
	void deleteJob(Job job);

	List<Job> getAllJobs();
}