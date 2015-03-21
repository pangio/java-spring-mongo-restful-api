package com.pangio.magazine.api;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.IntStream;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories;

import com.pangio.magazine.api.domain.Article;
import com.pangio.magazine.api.domain.Magazine;
import com.pangio.magazine.api.domain.PoliticArticle;
import com.pangio.magazine.api.domain.SportArticle;
import com.pangio.magazine.api.enums.ArticleType;
import com.pangio.magazine.api.factory.AbstractArticleFactory;
import com.pangio.magazine.api.repository.MagazineRepository;

/**
 * Main class. Performs the setup of the Spring Boot application. Also creates
 * sample data.
 * 
 * @author pangio
 */
@Configuration
@EnableAutoConfiguration
@ComponentScan
@EnableMongoRepositories(basePackages = "com.pangio.magazine.api.repository")
public class Application {

	public static void main(String[] args) {

		List<Article> articlesList = new ArrayList<Article>();
		PoliticArticle politicArticle = (PoliticArticle) AbstractArticleFactory.getFactory(
				ArticleType.POLITIC).createArticle();
		SportArticle sportArticle = (SportArticle) AbstractArticleFactory.getFactory(
				ArticleType.SPORT).createArticle();

		ConfigurableApplicationContext context = SpringApplication
				.run(Application.class);
		MagazineRepository magazineRepository = context
				.getBean(com.pangio.magazine.api.repository.MagazineRepository.class);

		// create MAGAZINES
		magazineRepository.save(new Magazine("Times"));
		magazineRepository.save(new Magazine("Western Times"));
		magazineRepository.save(new Magazine("ESPN Magazine"));

		// Times
		articlesList.clear();
		Magazine magazine = magazineRepository.findByName("Times");
		IntStream.rangeClosed(1, 2).forEach(idx -> {
			politicArticle.setTitle("Politic Title " + idx);
			politicArticle.setContent("Politic Content " + idx);
			politicArticle.setIntroduction("introduction" + idx);
			politicArticle.setEnding("outro" + idx);
			articlesList.add(politicArticle);
		});
		magazine.setArticles(articlesList);
		magazineRepository.save(magazine);

		// Western Times
		articlesList.clear();
		magazine = magazineRepository
				.findByName("Western Times");
		IntStream.rangeClosed(3, 4).forEach(idx -> {
			politicArticle.setTitle("Politic Title " + idx);
			politicArticle.setContent("Politic Content " + idx);
			politicArticle.setIntroduction("intro " + idx);
			politicArticle.setEnding("outro " + idx);
			articlesList.add(politicArticle);
		});
		magazine.setArticles(articlesList);
		magazineRepository.save(magazine);

		// ESPN Magazine
		articlesList.clear();
		final Magazine magazineEspn = magazineRepository
				.findByName("ESPN Magazine");
		IntStream.rangeClosed(5, 6).forEach(idx -> {
			sportArticle.setTitle("Sport Title " + idx);
			sportArticle.setContent("Sport Content " + idx);
			sportArticle.setSubTitle("some subtitle" + idx);
			sportArticle.setThumbnailsPath("some path...");
			articlesList.add(sportArticle);
		});
		magazineEspn.setArticles(articlesList);
		magazineRepository.save(magazineEspn);

	}
}
