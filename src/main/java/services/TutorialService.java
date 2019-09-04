
package services;

import java.util.Collection;
import java.util.Date;
import java.util.HashSet;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.Assert;

import repositories.TutorialRepository;
import domain.Actor;
import domain.Administrator;
import domain.Conference;
import domain.Section;
import domain.Tutorial;

@Service
@Transactional
public class TutorialService {

	//Managed repository ------------------------------------------------

	@Autowired
	private TutorialRepository	tutorialRepository;

	//Services

	@Autowired
	private ActorService		actorService;

	@Autowired
	private ConferenceService	conferenceService;

	@Autowired
	private SectionService		sectionService;


	// Constructor methods ---------------------------------------------------------
	public TutorialService() {
		super();
	}

	//Simple CRUD methods ------------------------

	public Tutorial create() {

		Actor principal;
		// Principal must be an auditor
		principal = this.actorService.findByPrincipal();
		Assert.isInstanceOf(Administrator.class, principal);

		final Tutorial s = new Tutorial();

		final Collection<Section> sections = new HashSet<Section>();

		//		final Section t = new Section();
		//		t.setPictures("http://www.picture.jpg");
		//		t.setSummary("summary 0");
		//		t.setTitle("title 0");
		//
		//		sections.add(t);

		s.setSections(sections);

		return s;
	}

	public Tutorial save(final Tutorial s, final int conferencId) {
		Assert.notNull(s);

		final Conference c = this.conferenceService.findOne(conferencId);

		Actor principal;

		// Principal must be an Admin
		principal = this.actorService.findByPrincipal();
		Assert.isInstanceOf(Administrator.class, principal);

		s.setSchedule(s.getStartMoment().toString() + "-" + s.getDuration().toString());

		Assert.isTrue(s.getStartMoment().after(c.getStartDate()));
		Assert.isTrue(s.getStartMoment().before(c.getEndDate()));

		Tutorial result;
		result = this.tutorialRepository.save(s);

		c.getActivities().add(result);

		this.conferenceService.save(c);

		return result;
	}

	public Tutorial saveEdit(final Tutorial s) {
		Assert.notNull(s);

		Actor principal;

		// Principal must be an Admin
		principal = this.actorService.findByPrincipal();
		Assert.isInstanceOf(Administrator.class, principal);

		s.setSchedule(s.getStartMoment().toString() + "-" + s.getDuration().toString());
		
		Conference c = this.conferenceService.findConferenceByActivity(s.getId());

		Assert.isTrue(s.getStartMoment().after(c.getStartDate()));
		Assert.isTrue(s.getStartMoment().before(c.getEndDate()));
		
		Tutorial result;
		result = this.tutorialRepository.save(s);

		return result;
	}

	public void delete(final Tutorial s) {

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

		final Tutorial result = this.tutorialRepository.findOne(s.getId());
		Assert.notNull(result);

		this.tutorialRepository.delete(result);

	}

	public Tutorial findOne(final int TutorialId) {
		Assert.isTrue(TutorialId != 0);
		Tutorial result;

		result = this.tutorialRepository.findOne(TutorialId);
		Assert.notNull(result);

		return result;
	}

	public Collection<Tutorial> findAll() {

		Collection<Tutorial> result;

		result = this.tutorialRepository.findAll();
		Assert.notNull(result);

		return result;
	}

	public Tutorial findTutorialBySection(final int sectionId) {

		Actor principal;

		// Principal must be an Admin
		principal = this.actorService.findByPrincipal();
		Assert.isInstanceOf(Administrator.class, principal);

		final Tutorial result = this.tutorialRepository.findTutorialBySectionId(sectionId);

		return result;
	}

}
