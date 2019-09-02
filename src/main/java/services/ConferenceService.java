
package services;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collection;
import java.util.Date;
import java.util.HashSet;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.Assert;

import repositories.ConferenceRepository;
import domain.Actor;
import domain.Administrator;
import domain.Conference;

@Service
@Transactional
public class ConferenceService {

	//Managed repository ------------------------------------------------

	@Autowired
	private ConferenceRepository	conferenceRepository;

	//Services

	@Autowired
	private AdministratorService	adminService;

	@Autowired
	private ActorService			actorService;


	// Constructor methods ---------------------------------------------------------
	public ConferenceService() {
		super();
	}

	//Simple CRUD methods ------------------------

	public Conference create() {

		Actor principal;
		// Principal must be an auditor
		principal = this.actorService.findByPrincipal();
		Assert.isInstanceOf(Administrator.class, principal);
		final Administrator principalAdmin = this.adminService.findByPrincipal();

		final Conference c = new Conference();

		c.setDraftMode(true);
		c.setAdmin(principalAdmin);

		return c;
	}

	public Conference save(final Conference c) {
		Assert.notNull(c);

		Actor principal;

		// Principal must be an Admin
		principal = this.actorService.findByPrincipal();
		Assert.isInstanceOf(Administrator.class, principal);

		Assert.isTrue(c.getSubmissionDeadline().before(c.getNotificationDeadline()));
		Assert.isTrue(c.getNotificationDeadline().before(c.getCameraDeadline()));
		Assert.isTrue(c.getCameraDeadline().before(c.getStartDate()));
		Assert.isTrue(c.getStartDate().before(c.getEndDate()));

		Conference result;
		result = this.conferenceRepository.save(c);

		return result;
	}

	public void delete(final Conference s) {

		Assert.notNull(s);
		Assert.isTrue(s.getId() != 0);

		Actor principal;

		// Principal must be an Admin
		principal = this.actorService.findByPrincipal();
		Assert.isInstanceOf(Administrator.class, principal);

		final Conference result = this.conferenceRepository.findOne(s.getId());
		Assert.notNull(result);

		this.conferenceRepository.delete(result);

	}

	public Conference findOne(final int ConferenceId) {
		Assert.isTrue(ConferenceId != 0);
		Conference result;

		result = this.conferenceRepository.findOne(ConferenceId);
		Assert.notNull(result);

		return result;
	}

	public Collection<Conference> findAll() {

		Collection<Conference> result;

		result = this.conferenceRepository.findAll();
		Assert.notNull(result);

		return result;
	}

	public Collection<Conference> findSubmissionDeadline5Conferences() {

		// Principal must be an Admin
		final Actor principal = this.actorService.findByPrincipal();
		Assert.isInstanceOf(Administrator.class, principal);

		Collection<Conference> all;

		all = this.conferenceRepository.findAll();

		final Collection<Conference> result = new HashSet<Conference>();

		final Calendar calendar = Calendar.getInstance();

		calendar.setTime(new Date());

		calendar.add(Calendar.DAY_OF_YEAR, -5);

		final Date fivedaysago = calendar.getTime();

		for (final Conference c : all)
			if (c.getSubmissionDeadline().before(new Date()) && c.getSubmissionDeadline().after(fivedaysago))
				result.add(c);

		return result;
	}

	public Collection<Conference> findNotificationDeadline5Conferences() {

		// Principal must be an Admin
		final Actor principal = this.actorService.findByPrincipal();
		Assert.isInstanceOf(Administrator.class, principal);

		Collection<Conference> all;

		all = this.conferenceRepository.findAll();

		final Collection<Conference> result = new HashSet<Conference>();

		final Calendar calendar = Calendar.getInstance();

		calendar.setTime(new Date());

		calendar.add(Calendar.DAY_OF_YEAR, 5);

		final Date fivedayslater = calendar.getTime();

		for (final Conference c : all)
			if (c.getNotificationDeadline().after(new Date()) && c.getSubmissionDeadline().before(fivedayslater))
				result.add(c);

		return result;
	}
	public Collection<Conference> findCameraDeadline5Conferences() {

		// Principal must be an Admin
		final Actor principal = this.actorService.findByPrincipal();
		Assert.isInstanceOf(Administrator.class, principal);

		Collection<Conference> all;

		all = this.conferenceRepository.findAll();

		final Collection<Conference> result = new HashSet<Conference>();

		final Calendar calendar = Calendar.getInstance();

		calendar.setTime(new Date());

		calendar.add(Calendar.DAY_OF_YEAR, 5);

		final Date fivedayslater = calendar.getTime();

		for (final Conference c : all)
			if (c.getCameraDeadline().after(new Date()) && c.getCameraDeadline().before(fivedayslater))
				result.add(c);

		return result;
	}

