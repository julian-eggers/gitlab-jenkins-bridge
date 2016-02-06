package com.itelg.devops.gjb.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.itelg.devops.gjb.domain.Project;
import com.itelg.devops.gjb.repository.ProjectFileRepository;
import com.itelg.devops.gjb.service.ProjectFileService;

@Service
public class DefaultProjectFileService implements ProjectFileService
{
	@Autowired
	private ProjectFileRepository projectFileRepository;

	@Override
	public byte[] getProjectFileContent(Project project, String filePath)
	{
		return projectFileRepository.getProjectFileContent(project, filePath);
	}
}