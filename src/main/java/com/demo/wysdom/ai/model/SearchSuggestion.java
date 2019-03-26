package com.demo.wysdom.ai.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlElementWrapper;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlProperty;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlRootElement;

@JsonIgnoreProperties(ignoreUnknown = true)
@JacksonXmlRootElement(localName = "SearchSuggestion")
public class SearchSuggestion {
	@JacksonXmlElementWrapper(localName = "Section", useWrapping = true)
	private Section sections;
	@JacksonXmlProperty(localName = "Query")
	private String query;

	public SearchSuggestion() {

	}

	public SearchSuggestion(Section sections, String query) {
		this.sections = sections;
		this.query = query;
	}

	public Section getSections() {
		return sections;
	}

	public void setSections(Section sections) {
		this.sections = sections;
	}

	public String getQuery() {
		return query;
	}

	public void setQuery(String query) {
		this.query = query;
	}

	@Override
	public String toString() {
		return "SearchSuggestion [sections=" + sections.toString() + ", query=" + query + "]";
	}
}
