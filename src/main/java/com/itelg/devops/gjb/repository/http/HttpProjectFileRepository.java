package com.itelg.devops.gjb.repository.http;

import java.io.FileNotFoundException;

import org.gitlab.api.models.GitlabProject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;

import com.itelg.devops.gjb.domain.Project;
import com.itelg.devops.gjb.repository.ProjectFileRepository;

@Repository
public class HttpProjectFileRepository extends AbstractGitlabHttpRepository implements ProjectFileRepository
{
	private static final Logger log = LoggerFactory.getLogger(HttpProjectFileRepository.class);
	
	@Override
	public byte[] getProjectFileContent(Project project, String filePath)
	{
		GitlabProject gitlabProject = new GitlabProject();
		gitlabProject.setId(toInteger(project.getId()));
		
		try
		{
			return gitlabAPI.getRawFileContent(gitlabProject, "master", filePath);
		}
		catch (FileNotFoundException e)
		{
			return null;
		}
		catch (Exception e)
		{
			log.error(e.getMessage(), e);
		}
		
		return null;
	}
}