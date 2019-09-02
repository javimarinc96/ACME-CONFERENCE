
package services;

import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.Assert;

import repositories.CategoryRepository;
import security.Authority;
import domain.Category;
import domain.Conference;

@Service
@Transactional
public class CategoryService {

	//Managed repository ------------------------------------------------

	@Autowired
	private CategoryRepository	categoryRepository;

	//Other repository -----------------------------------------------------

	@Autowired
	private ActorService		actorService;

	@Autowired
	private ConferenceService	conferenceService;


	public CategoryService() {
		super();
	}

	//Simple CRUD methods ------------------------

	public Category create() {

		this.actorService.checkAuth(Authority.ADMIN);
		final Category res = new Category();

		return res;
	}
	public Category save(final Category category) {

		Assert.notNull(category);
		this.actorService.checkAuth(Authority.ADMIN);

		Category result;

		result = this.categoryRepository.save(category);

		return result;
	}

	public void delete(final Category category) {

		Assert.notNull(category);
		this.actorService.checkAuth(Authority.ADMIN);

		Assert.isTrue(this.isRootCategory(category).equals(false));

		final Category padre = category.getParent();

		final Collection<Conference> asignadas = this.categoryRepository.findConferencesByCategory(category.getId());

		for (final Conference conf : asignadas) {
			conf.setCategory(padre);
			this.conferenceService.save(conf);
		}

		final Collection<Category> cats = this.findCategoryHijas(category.getId());

		for (final Category cat : cats) {
			cat.setParent(padre);
			this.categoryRepository.save(cat);
		}

		this.categoryRepository.delete(category);

	}
	public Collection<Conference> findConferencesByCategory(final int categoryId) {
		return this.categoryRepository.findConferencesByCategory(categoryId);

	}

	public Collection<Category> findCategoryHijas(final int categoryId) {
		return this.categoryRepository.findCategoryHijas(categoryId);

	}

	public Category findOne(final int categoryId) {
		Assert.isTrue(categoryId != 0);
		Category result;

		result = this.categoryRepository.findOne(categoryId);
		Assert.notNull(result);

		return result;
	}

	public Collection<Category> findAll() {

		Collection<Category> result;

		result = this.categoryRepository.findAll();
		Assert.notNull(result);

		return result;
	}

	public Boolean isRootCategory(final Category category) {
		Boolean res = false;
		if (category.getName().equals("CONFERENCE"))
			res = true;
		return res;
	}

}
