package com.itelg.devops.gjb.repository;

import java.util.List;

import com.itelg.devops.gjb.domain.Project;
import com.itelg.devops.gjb.domain.ProjectMember;

public interface ProjectMemberRepository
{
	void insertProjectMember(ProjectMember projectMember);
	
	List<ProjectMember> getProjectMembers(Project project);
}