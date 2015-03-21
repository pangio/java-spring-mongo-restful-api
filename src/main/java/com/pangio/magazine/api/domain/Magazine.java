package com.pangio.magazine.api.domain;

import java.util.ArrayList;
import java.util.List;

import org.springframework.data.annotation.Id;

/**
 * Domain Layer. Represents the POJO of an {@link Magazine}.
 * 
 * @author pangio
 */
public class Magazine {

	@Id
	private String id;
	private String name;
	private List<Article> articles = new ArrayList<Article>();

	/**
	 * {@link Magazine } default constructor
	 */
	public Magazine() {
	}

	/**
	 * Constructor
	 * 
	 * @param name
	 *            of the magazine
	 */
	public Magazine(String name) {
		this.name = name;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public List<Article> getArticles() {
		return articles;
	}

	public void setArticles(List<Article> articles) {
		this.articles = articles;
	}

	public void addArticle(Article article) {
		this.articles.add(article);
	}

}
