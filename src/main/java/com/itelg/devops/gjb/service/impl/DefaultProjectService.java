package com.itelg.devops.gjb.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.itelg.devops.gjb.domain.Project;
import com.itelg.devops.gjb.repository.ProjectRepository;
import com.itelg.devops.gjb.service.ProjectService;

@Service
public class DefaultProjectService implements ProjectService
{
	@Autowired
	private ProjectRepository projectRepository;

	@Override
	public List<Project> getAllProjects()
	{
		return projectRepository.getAllProjects();
	}
}