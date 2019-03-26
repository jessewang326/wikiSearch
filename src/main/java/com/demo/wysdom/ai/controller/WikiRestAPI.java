package com.demo.wysdom.ai.controller;

import java.io.IOException;
import java.text.Normalizer;

import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import com.demo.wysdom.ai.model.SearchSuggestion;
import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.dataformat.xml.XmlMapper;

@Component 
public class WikiRestAPI extends Thread {

	private final String REST_URL = "https://en.wikipedia.org/w/api.php?";
	private final String SEARCH = "action=opensearch&format=xml&search=";

	public WikiRestAPI() {
		System.out.println("initializing wikirestAPI");
	}
	
	public SearchSuggestion openSearch(String keyword) {
		return openSearch(keyword, 3);
	}

	public SearchSuggestion openSearch(String keyword, int num) {
		String input = normalizeQuery(keyword);
		String uri = REST_URL + SEARCH + input + "&limit=" + num;
		RestTemplate restTemplate = new RestTemplate();
		return parse(restTemplate.getForObject(uri, String.class));
		
	}
	
	public String normalizeQuery(String query) {
		String convertedString = 
				Normalizer.normalize(query, Normalizer.Form.NFD)
				.replaceAll("[^\\p{ASCII}]", "").trim().toLowerCase();
		System.out.println("----clean : " + convertedString);
		return convertedString;
	}

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

		if(xmlObj != null) {
			System.out.println("******");
			System.out.println(xmlObj);
		}
		return xmlObj;
	}
}
