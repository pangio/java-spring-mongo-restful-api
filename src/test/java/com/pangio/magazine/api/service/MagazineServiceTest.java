package com.pangio.magazine.api.service;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import static org.mockito.Matchers.any;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.verifyNoMoreInteractions;
import static org.mockito.Mockito.when;

import org.junit.Before;
import org.junit.Test;

import com.pangio.magazine.api.domain.Magazine;
import com.pangio.magazine.api.domain.PoliticArticle;
import com.pangio.magazine.api.domain.SportArticle;
import com.pangio.magazine.api.repository.MagazineRepository;

/**
 * {@link MagazineServiceTest} guarantees the proper behavior of the
 * {@link MagazineService} and its methods.
 * 
 * @author pangio
 */
public class MagazineServiceTest {

	private MagazineRepository magazineRepositoryMock;
	private MagazineService magazineService;

	@Before
	public void setUp() {
		magazineRepositoryMock = mock(MagazineRepository.class);
		magazineService = new MagazineService(magazineRepositoryMock);
	}

	/**
	 * Verifies that a new magazine was created and saved
	 */
	@Test
	public void createNewMagazine() {

		when(magazineRepositoryMock.save(any(Magazine.class))).thenReturn(
				new Magazine());
		magazineService.create(new Magazine());

		// verify that the magazine repository has performed a save action to
		// grab the new magazine
		verify(magazineRepositoryMock).save(any(Magazine.class));
		verifyNoMoreInteractions(magazineRepositoryMock);

	}

	/**
	 * Verifies that a magazine without articles was removed
	 */
	@Test
	public void deleteMagazineWithoutArticles() {

		Magazine magazine = new Magazine();

		when(magazineRepositoryMock.save(any(Magazine.class))).thenReturn(
				magazine);
		when(magazineRepositoryMock.findById(any(String.class))).thenReturn(
				magazine);

		magazineService.create(new Magazine());

		assertTrue(magazine.getArticles().isEmpty());

		magazineService.delete("1");

		// verify that the magazine repository has performed save action and
		// then delete
		verify(magazineRepositoryMock).save(any(Magazine.class));
		verify(magazineRepositoryMock).delete("1");
		verifyNoMoreInteractions(magazineRepositoryMock);

	}

	/**
	 * Verifies that a magazine with articles was removed
	 */
	@Test
	public void deleteMagazineWithArticles() {

		Magazine magazine = new Magazine();
		SportArticle article = new SportArticle();

		when(magazineRepositoryMock.save(any(Magazine.class))).thenReturn(
				magazine);
		when(magazineRepositoryMock.findById(any(String.class))).thenReturn(
				magazine);

		magazineService.addSportArticle(article, magazine.getId());

		// verify that the magazine repository has performed a save action to
		// grab the magazine with new article
		verify(magazineRepositoryMock).save(any(Magazine.class));

		// verify that the magazine list of articles is not empty, and the
		// article exist on its list
		assertFalse(magazine.getArticles().isEmpty());
		assertEquals(magazine.getArticles().size(), 1);
		assertTrue(magazine.getArticles().contains(article));

		// verify the magazine is removed
		magazineService.delete("1");
		verify(magazineRepositoryMock).delete("1");

	}

	/**
	 * Verifies that a new sport article was added to a magazine
	 */
	@Test
	public void addSingleSportArticlesToMagazine() {

		Magazine magazine = new Magazine();
		SportArticle article = new SportArticle();

		when(magazineRepositoryMock.save(any(Magazine.class))).thenReturn(
				magazine);
		when(magazineRepositoryMock.findById(any(String.class))).thenReturn(
				magazine);

		magazineService.addSportArticle(article, magazine.getId());

		// verify that the magazine repository has performed a save action to
		// grab the magazine with new article
		verify(magazineRepositoryMock).save(any(Magazine.class));

		// verify that the magazine list of articles is not empty, and the
		// article exist on its list
		assertFalse(magazine.getArticles().isEmpty());
		assertEquals(magazine.getArticles().size(), 1);
		assertTrue(magazine.getArticles().contains(article));

	}

	/**
	 * Verifies that a new politic article was added to a magazine
	 */
	@Test
	public void addSinglePoliticArticleToMagazine() {

		Magazine magazine = new Magazine();
		PoliticArticle article = new PoliticArticle();

		when(magazineRepositoryMock.save(any(Magazine.class))).thenReturn(
				magazine);
		when(magazineRepositoryMock.findById(any(String.class))).thenReturn(
				magazine);

		magazineService.addPoliticArticle(article, magazine.getId());

		// verify that the magazine repository has performed a save action to
		// grab the magazine with new article
		verify(magazineRepositoryMock).save(any(Magazine.class));

		// verify that the magazine list of articles is not empty, and the
		// article exist on its list
		assertFalse(magazine.getArticles().isEmpty());
		assertEquals(magazine.getArticles().size(), 1);
		assertTrue(magazine.getArticles().contains(article));

	}

	/**
	 * Verifies that two new magazines were created and saved
	 */
	@Test
	public void addTwoNewMagazines() {

		when(magazineRepositoryMock.save(any(Magazine.class))).thenReturn(
				new Magazine());

		// Create two magazines
		magazineService.create(new Magazine());
		magazineService.create(new Magazine());

		// verify that the magazine repository has performed two times the save
		// action to grab both magazines
		verify(magazineRepositoryMock, times(2)).save(any(Magazine.class));
		verifyNoMoreInteractions(magazineRepositoryMock);
	}

}