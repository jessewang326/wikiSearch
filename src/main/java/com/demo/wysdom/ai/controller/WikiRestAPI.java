package com.demo.wysdom.ai.controller;

import java.io.IOException;
import java.text.Normalizer;
import java.util.List;
import java.util.concurrent.CountDownLatch;

import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import com.demo.wysdom.ai.model.SearchSuggestion;
import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.dataformat.xml.XmlMapper;

@Component 
public class WikiRestAPI implements Runnable {

	private final String REST_URL = "https://en.wikipedia.org/w/api.php?";
	private final String SEARCH = "action=opensearch&format=xml&search=";
	private String key;
	private int limit = 3;
	private List<SearchSuggestion> resultset;
	private CountDownLatch latch;

	public WikiRestAPI() {
		System.out.println("default WikiRestAPI constructor");
	}
	
	public WikiRestAPI(String key, List<SearchSuggestion> resultset, CountDownLatch latch) {
		System.out.println("initializing WikiRestAPI : key=" + key + " | latch=" + latch.getCount());
		this.key = key;
		this.resultset = resultset;
		this.latch = latch;
	}
	
	/**
	 * search relative wiki page using keywords and wikipedia open api
	 * @param keyword keyword for query
	 * @param num 	  limit of returned result
	 * @param latch   num of querying thread left in the same requests
	 */
	public void openSearch(String keyword, int num, CountDownLatch latch) {
		
		//normalize the keyword
		String input = normalizeQuery(keyword);
		
		//run the query
		String uri = REST_URL + SEARCH + input + "&limit=" + num;
		RestTemplate restTemplate = new RestTemplate();
		
		//parse the result
		resultset.add(parse(restTemplate.getForObject(uri, String.class)));
		
		//count down the latch to let the controller know this thread is completed
		latch.countDown();
		System.out.println("Search Done : key=" + key + " | latch=" + latch.getCount());
	}
	
	/**
	 * normalize the query keyword
	 * @param query
	 * @return
	 */
	public String normalizeQuery(String query) {
		String convertedString = 
				Normalizer.normalize(query, Normalizer.Form.NFD)
				.replaceAll("[^\\p{ASCII}]", "").trim().toLowerCase();
		return convertedString;
	}

	
	/**
	 * parse the returned xml result into modle object
	 * @param result
	 * @return
	 */
	public SearchSuggestion parse(String result) {

		SearchSuggestion xmlObj = null;

		XmlMapper mapper = new XmlMapper();
		try {
			xmlObj = mapper.readValue(result, SearchSuggestion.class);
		} catch (JsonParseException e) {
			e.printStackTrace();
		} catch (JsonMappingException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
//		if(xmlObj != null) {System.out.println(xmlObj);}
		return xmlObj;
	}
	
	@Override
	public void run() {
		openSearch(this.key, this.limit, this.latch);
	}
}
