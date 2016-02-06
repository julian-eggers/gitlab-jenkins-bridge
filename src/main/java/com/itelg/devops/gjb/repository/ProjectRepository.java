package com.itelg.devops.gjb.repository;

import java.util.List;

import com.itelg.devops.gjb.domain.Project;

public interface ProjectRepository
{
	List<Project> getAllProjects();
}