package com.itelg.devops.gjb.service;

import java.util.List;

import com.itelg.devops.gjb.domain.Project;
import com.itelg.devops.gjb.domain.ProjectMember;

public interface ProjectMemberService
{
	boolean addJenkinsProjectMemberIfNotExists(Project project);
	
	void insertProjectMember(ProjectMember projectMember);
	
	List<ProjectMember> getProjectMembers(Project project);
}