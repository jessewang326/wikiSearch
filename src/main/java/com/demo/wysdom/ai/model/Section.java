package com.demo.wysdom.ai.model;

import java.util.ArrayList;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlElementWrapper;

@JsonIgnoreProperties(ignoreUnknown = true)
public class Section {
	@JacksonXmlElementWrapper(localName = "Item", useWrapping = true)
	private List<Item> items;

	public Section() {

	}

	public Section(List<Item> items) {
		this.items = items;
	}

	public List<Item> getItems() {
		return items;
	}

	public void setItems(Item item) {
		if (this.items == null) {
			this.items = new ArrayList<Item>(3);
		}
		this.items.add(item);
	}

	@Override
	public String toString() {
		return "Section [items=" + items + "]";
	}

}
