
package services;

import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.Assert;

import repositories.PanelRepository;
import domain.Actor;
import domain.Administrator;
import domain.Conference;
import domain.Panel;

@Service
@Transactional
public class PanelService {

	//Managed repository ------------------------------------------------

	@Autowired
	private PanelRepository		panelRepository;

	//Services

	@Autowired
	private ActorService		actorService;

	@Autowired
	private ConferenceService	conferenceService;


	// Constructor methods ---------------------------------------------------------
	public PanelService() {
		super();
	}

	//Simple CRUD methods ------------------------

	public Panel create() {

		Actor principal;
		// Principal must be an auditor
		principal = this.actorService.findByPrincipal();
		Assert.isInstanceOf(Administrator.class, principal);

		final Panel s = new Panel();

		return s;
	}

	public Panel save(final Panel s, final int conferencId) {
		Assert.notNull(s);

		Actor principal;

		final Conference c = this.conferenceService.findOne(conferencId);

		// Principal must be an Admin
		principal = this.actorService.findByPrincipal();
		Assert.isInstanceOf(Administrator.class, principal);

		s.setSchedule(s.getStartMoment().toString() + "-" + s.getDuration().toString());

		Assert.isTrue(s.getStartMoment().after(c.getStartDate()));
		Assert.isTrue(s.getStartMoment().before(c.getEndDate()));

		Panel result;
		result = this.panelRepository.save(s);

		c.getActivities().add(result);

		this.conferenceService.save(c);

		return result;
	}

	public Panel saveEdit(final Panel s) {
		Assert.notNull(s);

		Actor principal;

		// Principal must be an Admin
		principal = this.actorService.findByPrincipal();
		Assert.isInstanceOf(Administrator.class, principal);

		s.setSchedule(s.getStartMoment().toString() + "-" + s.getDuration().toString());

		final Conference c = this.conferenceService.findConferenceByActivity(s.getId());

		Assert.isTrue(s.getStartMoment().after(c.getStartDate()));
		Assert.isTrue(s.getStartMoment().before(c.getEndDate()));

		Panel result;
		result = this.panelRepository.save(s);

		return result;
	}

	public void delete(final Panel s) {

		Assert.notNull(s);
		Assert.isTrue(s.getId() != 0);

		Actor principal;

		// Principal must be an Admin
		principal = this.actorService.findByPrincipal();
		Assert.isInstanceOf(Administrator.class, principal);

		final Conference c = this.conferenceService.findConferenceByActivity(s.getId());

		c.getActivities().remove(s);

		this.conferenceService.save(c);

		final Panel result = this.panelRepository.findOne(s.getId());
		Assert.notNull(result);

		this.panelRepository.delete(result);

	}

	public Panel findOne(final int PanelId) {
		Assert.isTrue(PanelId != 0);
		Panel result;

		result = this.panelRepository.findOne(PanelId);
		Assert.notNull(result);

		return result;
	}

	public Collection<Panel> findAll() {

		Collection<Panel> result;

		result = this.panelRepository.findAll();
		Assert.notNull(result);

		return result;
	}

}
