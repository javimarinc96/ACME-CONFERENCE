
package services;

import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.Assert;

import repositories.PaperRepository;
import domain.Actor;
import domain.Author;
import domain.Paper;

@Service
@Transactional
public class PaperService {

	//Managed repository ------------------------------------------------

	@Autowired
	private PaperRepository	paperRepository;

	//Services

	@Autowired
	private ActorService	actorService;


	// Constructor methods ---------------------------------------------------------
	public PaperService() {
		super();
	}

	//Simple CRUD methods ------------------------

	public Paper create() {

		Actor principal;
		// Principal must be an auditor
		principal = this.actorService.findByPrincipal();
		Assert.isInstanceOf(Author.class, principal);

		final Paper s = new Paper();

		s.setTitle("");
		s.setSummary("");
		s.setDocument("");
		s.setAuthors("");

		return s;
	}

	public Paper save(final Paper s) {
		Assert.notNull(s);

		Actor principal;

		// Principal must be an Admin
		principal = this.actorService.findByPrincipal();
		Assert.isInstanceOf(Author.class, principal);

		Paper result;
		result = this.paperRepository.save(s);

		return result;
	}

	public void delete(final Paper s) {

		Assert.notNull(s);
		Assert.isTrue(s.getId() != 0);

		Actor principal;

		// Principal must be an Admin
		principal = this.actorService.findByPrincipal();
		Assert.isInstanceOf(Author.class, principal);

		final Paper result = this.paperRepository.findOne(s.getId());
		Assert.notNull(result);

		this.paperRepository.delete(result);

	}

	public Paper findOne(final int PaperId) {
		Assert.isTrue(PaperId != 0);
		Paper result;

		result = this.paperRepository.findOne(PaperId);
		Assert.notNull(result);

		return result;
	}

	public Collection<Paper> findAll() {

		Collection<Paper> result;

		result = this.paperRepository.findAll();
		Assert.notNull(result);

		return result;
	}

}
