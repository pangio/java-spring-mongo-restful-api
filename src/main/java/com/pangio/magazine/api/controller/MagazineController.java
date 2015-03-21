package com.pangio.magazine.api.controller;

import java.util.List;

import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.pangio.magazine.api.domain.Article;
import com.pangio.magazine.api.domain.Magazine;
import com.pangio.magazine.api.domain.PoliticArticle;
import com.pangio.magazine.api.domain.SportArticle;
import com.pangio.magazine.api.exception.MagazineNotFoundException;
import com.pangio.magazine.api.service.MagazineService;

/**
 * RestController of the {@link Magazine} entity. Restful API that manages all
 * requests under the URI '/magazines' See {@link HttpMethod}
 * 
 * @author pangio
 */
@RestController
@RequestMapping("/magazines")
@ComponentScan(basePackages = "com.pangio.magazine.api")
public class MagazineController {

	@Autowired
	private MagazineService magazineService;

	MagazineController() {
	}

	/**
	 * Creates a new {@link Magazine}.
	 * 
	 * @param the
	 *            JSON representation of the magazine.
	 * @return the created magazine
	 */
	@RequestMapping(method = RequestMethod.POST)
	ResponseEntity<?> create(@RequestBody Magazine magazine) {
		this.magazineService.create(magazine);
		return new ResponseEntity<Magazine>(magazine, HttpStatus.CREATED);
	}

	/**
	 * Retrieves a {@link Magazine}.
	 * 
	 * @param magazineId
	 *            of the requested {@link Magazine}.
	 * @return the magazine.
	 */
	@RequestMapping(value = "/{magazineId}", method = RequestMethod.GET)
	@ResponseBody
	public Magazine get(@PathVariable String magazineId) {
		validateMagazine(magazineId);
		return magazineService.findById(magazineId);
	}

	/**
	 * Updates a {@link Magazine}.
	 * 
	 * @param newMagazine
	 *            - the JSON representation of the magazine.
	 * @param magazineId
	 *            to be updated.
	 * @return the updated magazine.
	 */
	@RequestMapping(value = "/{magazineId}", method = RequestMethod.PUT)
	@ResponseBody
	public Magazine update(@PathVariable String magazineId,
			@RequestBody Magazine newMagazine) {
		validateMagazine(magazineId);
		Magazine magazine = null;
		magazine = magazineService.update(magazineId, newMagazine);
		return magazine;
	}

	/**
	 * Deletes a {@link Magazine}
	 * 
	 * @param magazineId
	 *            to be deleted.
	 */
	@RequestMapping(value = "/{magazineId}", method = RequestMethod.DELETE)
	@ResponseBody
	public void delete(@PathVariable String magazineId,
			HttpServletResponse response) {
		validateMagazine(magazineId);
		magazineService.delete(magazineId);
		response.setStatus(HttpServletResponse.SC_OK);
	}

	/**
	 * Adds the {@link PoliticArticle} to the articleList of the
	 * {@link Magazine}.
	 * 
	 * @param article
	 *            to be added.
	 * @param magazineId
	 *            to be updated.
	 * @return {@link HttpStatus}
	 */
	@RequestMapping(value = "/{magazineId}/politic", method = RequestMethod.POST)
	ResponseEntity<?> addPoliticArticle(@RequestBody PoliticArticle article,
			@PathVariable String magazineId) {
		validateMagazine(magazineId);
		magazineService.addPoliticArticle(article, magazineId);
		return new ResponseEntity<Article>(article, HttpStatus.CREATED);
	}

	/**
	 * Adds the {@link SportArticle} to the articleList of the {@link Magazine}.
	 * 
	 * @param article
	 *            to be added.
	 * @param magazineId
	 *            to be updated.
	 * @return {@link HttpStatus}
	 */
	@RequestMapping(value = "/{magazineId}/sport", method = RequestMethod.POST)
	ResponseEntity<?> addSportArticle(@RequestBody SportArticle article,
			@PathVariable String magazineId) {
		validateMagazine(magazineId);
		magazineService.addSportArticle(article, magazineId);
		return new ResponseEntity<Article>(article, HttpStatus.CREATED);
	}

	/**
	 * Provides the list of {@link Article}s of a {@link Magazine}s.
	 * 
	 * @param magazineId
	 *            .
	 * @return the list of all magazines.
	 */
	@RequestMapping(method = RequestMethod.GET)
	@ResponseBody
	public List<Magazine> list() {
		return magazineService.list();
	}

	// /**
	// * Deletes an {@link Article} from a {@link Magazine}
	// * @param magazineId to be updated.
	// * @param articleId to be deleted.
	// */
	// @RequestMapping(value = "/{magazineId}/articles/{magazineId}", method =
	// RequestMethod.DELETE)
	// @ResponseBody
	// public void delete(@PathVariable String magazineId, HttpServletResponse
	// response) {
	// validateMagazine(magazineId);
	// magazineService.delete(magazineId);
	// response.setStatus(HttpServletResponse.SC_OK);
	// }

	/**
	 * Validates if the provided magazine id is correct. If not a
	 * {@link MagazineNotFoundException} is thrown.
	 * 
	 * @param magazineId
	 *            - magazine id to validate
	 * @throws MagazineNotFoundException
	 */
	private void validateMagazine(String magazineId) {
		Magazine magazine = this.magazineService.findById(magazineId);
		if (magazine == null)
			throw new MagazineNotFoundException(magazineId);
	}
}
