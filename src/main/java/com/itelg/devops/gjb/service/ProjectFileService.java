package com.itelg.devops.gjb.service;

import com.itelg.devops.gjb.domain.Project;

public interface ProjectFileService
{
	public byte[] getProjectFileContent(Project project, String filePath);
}