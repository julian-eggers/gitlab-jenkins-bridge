package com.itelg.devops.gjb.helper;

import org.junit.Assert;
import org.junit.Test;

public class UrlHelperTest
{
	@Test
	public void testClearJenkinsUrl()
	{
		String actual = UrlHelper.clearJenkinsUrl("http://jenkins.com/project/test Clear JenkinsUrl");
		String expected = "http://jenkins.com/project/test%20Clear%20JenkinsUrl";
		Assert.assertEquals(expected, actual);
	}
}