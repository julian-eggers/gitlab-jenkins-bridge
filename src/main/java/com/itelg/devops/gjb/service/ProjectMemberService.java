package com.itelg.devops.gjb.service;

import java.util.List;

import com.itelg.devops.gjb.domain.Project;
import com.itelg.devops.gjb.domain.ProjectMember;

public interface ProjectMemberService
{
	public boolean addJenkinsProjectMemberIfNotExists(Project project);
	
	public void insertProjectMember(ProjectMember projectMember);
	
	public List<ProjectMember> getProjectMembers(Project project);
}