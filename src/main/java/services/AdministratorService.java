
package services;

import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.encoding.Md5PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.Assert;

import repositories.AdministratorRepository;
import security.Authority;
import security.LoginService;
import security.UserAccount;
import security.UserAccountRepository;
import domain.Actor;
import domain.Administrator;

@Service
@Transactional
public class AdministratorService {

	// Managed Repository ---------------------------------------------------------
	@Autowired
	private AdministratorRepository	administratorRepository;

	// Supporting services ---------------------------------------------------------
	@Autowired
	private ActorService			actorService;

	@Autowired
	private UserAccountRepository	userAccountRepository;


	// Constructor methods ---------------------------------------------------------
	public AdministratorService() {
		super();
	}

	// Simple CRUD methods ---------------------------------------------------------

	public Administrator create() {

		final UserAccount userAccount = new UserAccount();
		final Administrator res = new Administrator();

		final Authority authority = new Authority();
		authority.setAuthority(Authority.ADMIN);

		Collection<Authority> authorities;

		authorities = userAccount.getAuthorities();
		authorities.add(authority);
		userAccount.setAuthorities(authorities);

		res.setUserAccount(userAccount);

		return res;
	}

	public Administrator save(final Administrator administrator) {
		Assert.notNull(administrator);
		if (administrator.getId() == 0) {
			final Md5PasswordEncoder encoder = new Md5PasswordEncoder();
			final UserAccount userAccount = administrator.getUserAccount();
			final String password = userAccount.getPassword();
			final String hashedPassword = encoder.encodePassword(password, null);
			userAccount.setPassword(hashedPassword);
			final UserAccount ua = this.userAccountRepository.save(userAccount);
			administrator.setUserAccount(ua);

		}
		this.actorService.checkAuth(Authority.ADMIN);
		final Administrator res = this.administratorRepository.save(administrator);
		return res;
	}

	public Administrator findOne(final int administratorId) {
		Assert.isTrue(administratorId != 0);
		Administrator result;

		result = this.administratorRepository.findOne(administratorId);
		Assert.notNull(result);

		return result;
	}

	public Collection<Administrator> findAll() {
		Collection<Administrator> result;

		result = this.administratorRepository.findAll();
		Assert.notNull(result);

		return result;
	}

	// Check if the actual user is an admin
	public void checkIfAdmin() {
		boolean res = false;

		Collection<Authority> authority;
		authority = LoginService.getPrincipal().getAuthorities();
		for (final Authority a : authority)
			if (a.getAuthority().equals(Authority.ADMIN))
				res = true;
		Assert.isTrue(res);
	}

	// Finds the actual admin
	public Administrator findByPrincipal() {
		Administrator result;
		UserAccount userAccount;

		userAccount = LoginService.getPrincipal();
		Assert.notNull(userAccount);

		result = this.administratorRepository.findAdministratorByUserAccount(userAccount.getId());
		Assert.notNull(result);

		return result;
	}

	public void checkAdministrator(final Administrator administrator) {
		Boolean result = true;

		if (administrator.getAddress() == null || administrator.getName() == null || administrator.getSurname() == null)
			result = false;

		Assert.isTrue(result);
	}

	// DASHBOARD -----------------

	public Double avgFeeConference() {
		Actor principal;

		// Principal must be an admin
		principal = this.actorService.findByPrincipal();
		Assert.isInstanceOf(Administrator.class, principal);

		return this.administratorRepository.avgFeePerConference();
	}

	public Integer maxFeeConference() {
		Actor principal;

		// Principal must be an admin
		principal = this.actorService.findByPrincipal();
		Assert.isInstanceOf(Administrator.class, principal);

		return this.administratorRepository.maxFeePerConference();
	}

	public Integer minFeeConference() {
		Actor principal;

		// Principal must be an admin
		principal = this.actorService.findByPrincipal();
		Assert.isInstanceOf(Administrator.class, principal);

		return this.administratorRepository.minFeePerConference();
	}

	public Double stddevFeeConference() {
		Actor principal;

		// Principal must be an admin
		principal = this.actorService.findByPrincipal();
		Assert.isInstanceOf(Administrator.class, principal);

		return this.administratorRepository.stddevFeePerConference();
	}

	public Double avgSubmissionsConference() {
		Actor principal;

		// Principal must be an admin
		principal = this.actorService.findByPrincipal();
		Assert.isInstanceOf(Administrator.class, principal);

		return this.administratorRepository.avgSubmissionsPerConference();
	}

	public Integer maxSubmissionsConference() {
		Actor principal;

		// Principal must be an admin
		principal = this.actorService.findByPrincipal();
		Assert.isInstanceOf(Administrator.class, principal);

		return this.administratorRepository.maxSubmissionsPerConference();
	}

	public Integer minSubmissionsConference() {
		Actor principal;

		// Principal must be an admin
		principal = this.actorService.findByPrincipal();
		Assert.isInstanceOf(Administrator.class, principal);

		return this.administratorRepository.minSubmissionsPerConference();
	}

	public Double stddevSubmissionsConference() {
		Actor principal;

		// Principal must be an admin
		principal = this.actorService.findByPrincipal();
		Assert.isInstanceOf(Administrator.class, principal);

		return this.administratorRepository.stddevSubmissionsPerConference();
	}

	public Double avgRegistrationsConference() {
		Actor principal;

		// Principal must be an admin
		principal = this.actorService.findByPrincipal();
		Assert.isInstanceOf(Administrator.class, principal);

		return this.administratorRepository.avgRegistrationsPerConference();
	}

	public Integer maxRegistrationsConference() {
		Actor principal;

		// Principal must be an admin
		principal = this.actorService.findByPrincipal();
		Assert.isInstanceOf(Administrator.class, principal);

		return this.administratorRepository.maxRegistrationsPerConference();
	}

	public Integer minRegistrationsConference() {
		Actor principal;

		// Principal must be an admin
		principal = this.actorService.findByPrincipal();
		Assert.isInstanceOf(Administrator.class, principal);

		return this.administratorRepository.minRegistrationsPerConference();
	}

	public Double stddevRegitrationsConference() {
		Actor principal;

		// Principal must be an admin
		principal = this.actorService.findByPrincipal();
		Assert.isInstanceOf(Administrator.class, principal);

		return this.administratorRepository.stddevRegistrationsPerConference();
	}

	public Double avgDaysConference() {
		Actor principal;

		// Principal must be an admin
		principal = this.actorService.findByPrincipal();
		Assert.isInstanceOf(Administrator.class, principal);

		return this.administratorRepository.avgDaysPerConference();
	}

	public Integer maxDaysConference() {
		Actor principal;

		// Principal must be an admin
		principal = this.actorService.findByPrincipal();
		Assert.isInstanceOf(Administrator.class, principal);

		return this.administratorRepository.maxDaysPerConference();
	}

	public Integer minDaysConference() {
		Actor principal;

		// Principal must be an admin
		principal = this.actorService.findByPrincipal();
		Assert.isInstanceOf(Administrator.class, principal);

		return this.administratorRepository.minDaysPerConference();
	}

	public Double stddevDaysConference() {
		Actor principal;

		// Principal must be an admin
		principal = this.actorService.findByPrincipal();
		Assert.isInstanceOf(Administrator.class, principal);

		return this.administratorRepository.stddevDaysPerConference();
	}

}
