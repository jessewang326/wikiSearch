package com.demo.wysdom.ai.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlProperty;

@JsonIgnoreProperties(ignoreUnknown = true)
public class Item {
	@JacksonXmlProperty(localName = "Text", isAttribute = true)
	private String text;
	@JacksonXmlProperty(localName = "Url")
	private String url;
	@JacksonXmlProperty(localName = "Description")
	private String des;
	@JacksonXmlProperty(localName = "Image")
	private String image;

	public Item() {

	}

	public Item(String text, String url, String des, String image) {
		this.text = text;
		this.url = url;
		this.des = des;
		this.image = image;
	}

	public String getText() {
		return text;
	}

	public void setText(String text) {
		this.text = text;
	}

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public String getDes() {
		return des;
	}

	public void setDes(String des) {
		this.des = des;
	}

	public String getImage() {
		return image;
	}

	public void setImage(String image) {
		this.image = image;
	}

	@Override
	public String toString() {
		return "Item [text=" + text + ", url=" + url + ", des=" + des + ", image=" + image + "]";
	}

}
