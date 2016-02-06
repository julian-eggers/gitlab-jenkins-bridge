package com.itelg.devops.gjb.helper;

public class UrlHelper
{
	public static String clearJenkinsUrl(String url)
	{
		return url.replaceAll(" ", "%20");
	}
}