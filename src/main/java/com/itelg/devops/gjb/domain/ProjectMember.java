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
	public int hashCode() 
	{
		final int prime = 31;
		int result = 1;
		result = prime * result + (int) (id ^ (id >>> 32));
		return result;
	}

	@Override
	public boolean equals(Object obj) 
	{
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		ProjectMember other = (ProjectMember) obj;
		if (id != other.id)
			return false;
		return true;
	}

	@Override
	public String toString()
	{
		return "ProjectMember [id=" + id + ", username=" + username + ", projectId=" + projectId + "]";
	}
}