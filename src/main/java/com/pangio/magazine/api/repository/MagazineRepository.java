package com.pangio.magazine.api.repository;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.pangio.magazine.api.domain.Magazine;

/**
 * {@link MagazineRepository} is where all the {@link Magazine}s will be stored.
 * Also see {@link MongoRepository}
 * 
 * @author pangio
 */
public interface MagazineRepository extends MongoRepository<Magazine, String> {

	/**
	 * Finds a {@link Magazine} by id.
	 * 
	 * @param id
	 *            of the requested magazine.
	 * @return the magazine.
	 */
	Magazine findById(String id);

	/**
	 * Finds a {@link Magazine} by name.
	 * 
	 * @param name
	 *            of the requested magazine.
	 * @return the magazine.
	 */
	Magazine findByName(String name);

}
