
package services;

import java.util.Collection;
import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.Assert;

import repositories.PaperRepository;
import repositories.PresentationRepository;
import domain.Actor;
import domain.Administrator;
import domain.Conference;
import domain.Paper;
import domain.Presentation;

@Service
@Transactional
public class PresentationService {

	//Managed repository ------------------------------------------------

	@Autowired
	private PresentationRepository	presentationRepository;

	@Autowired
	private PaperRepository			paperRepository;

	//Services

	@Autowired
	private ActorService			actorService;

	@Autowired
	private ConferenceService		conferenceService;


	// Constructor methods ---------------------------------------------------------
	public PresentationService() {
		super();
	}

	//Simple CRUD methods ------------------------

	public Presentation create() {

		Actor principal;
		// Principal must be an auditor
		principal = this.actorService.findByPrincipal();
		Assert.isInstanceOf(Administrator.class, principal);

		final Presentation s = new Presentation();

		return s;
	}

	public Presentation save(final Presentation s, final int conferenceId) {
		Assert.notNull(s);

		Actor principal;

		final Conference c = this.conferenceService.findOne(conferenceId);

		// Principal must be an Admin
		principal = this.actorService.findByPrincipal();
		Assert.isInstanceOf(Administrator.class, principal);

		s.setSchedule(s.getStartMoment().toString() + "-" + s.getDuration().toString());

		final Paper p = s.getCameraReady();
		final Paper saved = this.paperRepository.save(p);
		s.setCameraReady(saved);

		Assert.isTrue(s.getStartMoment().after(c.getStartDate()));
		Assert.isTrue(s.getStartMoment().before(c.getEndDate()));

		Presentation result;
		result = this.presentationRepository.save(s);

		c.getActivities().add(result);

		this.conferenceService.save(c);

		return result;
	}

	public Presentation saveEdit(final Presentation s) {
		Assert.notNull(s);

		Actor principal;

		// Principal must be an Admin
		principal = this.actorService.findByPrincipal();
		Assert.isInstanceOf(Administrator.class, principal);

		s.setSchedule(s.getStartMoment().toString() + "-" + s.getDuration().toString());

		final Paper p = s.getCameraReady();
		final Paper saved = this.paperRepository.save(p);
		s.setCameraReady(saved);

		final Conference c = this.conferenceService.findConferenceByActivity(s.getId());

		Assert.isTrue(s.getStartMoment().after(c.getStartDate()));
		Assert.isTrue(s.getStartMoment().before(c.getEndDate()));

		Presentation result;
		result = this.presentationRepository.save(s);

		return result;
	}

	public void delete(final Presentation s) {

		Assert.notNull(s);
		Assert.isTrue(s.getId() != 0);

		Actor principal;

		// Principal must be an Admin
		principal = this.actorService.findByPrincipal();
		Assert.isInstanceOf(Administrator.class, principal);

		final Conference c = this.conferenceService.findConferenceByActivity(s.getId());
		
		Assert.isTrue(c.getStartDate().after(new Date()));

		c.getActivities().remove(s);

		this.conferenceService.save(c);

		final Presentation result = this.presentationRepository.findOne(s.getId());
		Assert.notNull(result);

		this.presentationRepository.delete(result);

	}

	public Presentation findOne(final int PresentationId) {
		Assert.isTrue(PresentationId != 0);
		Presentation result;

		result = this.presentationRepository.findOne(PresentationId);
		Assert.notNull(result);

		return result;
	}

	public Collection<Presentation> findAll() {

		Collection<Presentation> result;

		result = this.presentationRepository.findAll();
		Assert.notNull(result);

		return result;
	}

}
