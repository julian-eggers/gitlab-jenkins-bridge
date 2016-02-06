package com.itelg.devops.gjb.domain;

public class WebHook
{
	private long id;
	private long projectId;
	private String url;

	public long getId()
	{
		return id;
	}

	public void setId(long id)
	{
		this.id = id;
	}

	public long getProjectId()
	{
		return projectId;
	}

	public void setProjectId(long projectId)
	{
		this.projectId = projectId;
	}

	public String getUrl()
	{
		return url;
	}

	public void setUrl(String url)
	{
		this.url = url;
	}

	@Override
	public String toString()
	{
		return "WebHook [id=" + id + ", projectId=" + projectId + ", url=" + url + "]";
	}
}