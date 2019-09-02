
package services;

import java.util.ArrayList;
import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.Assert;

import repositories.FinderRepository;
import security.Authority;
import domain.Actor;
import domain.Author;
import domain.Conference;
import domain.Finder;

@Service
@Transactional
public class FinderService {

	//Managed repository ------------------------------------------------

	@Autowired
	private FinderRepository	finderRepository;

	//Suported services ------------------------------------------------

	@Autowired
	private AuthorService		authorService;

	@Autowired
	private ActorService		actorService;

	@Autowired
	private ConferenceService	conferenceService;


	// Constructor methods ---------------------------------------------------------
	public FinderService() {
		super();
	}

	//Simple CRUD methods ------------------------

	public Finder create() {

		this.actorService.checkAuth(Authority.AUTHOR);
		final Finder res = new Finder();

		return res;
	}

	public Finder save(final Finder f) {

		Finder result;

		result = this.finderRepository.save(f);

		return result;
	}

	public Finder findOne(final int finderId) {
		Assert.isTrue(finderId != 0);
		Finder result;

		result = this.finderRepository.findOne(finderId);
		Assert.notNull(result);

		return result;
	}

	public Collection<Finder> findAll() {

		Collection<Finder> result;

		result = this.finderRepository.findAll();
		Assert.notNull(result);

		return result;
	}

	// Other business methods ---------------------------------------------------------

	public void saveResults(final Finder f) {

		// Principal must be an Admin
		final Actor principal = this.actorService.findByPrincipal();
		Assert.isInstanceOf(Author.class, principal);

		Collection<Conference> result = new ArrayList<Conference>();

		if (f.getKeyWord() != null) {
			final String s = f.getKeyWord().toLowerCase();
			final Collection<Conference> temp = new ArrayList<Conference>();

			for (final Conference c : this.conferenceService.findPublicConferences())
				if (c.getTitle().toLowerCase().contains(s))
					temp.add(c);
				else if (c.getSummary().toLowerCase().contains(s))
					temp.add(c);
				else if (c.getAcronym().toLowerCase().contains(s))
					temp.add(c);
				else if (c.getVenue().toLowerCase().contains(s))
					temp.add(c);

			if (temp.isEmpty())
				result.clear();
			else if (result.isEmpty())
				result.addAll(temp);
			else
				result.retainAll(temp);
		}

		if (f.getMaximumFee() != null) {
			final Collection<Conference> temp = this.finderRepository.findConferencesByMaxFee(f.getMaximumFee());

			if (temp.isEmpty())
				result.clear();
			else if (result.isEmpty())
				result.addAll(temp);
			else
				result.retainAll(temp);
		}

		if (f.getStartDate() != null) {
			final Collection<Conference> temp = this.finderRepository.findConferencesByStartDate(f.getStartDate());
			if (temp.isEmpty())
				result.clear();
			else if (result.isEmpty())
				result.addAll(temp);
			else
				result.retainAll(temp);
		}

		if (f.getEndDate() != null) {
			final Collection<Conference> temp = this.finderRepository.findConferencesByEndDate(f.getEndDate());
			if (temp.isEmpty())
				result.clear();
			else if (result.isEmpty())
				result.addAll(temp);
			else
				result.retainAll(temp);
		}

		if (f.getCategory() != null) {
			final Collection<Conference> temp = this.finderRepository.findConferencesByCategory(f.getCategory());
			if (temp.isEmpty())
				result.clear();
			else if (result.isEmpty())
				result.addAll(temp);
			else
				result.retainAll(temp);
		}

		if (f.getKeyWord() == null && f.getCategory() == null && f.getEndDate() == null && f.getStartDate() == null && f.getMaximumFee() == null)
			result = this.conferenceService.findPublicConferences();

		f.setConferences(result);
		this.save(f);
	}

}
