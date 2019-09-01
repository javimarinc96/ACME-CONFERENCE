
package services;

import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.Assert;

import repositories.CreditCardRepository;
import repositories.RegistrationRepository;
import domain.Actor;
import domain.Author;
import domain.CreditCard;
import domain.Registration;

@Service
@Transactional
public class RegistrationService {

	//Managed repository ------------------------------------------------

	@Autowired
	private RegistrationRepository	registrationRepository;

	@Autowired
	private CreditCardRepository	ccRepository;

	//Services

	@Autowired
	private AuthorService			authorService;

	@Autowired
	private ActorService			actorService;


	// Constructor methods ---------------------------------------------------------
	public RegistrationService() {
		super();
	}

	//Simple CRUD methods ------------------------

	public Registration create() {

		Actor principal;
		// Principal must be an auditor
		principal = this.actorService.findByPrincipal();
		Assert.isInstanceOf(Author.class, principal);
		final Author principalAuthor = this.authorService.findByPrincipal();

		final Registration s = new Registration();

		s.setAuthor(principalAuthor);
		s.setCreditCard(new CreditCard());

		return s;
	}

	public Registration save(final Registration s) {
		Assert.notNull(s);

		Actor principal;

		// Principal must be an Admin
		principal = this.actorService.findByPrincipal();
		Assert.isInstanceOf(Author.class, principal);

		if (s.getId() == 0) {
			final CreditCard cc = s.getCreditCard();
			final CreditCard saved = this.ccRepository.save(cc);
			s.setCreditCard(saved);
		}

		Registration result;
		result = this.registrationRepository.save(s);

		return result;
	}

	public void delete(final Registration s) {

		Assert.notNull(s);
		Assert.isTrue(s.getId() != 0);

		Actor principal;

		// Principal must be an Admin
		principal = this.actorService.findByPrincipal();
		Assert.isInstanceOf(Author.class, principal);

		final Registration result = this.registrationRepository.findOne(s.getId());
		Assert.notNull(result);

		this.registrationRepository.delete(result);

	}

	public Registration findOne(final int RegistrationId) {
		Assert.isTrue(RegistrationId != 0);
		Registration result;

		result = this.registrationRepository.findOne(RegistrationId);
		Assert.notNull(result);

		return result;
	}

	public Collection<Registration> findAll() {

		Collection<Registration> result;

		result = this.registrationRepository.findAll();
		Assert.notNull(result);

		return result;
	}

	public Collection<Registration> findByAuthor(final int authorId) {

		Collection<Registration> result;

		result = this.registrationRepository.findByAuthorId(authorId);
		Assert.notNull(result);

		return result;
	}

}
