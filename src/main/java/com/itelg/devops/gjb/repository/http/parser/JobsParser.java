package com.itelg.devops.gjb.repository.http.parser;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Component;

import com.itelg.devops.gjb.domain.Job;
import com.itelg.xpath.helper.XPathHelper;
import com.itelg.xpath.helper.parser.AbstractParser;

import nu.xom.Element;
import nu.xom.Node;

@Component
public class JobsParser extends AbstractParser<List<Job>>
{
	@Override
	protected List<Job> doParse(Element rootElement) throws Exception
	{
		List<Job> jobs = new ArrayList<>();
		
		for (Node jobNode : XPathHelper.getNodeList("job", rootElement))
		{
			Job job = new Job();
			job.setName(XPathHelper.getString("name", jobNode));
			jobs.add(job);
		}
		
		return jobs;
	}
}