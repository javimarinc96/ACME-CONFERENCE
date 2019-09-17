
package services;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collection;
import java.util.Date;
import java.util.Locale;

import org.apache.commons.lang.RandomStringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.Assert;

import repositories.DompRepository;
import domain.Actor;
import domain.Administrator;
import domain.Domp;

@Service
@Transactional
public class DompService {

	// Managed repository -------------------------------------------
	@Autowired
	private DompRepository		DompRepository;

	// Supported services -------------------------------------------

	@Autowired
	private ActorService			actorService;

	@Autowired
	private AdministratorService	administratorService;


	// Constructor methods -------------------------------------------
	public DompService() {
		super();
	}

	// Simple CRUD methods ------------------------------------------

	public Domp create() {
		Domp res;
		Actor principal;

		// Principal must be an Dompor
		principal = this.actorService.findByPrincipal();
		Assert.isInstanceOf(Administrator.class, principal);
		final Administrator principalAdministrator = this.administratorService.findByPrincipal();

		res = new Domp();

		res.setBody("");
		res.setPicture("");
		res.setMoment(new Date());
		res.setTicker(this.generateTicker());
		res.setDraftMode(true);
		res.setAdministrator(principalAdministrator);

		return res;
	}

	public Domp findOne(final int id) {
		final Domp result = this.DompRepository.findOne(id);

		Assert.notNull(result);

		return result;
	}

	public Collection<Domp> findAll() {
		final Collection<Domp> result = this.DompRepository.findAll();

		return result;

	}

	public Domp save(final Domp a) {
		Assert.notNull(a);
		Actor principal;

		// Principal must be a Administrator
		principal = this.actorService.findByPrincipal();
		Assert.isInstanceOf(Administrator.class, principal);

		this.checkDomp(a);

		if (a.getDraftMode() == true)
			a.setMoment(null);
		else if (a.getDraftMode() == false)
			a.setMoment(new Date());

		return this.DompRepository.save(a);
	}

	public void delete(final int DompId) {

		Assert.notNull(DompId);

		final Domp a = this.findOne(DompId);
		Assert.notNull(a);

		// Principal must be a Administrator
		final Actor principal = this.actorService.findByPrincipal();
		Assert.isInstanceOf(Administrator.class, principal);

		//Domp must be in draft mode
		Assert.isTrue(a.getDraftMode());

		this.DompRepository.delete(DompId);

	}

	// Other business methods ---------------------------------------------------------

	public String generateTicker() {
		String ticker = "";
		final DateFormat dateFormat = new SimpleDateFormat("dd");
		final DateFormat dateFormat2 = new SimpleDateFormat("MM");
		final DateFormat dateFormat3 = new SimpleDateFormat("yy");
		final Date date = new Date();
		final String tickerDate = (dateFormat.format(date));
		final String tickerDate2 = (dateFormat2.format(date));
		final String tickerDate3 = (dateFormat3.format(date));
		final String tickerAlphabetic = RandomStringUtils.randomAlphabetic(4).toUpperCase(Locale.ENGLISH);
		ticker = ticker.concat(tickerAlphabetic).concat(":").concat(tickerDate3).concat(":").concat(tickerDate2).concat(tickerDate);
		return ticker;
	}

	public void flush() {
		this.DompRepository.flush();
	}
	
	public Collection<Domp> getDompsByOneMonthAntiquity(final int conferenceId){
		final Collection<Domp> result = new ArrayList<Domp>();

		final Collection<Domp> tests = this.getDompsByConference(conferenceId);

		final Calendar c = Calendar.getInstance();
		c.setTime(new Date());
		
		c.add(Calendar.MONTH, -1);
		
		for (final Domp t : tests)
			if (t.getDraftMode() == false) {
				if (t.getMoment().after(c.getTime()))
					result.add(t);
			}
		
		return result;
	}

	public Collection<Domp> getDompsByOneTwoMonthAntiquity(final int conferenceId){
		final Collection<Domp> result = new ArrayList<Domp>();

		final Collection<Domp> tests = this.getDompsByConference(conferenceId);

		final Calendar c = Calendar.getInstance();
		c.setTime(new Date());
		
		final Calendar c2 = Calendar.getInstance();
		c2.setTime(new Date());
		
		c.add(Calendar.MONTH, -1);
		c2.add(Calendar.MONTH, -2);
		
		for (final Domp t : tests)
			if (t.getDraftMode() == false) {
				if (t.getMoment().after(c.getTime()) && t.getMoment().before(c.getTime()))
					result.add(t);
			}
		
		return result;
	}
	
	
	public Collection<Domp> getDompsByTwoMonthAntiquity(final int conferenceId){
		final Collection<Domp> result = new ArrayList<Domp>();

		final Collection<Domp> tests = this.getDompsByConference(conferenceId);

		final Calendar c = Calendar.getInstance();
		c.setTime(new Date());
		
		c.add(Calendar.MONTH, -2);
		
		for (final Domp t : tests)
			if (t.getDraftMode() == false) {
				if (t.getMoment().before(c.getTime()))
					result.add(t);
			}
		
		return result;
	}
	
	public void checkDomp(final Domp a) {
		boolean check = true;

		if (a.getBody() == null || a.getTicker() == null)
			check = false;

		Assert.isTrue(check);
	}

	public Collection<Domp> getDompsByAdministrator(final int administratorId) {
		final Actor logueado = this.actorService.findByPrincipal();
		Assert.isInstanceOf(Administrator.class, logueado);
		return this.DompRepository.getDompsByAdministrator(administratorId);
	}

	public Collection<Domp> getDompsByConference(final int conferenceId) {
		return this.DompRepository.getDompsByConference(conferenceId);
	}

}
