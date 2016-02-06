package com.itelg.devops.gjb.domain;

public class ProjectMember
{
	private long id;
	private String username;
	private long projectId;

	public long getId()
	{
		return id;
	}

	public void setId(long id)
	{
		this.id = id;
	}

	public String getUsername()
	{
		return username;
	}

	public void setUsername(String username)
	{
		this.username = username;
	}

	public long getProjectId()
	{
		return projectId;
	}

	public void setProjectId(long projectId)
	{
		this.projectId = projectId;
	}

	@Override
	public String toString()
	{
		return "ProjectMember [id=" + id + ", username=" + username + ", projectId=" + projectId + "]";
	}
}