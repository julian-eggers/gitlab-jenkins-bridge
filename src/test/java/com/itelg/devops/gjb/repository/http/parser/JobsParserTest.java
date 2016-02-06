package com.itelg.devops.gjb.repository.http.parser;

import java.util.List;

import org.junit.Assert;
import org.junit.Test;
import org.springframework.core.io.ClassPathResource;

import com.itelg.devops.gjb.domain.Job;

public class JobsParserTest
{
	@Test
	public void testParse() throws Exception
	{
		List<Job> jobs = new JobsParser().parse(new ClassPathResource("jobs.xml").getInputStream());
		Assert.assertEquals(2, jobs.size());
		Assert.assertEquals("project1 (Gitlab)", jobs.get(0).getName());
		Assert.assertEquals("project2 (Gitlab)", jobs.get(1).getName());
	}
}