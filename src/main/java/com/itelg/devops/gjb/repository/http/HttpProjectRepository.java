package com.itelg.devops.gjb.repository.http;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.gitlab.api.models.GitlabProject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;

import com.itelg.devops.gjb.domain.Project;
import com.itelg.devops.gjb.repository.ProjectRepository;

@Repository
public class HttpProjectRepository extends AbstractGitlabHttpRepository implements ProjectRepository
{
	private static final Logger log = LoggerFactory.getLogger(HttpProjectRepository.class);
	
	@Override
	public List<Project> getAllProjects()
	{
		List<Project> projects = new ArrayList<>();
		
		try
		{
			for (GitlabProject gitlabProject : gitlabAPI.getAllProjects())
			{
				Project project = new Project();
				project.setId(gitlabProject.getId().longValue());
				project.setNamespace(gitlabProject.getNamespace().getName());
				project.setPath(gitlabProject.getPath());
				projects.add(project);
			}
		}
		catch (IOException e)
		{
			log.error(e.getMessage(), e);
		}
		
		return projects;
	}
}