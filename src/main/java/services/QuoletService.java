
package services;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collection;
import java.util.Date;

import org.apache.commons.lang.RandomStringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.Assert;

import repositories.QuoletRepository;
import domain.Actor;
import domain.Administrator;
import domain.Quolet;

@Service
@Transactional
public class QuoletService {

	// Managed repository -------------------------------------------
	@Autowired
	private QuoletRepository		QuoletRepository;

	// Supported services -------------------------------------------

	@Autowired
	private ActorService			actorService;

	@Autowired
	private AdministratorService	administratorService;


	// Constructor methods -------------------------------------------
	public QuoletService() {
		super();
	}

	// Simple CRUD methods ------------------------------------------

	public Quolet create() {
		Quolet res;
		Actor principal;

		// Principal must be an Quoletor
		principal = this.actorService.findByPrincipal();
		Assert.isInstanceOf(Administrator.class, principal);
		final Administrator principalAdministrator = this.administratorService.findByPrincipal();

		res = new Quolet();

		res.setBody("");
		res.setPhoto("");
		res.setMoment(new Date());
		res.setTicker(this.generateTicker());
		res.setDraftMode(true);
		res.setAdministrator(principalAdministrator);

		return res;
	}

	public Quolet findOne(final int id) {
		final Quolet result = this.QuoletRepository.findOne(id);

		Assert.notNull(result);

		return result;
	}

	public Collection<Quolet> findAll() {
		final Collection<Quolet> result = this.QuoletRepository.findAll();

		return result;

	}

	public Quolet save(final Quolet a) {
		Assert.notNull(a);
		Actor principal;

		// Principal must be a Administrator
		principal = this.actorService.findByPrincipal();
		Assert.isInstanceOf(Administrator.class, principal);

		this.checkQuolet(a);

		if (a.getDraftMode() == true)
			a.setMoment(null);
		else if (a.getDraftMode() == false)
			a.setMoment(new Date());

		return this.QuoletRepository.save(a);
	}

	public void delete(final int QuoletId) {

		Assert.notNull(QuoletId);

		final Quolet a = this.findOne(QuoletId);
		Assert.notNull(a);

		// Principal must be a Administrator
		final Actor principal = this.actorService.findByPrincipal();
		Assert.isInstanceOf(Administrator.class, principal);

		//Quolet must be in draft mode
		Assert.isTrue(a.getDraftMode());

		this.QuoletRepository.delete(QuoletId);

	}

	// Other business methods ---------------------------------------------------------

	public String generateTicker() {
		String ticker = "";
		final DateFormat dateFormat = new SimpleDateFormat("dd");
		final DateFormat dateFormat2 = new SimpleDateFormat("MMyy");
		final Date date = new Date();
		final String tickerDate = (dateFormat.format(date));
		final String tickerDate2 = (dateFormat2.format(date));
		final String tickerAlphabetic = RandomStringUtils.randomAlphabetic(4);
		ticker = ticker.concat(tickerDate).concat(tickerAlphabetic).concat("-").concat(tickerDate2);
		return ticker;
	}

	public void flush() {
		this.QuoletRepository.flush();
	}

	public Collection<Quolet> getQuoletsByAntiquity(final int conferenceId, final int months) {
		final Collection<Quolet> result = new ArrayList<Quolet>();

		final Collection<Quolet> tests = this.getQuoletsByConference(conferenceId);

		final Calendar c = Calendar.getInstance();
		c.setTime(new Date());

		final Calendar c2 = Calendar.getInstance();
		c2.setTime(new Date());

		for (final Quolet t : tests)

			if (months == 1 && t.getDraftMode() == false) {
				c.add(Calendar.MONTH, -1);
				if (t.getMoment().after(c.getTime()))
					result.add(t);
			} else if (months == 2 && t.getDraftMode() == false) {
				c.add(Calendar.MONTH, -2);
				c2.add(Calendar.MONTH, -1);
				if (t.getMoment().after(c.getTime()) && t.getMoment().before(c2.getTime()))
					result.add(t);
			} else if (months == 3 && t.getDraftMode() == false) {
				c.add(Calendar.MONTH, -2);
				if (t.getMoment().before(c.getTime()))
					result.add(t);
			}

		return result;
	}

	public void checkQuolet(final Quolet a) {
		boolean check = true;

		if (a.getBody() == null || a.getTicker() == null)
			check = false;

		Assert.isTrue(check);
	}

	public Collection<Quolet> getQuoletsByAdministrator(final int administratorId) {
		final Actor logueado = this.actorService.findByPrincipal();
		Assert.isInstanceOf(Administrator.class, logueado);
		return this.QuoletRepository.getQuoletsByAdministrator(administratorId);
	}

	public Collection<Quolet> getQuoletsByConference(final int conferenceId) {
		return this.QuoletRepository.getQuoletsByConference(conferenceId);
	}

}
