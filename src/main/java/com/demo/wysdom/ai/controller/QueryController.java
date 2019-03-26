package com.demo.wysdom.ai.controller;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.concurrent.CountDownLatch;

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
	private ThreadPoolTaskExecutor taskExecutor;
	
	@RequestMapping(value = "/topics", method = RequestMethod.GET)
	@ResponseBody
	public List<SearchSuggestion> searchWiki(@RequestParam("key") List<String> keys) {
		System.out.println("Input keys : " + keys);
		List<SearchSuggestion> resultset = Collections.synchronizedList(new ArrayList<SearchSuggestion>());
		CountDownLatch latch = new CountDownLatch(keys.size());
		
		for(String key : keys) {
			System.out.println("processing key=" + key + ", pool size=" + taskExecutor.getPoolSize());
			WikiRestAPI wikiRest = new WikiRestAPI(key, resultset, latch);
			taskExecutor.execute(wikiRest);
		}
		
		try {
			System.out.println("wait for all threads to finish ");
			latch.await();
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		System.out.println("return size = " + resultset.size());
		System.out.println("return : " + resultset);
		return resultset;
	}

}
