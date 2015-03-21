package com.pangio.magazine.api.domain;

/**
 * Domain Layer. Represents the POJO of an {@link Article}.
 * 
 * @author pangio
 */
public class Article {

	private String title;
	private String content;

	/**
	 * {@link Article } default constructor
	 */
	public Article() {
	}

	/**
	 * Constructor
	 * 
	 * @param title
	 *            of the article
	 * @param content
	 *            of the article
	 */
	public Article(String title, String content) {
		this.title = title;
		this.content = content;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

}