	public Collection<Conference> findStartDate5Conferences() {

		// Principal must be an Admin
		final Actor principal = this.actorService.findByPrincipal();
		Assert.isInstanceOf(Administrator.class, principal);

		Collection<Conference> all;

		all = this.conferenceRepository.findAll();

		final Collection<Conference> result = new HashSet<Conference>();

		final Calendar calendar = Calendar.getInstance();

		calendar.setTime(new Date());

		calendar.add(Calendar.DAY_OF_YEAR, 5);

		final Date fivedayslater = calendar.getTime();

		for (final Conference c : all)
			if (c.getStartDate().after(new Date()) && c.getStartDate().before(fivedayslater))
				result.add(c);

		return result;
	}

	public Collection<Conference> findPastConferences() {

		Collection<Conference> all;

		all = this.conferenceRepository.findAll();

		final Collection<Conference> result = new HashSet<Conference>();

		for (final Conference c : all)
			if (c.getEndDate().before(new Date()) && c.getDraftMode().equals(false))
				result.add(c);

		return result;
	}

	public Collection<Conference> findForthcomingConferences() {

		Collection<Conference> all;

		all = this.conferenceRepository.findAll();

		final Collection<Conference> result = new HashSet<Conference>();

		for (final Conference c : all)
			if (c.getStartDate().after(new Date()) && c.getDraftMode().equals(false))
				result.add(c);

		return result;
	}

	public Collection<Conference> findRunningConferences() {

		Collection<Conference> all;

		all = this.conferenceRepository.findAll();

		final Collection<Conference> result = new HashSet<Conference>();

		for (final Conference c : all)
			if (c.getStartDate().before(new Date()) && c.getEndDate().after(new Date()) && c.getDraftMode().equals(false))
				result.add(c);

		return result;
	}

	public Collection<Conference> findAvailableConferences() {

		Collection<Conference> all;

		all = this.conferenceRepository.findAll();

		final Collection<Conference> result = new HashSet<Conference>();

		for (final Conference c : all)
			if (c.getSubmissionDeadline().after(new Date()) && c.getDraftMode().equals(false))
				result.add(c);

		return result;
	}

	public Collection<Conference> findPublicConferences() {

		Collection<Conference> all;

		all = this.conferenceRepository.findAll();

		final Collection<Conference> result = new HashSet<Conference>();

		for (final Conference c : all)
			if (c.getDraftMode().equals(false))
				result.add(c);

		return result;
	}

	public Conference findConferenceByActivity(final int activityId) {

		Actor principal;

		// Principal must be an Admin
		principal = this.actorService.findByPrincipal();
		Assert.isInstanceOf(Administrator.class, principal);

		final Conference result = this.conferenceRepository.findConferenceByActivityId(activityId);

		return result;
	}

	public Collection<Conference> searcherConferences(final String searcher) {
		final Collection<Conference> result = new ArrayList<Conference>();
		final Collection<Conference> all = this.findAll();

		final String s = searcher.toLowerCase();

		for (final Conference c : all)
			if (c.getTitle().toLowerCase().contains(s) || c.getVenue().toLowerCase().contains(s) || c.getSummary().toLowerCase().contains(s) || c.getCategory().getName().toLowerCase().contains(s) || c.getCategory().getNombre().toLowerCase().contains(s))
				if (c.getDraftMode().equals(false))
					result.add(c);

		return result;
	}
}
