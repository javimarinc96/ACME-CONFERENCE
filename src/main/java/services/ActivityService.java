
package services;

import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.Assert;

import repositories.ActivityRepository;
import domain.Activity;
import domain.Actor;
import domain.Administrator;

@Service
@Transactional
public class ActivityService {

	//Managed repository ------------------------------------------------

	@Autowired
	private ActivityRepository	activityRepository;

	//Services

	@Autowired
	private ActorService		actorService;


	// Constructor methods ---------------------------------------------------------
	public ActivityService() {
		super();
	}

	//Simple CRUD methods ------------------------

	public Activity save(final Activity s) {
		Assert.notNull(s);

		Actor principal;

		// Principal must be an Admin
		principal = this.actorService.findByPrincipal();
		Assert.isInstanceOf(Administrator.class, principal);

		Activity result;
		result = this.activityRepository.save(s);

		return result;
	}

	public void delete(final Activity s) {

		Assert.notNull(s);
		Assert.isTrue(s.getId() != 0);

		Actor principal;

		// Principal must be an Admin
		principal = this.actorService.findByPrincipal();
		Assert.isInstanceOf(Administrator.class, principal);

		final Activity result = this.activityRepository.findOne(s.getId());
		Assert.notNull(result);

		this.activityRepository.delete(result);

	}

	public Activity findOne(final int ActivityId) {
		Assert.isTrue(ActivityId != 0);
		Activity result;

		result = this.activityRepository.findOne(ActivityId);
		Assert.notNull(result);

		return result;
	}

	public Collection<Activity> findAll() {

		Collection<Activity> result;

		result = this.activityRepository.findAll();
		Assert.notNull(result);

		return result;
	}

	public Collection<Activity> findActivitiesByConference(final int conferenceId) {

		Collection<Activity> result;

		result = this.activityRepository.findActivitiesByConference(conferenceId);
		Assert.notNull(result);

		return result;
	}

}
