package com.itelg.devops.gjb.domain;

public class Project
{
	private long id;
	private String namespace;
	private String path;

	public long getId()
	{
		return id;
	}

	public void setId(long id)
	{
		this.id = id;
	}
	
	public String getNamespace()
	{
		return namespace;
	}

	public void setNamespace(String namespace)
	{
		this.namespace = namespace;
	}

	public String getPath()
	{
		return path;
	}

	public void setPath(String path)
	{
		this.path = path;
	}

	@Override
	public String toString()
	{
		return "Project [id=" + id + ", namespace=" + namespace + ", path=" + path + "]";
	}
}