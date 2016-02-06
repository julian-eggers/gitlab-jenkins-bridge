package com.itelg.devops.gjb.domain;

public class JobTemplate
{
	private String jobName;
	private String fileName;
	private byte[] content;

	public String getJobName()
	{
		return jobName;
	}

	public void setJobName(String jobName)
	{
		this.jobName = jobName;
	}

	public String getFileName()
	{
		return fileName;
	}

	public void setFileName(String fileName)
	{
		this.fileName = fileName;
	}

	public byte[] getContent()
	{
		return content;
	}

	public void setContent(byte[] content)
	{
		this.content = content;
	}

	@Override
	public String toString()
	{
		return "JobTemplate [jobName=" + jobName + ", fileName=" + fileName + "]";
	}
}