package com.itelg.devops.gjb.service.impl;

import java.util.List;

import org.hibernate.validator.constraints.NotBlank;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.itelg.devops.gjb.domain.Project;
import com.itelg.devops.gjb.domain.ProjectMember;
import com.itelg.devops.gjb.repository.ProjectMemberRepository;
import com.itelg.devops.gjb.service.ProjectMemberService;

@Service
public class DefaultProjectMemberService implements ProjectMemberService
{
	private static final Logger log = LoggerFactory.getLogger(DefaultProjectMemberService.class);
	
	@Autowired
	private ProjectMemberRepository projectMemberRepository;
	
	@NotBlank
	@Value("${gitlab.jenkins.user.id}")
	private long gitlabJenkinsUserId;
	
	@Override
	public boolean addJenkinsProjectMemberIfNotExists(Project project)
	{
		ProjectMember projectMember = new ProjectMember();
		projectMember.setId(gitlabJenkinsUserId);
		projectMember.setProjectId(project.getId());
		
		if (getProjectMembers(project).contains(projectMember) == false)
		{
			insertProjectMember(projectMember);
			return true;
		}
		
		return false;
	}
	
	@Override
	public void insertProjectMember(ProjectMember projectMember)
	{
		projectMemberRepository.insertProjectMember(projectMember);
		log.info("ProjectMember inserted (" + projectMember + ")");
	}
	
	@Override
	public List<ProjectMember> getProjectMembers(Project project)
	{
		return projectMemberRepository.getProjectMembers(project);
	}
}