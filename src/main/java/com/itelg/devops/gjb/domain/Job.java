package com.itelg.devops.gjb.domain;

public class Job
{
	private String name;
	private Project project;
	private JobTemplate template;

	public String getName()
	{
		return name;
	}

	public void setName(String name)
	{
		this.name = name;
	}

	public Project getProject()
	{
		return project;
	}

	public void setProject(Project project)
	{
		this.project = project;
	}

	public JobTemplate getTemplate()
	{
		return template;
	}

	public void setTemplate(JobTemplate template)
	{
		this.template = template;
	}

	@Override
	public String toString()
	{
		return "Job [name=" + name + ", project=" + project + ", template=" + template + "]";
	}
}