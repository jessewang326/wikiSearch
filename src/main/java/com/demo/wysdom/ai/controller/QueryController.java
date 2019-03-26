package com.demo.wysdom.ai.controller;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.demo.wysdom.ai.model.SearchSuggestion;

@Controller
public class QueryController {
	@Autowired
	private WikiRestAPI wikiRest;
	
	@Autowired
	private ThreadPoolTaskExecutor taskExecutor;
	
	@RequestMapping(value = "/topics", method = RequestMethod.GET)
	@ResponseBody
	public List<SearchSuggestion> searchWiki(@RequestParam("key") List<String> keys) {
		System.out.println(keys);
		List<SearchSuggestion> resultset = Collections.synchronizedList(new ArrayList<SearchSuggestion>());
		for(String key : keys) {
			resultset.add(wikiRest.openSearch(key));
		}
		
		return resultset;
	}

}
