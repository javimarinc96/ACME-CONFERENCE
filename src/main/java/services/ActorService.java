
package services;

import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.Assert;

import repositories.ActorRepository;
import security.Authority;
import security.LoginService;
import security.UserAccount;
import domain.Actor;

@Service
@Transactional
public class ActorService {

	//Managed repositories--------------------------------------
	@Autowired
	private ActorRepository	actorRepository;


	//Supporting services--------------------------------------

	//Constructor-----------------------------------------------
	public ActorService() {
		super();
	}

	//Simple CRUD methods---------------------------------------
	public Actor save(final Actor actor) {
		Assert.notNull(actor);
		Assert.notNull(actor.getUserAccount());
		Actor res;
		res = this.actorRepository.save(actor);
		return res;
	}

	public Actor findOne(final int actorId) {
		Assert.notNull(actorId);
		final Actor actor = this.actorRepository.findOne(actorId);
		return actor;
	}

	public Collection<Actor> findAll() {
		final Collection<Actor> actors = this.actorRepository.findAll();
		return actors;
	}

	//Other methods---------------------------------------------
	public Actor findByPrincipal() {
		Actor result;
		UserAccount userAccount;

		try {
			userAccount = LoginService.getPrincipal();
			result = this.findByUserAccount(userAccount);
		} catch (final Throwable exc) {
			result = null;
		}
		return result;
	}

	public Actor findByUserAccount(final UserAccount userAccount) {
		Assert.notNull(userAccount);
		Actor res;
		res = this.actorRepository.findByUserAccountId(userAccount.getId());
		return res;
	}

	public Actor findByUserAccountUsername(final String userAccountUsername) {
		Actor res;
		res = this.actorRepository.findByUserAccountUsername(userAccountUsername);
		return res;
	}

	public Actor findByUserAccount() {
		Actor res = null;
		try {
			res = this.actorRepository.findByUserAccountId(LoginService.getPrincipal().getId());
		} catch (final IllegalArgumentException e) {

		}
		return res;
	}

	public void checkAuth(final String auth) {
		Assert.notNull(auth);
		final Authority a = new Authority();
		a.setAuthority(auth);
		Assert.isTrue(LoginService.getPrincipal().getAuthorities().contains(a));
	}

	public void checkAuth(final String... auths) {
		boolean check = false;
		final Authority a = new Authority();
		for (final String auth : auths) {
			a.setAuthority(auth);
			if (LoginService.getPrincipal().getAuthorities().contains(a)) {
				check = true;
				break;
			}
		}
		Assert.isTrue(check);
	}

}
