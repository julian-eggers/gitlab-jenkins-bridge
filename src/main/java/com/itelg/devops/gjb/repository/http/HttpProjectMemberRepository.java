package com.itelg.devops.gjb.repository.http;

import java.util.ArrayList;
import java.util.List;

import org.gitlab.api.models.GitlabAccessLevel;
import org.gitlab.api.models.GitlabProjectMember;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;

import com.itelg.devops.gjb.domain.Project;
import com.itelg.devops.gjb.domain.ProjectMember;
import com.itelg.devops.gjb.repository.ProjectMemberRepository;

@Repository
public class HttpProjectMemberRepository extends AbstractGitlabHttpRepository implements ProjectMemberRepository
{
	private static final Logger log = LoggerFactory.getLogger(HttpProjectMemberRepository.class);

	@Override
	public void insertProjectMember(ProjectMember projectMember)
	{
		try
		{
			gitlabAPI.addProjectMember(toInteger(projectMember.getProjectId()), toInteger(projectMember.getId()), GitlabAccessLevel.Developer);
		}
		catch (Exception e)
		{
			log.error(e.getMessage(), e);
		}
	}
	
	@Override
	public List<ProjectMember> getProjectMembers(Project project)
	{
		List<ProjectMember> projectMembers = new ArrayList<>();
		
		try 
		{
			for (GitlabProjectMember gitlabProjectMember : gitlabAPI.getProjectMembers(toInteger(project.getId())))
			{
				ProjectMember projectMember = new ProjectMember();
				projectMember.setId(gitlabProjectMember.getId().longValue());
				projectMember.setProjectId(project.getId());
				projectMember.setUsername(gitlabProjectMember.getUsername());
				projectMembers.add(projectMember);
			}
		}
		catch (Exception e)
		{
			log.error(e.getMessage(), e);
		}
		
		return projectMembers;
	}
}