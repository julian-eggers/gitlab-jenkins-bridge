package com.itelg.devops.gjb.repository;

import com.itelg.devops.gjb.domain.Project;

public interface ProjectFileRepository
{
	byte[] getProjectFileContent(Project project, String filePath);
}