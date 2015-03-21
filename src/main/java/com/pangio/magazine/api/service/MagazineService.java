package com.pangio.magazine.api.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.pangio.magazine.api.domain.Magazine;
import com.pangio.magazine.api.domain.PoliticArticle;
import com.pangio.magazine.api.domain.SportArticle;
import com.pangio.magazine.api.repository.MagazineRepository;

/**
 * Service Layer. {@link MagazineService} provides an interface to do CRUD
 * operations of {@link Magazine}s
 * 
 * @author pangio
 */
@Component
public class MagazineService {

	public MagazineService() {
	}

	public MagazineService(MagazineRepository magazineRepository) {
		this.magazineRepository = magazineRepository;
	}

	@Autowired
	MagazineRepository magazineRepository;

	/**
	 * Creates a new {@link Magazine}.
	 * 
	 * @param magazine
	 *            - a JSON representation of a magazine
	 */
	public void create(Magazine magazine) {
		this.magazineRepository.save(magazine);
		return;
	}

	/**
	 * Finds a {@link Magazine} by id
	 * 
	 * @param id
	 *            of the requested Magazine
	 * @return the magazine
	 */
	public Magazine findById(String id) {
		Magazine magazine = null;
		magazine = this.magazineRepository.findById(id);
		return magazine;
	}

	/**
	 * Updates a {@link Magazine}
	 * 
	 * @param magazineId
	 *            to be updated
	 * @param newMagazine
	 *            - JSON representation of the new Magazine
	 * @return the updated magazine
	 */
	public Magazine update(String magazineId, Magazine newMagazine) {

		Magazine updatedMagazine = null;

		if (magazineId != null
				&& magazineRepository.findById(magazineId) != null) {
			Magazine storedMagazine = magazineRepository.findById(magazineId);
			storedMagazine.setName(newMagazine.getName());
			updatedMagazine = magazineRepository.save(storedMagazine);
		}
		return updatedMagazine;
	}

	/**
	 * Deletes a {@link Magazine}
	 * 
	 * @param id
	 *            of the magazine to be deleted.
	 */
	public void delete(String id) {
		this.magazineRepository.delete(id);
		return;
	}

	/**
	 * Provides a list of all the {@link Magazine}s
	 * 
	 * @return the list of magazines
	 */
	public List<Magazine> list() {
		return this.magazineRepository.findAll();
	}

	/**
	 * Adds the {@link PoliticArticle} to the articleList of the
	 * {@link Magazine}
	 * 
	 * @param article
	 *            to be added.
	 * @param magazineId
	 *            where the article will be added to.
	 */
	public void addPoliticArticle(PoliticArticle article, String magazineId) {
		Magazine magazine = this.magazineRepository.findById(magazineId);
		magazine.addArticle(article);
		this.magazineRepository.save(magazine);
		return;
	}

	/**
	 * Adds the {@link SportArticle} to the articleList of the {@link Magazine}
	 * 
	 * @param article
	 *            to be added.
	 * @param magazineId
	 *            where the article will be added.
	 */
	public void addSportArticle(SportArticle article, String magazineId) {
		Magazine magazine = this.magazineRepository.findById(magazineId);
		magazine.addArticle(article);
		this.magazineRepository.save(magazine);
		return;
	}

}
