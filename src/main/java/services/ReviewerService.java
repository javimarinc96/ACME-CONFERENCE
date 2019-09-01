
package services;

import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.encoding.Md5PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.Assert;

import repositories.ReviewerRepository;
import security.Authority;
import security.LoginService;
import security.UserAccount;
import security.UserAccountRepository;
import domain.Reviewer;

@Service
@Transactional
public class ReviewerService {

	// Managed Repository ---------------------------------------------------------
	@Autowired
	private ReviewerRepository		reviewerRepository;

	// Supporting services ---------------------------------------------------------

	@Autowired
	private UserAccountRepository	userAccountRepository;


	// Constructor methods ---------------------------------------------------------
	public ReviewerService() {
		super();
	}

	// Simple CRUD methods ---------------------------------------------------------

	public Reviewer create() {

		final UserAccount userAccount = new UserAccount();
		final Reviewer res = new Reviewer();

		final Authority authority = new Authority();
		authority.setAuthority(Authority.REVIEWER);

		Collection<Authority> authorities;

		authorities = userAccount.getAuthorities();
		authorities.add(authority);
		userAccount.setAuthorities(authorities);

		res.setUserAccount(userAccount);

		return res;
	}

	public Reviewer save(final Reviewer reviewer) {
		Assert.notNull(reviewer);
		if (reviewer.getId() == 0) {
			final Md5PasswordEncoder encoder = new Md5PasswordEncoder();
			final UserAccount userAccount = reviewer.getUserAccount();
			final String password = userAccount.getPassword();
			final String hashedPassword = encoder.encodePassword(password, null);
			userAccount.setPassword(hashedPassword);
			final UserAccount ua = this.userAccountRepository.save(userAccount);
			reviewer.setUserAccount(ua);

		}
		final Reviewer res = this.reviewerRepository.save(reviewer);
		return res;
	}

	public Reviewer findOne(final int reviewerId) {
		Assert.isTrue(reviewerId != 0);
		Reviewer result;

		result = this.reviewerRepository.findOne(reviewerId);
		Assert.notNull(result);

		return result;
	}

	public Collection<Reviewer> findAll() {
		Collection<Reviewer> result;

		result = this.reviewerRepository.findAll();
		Assert.notNull(result);

		return result;
	}

	// Check if the actual user is an admin
	public void checkIfReviewer() {
		boolean res = false;

		Collection<Authority> authorities;
		authorities = LoginService.getPrincipal().getAuthorities();
		for (final Authority a : authorities)
			if (a.getAuthority().equals(Authority.REVIEWER))
				res = true;
		Assert.isTrue(res);
	}

	// Finds the actual reviewer
	public Reviewer findByPrincipal() {
		Reviewer result;
		UserAccount userAccount;

		userAccount = LoginService.getPrincipal();
		Assert.notNull(userAccount);

		result = this.reviewerRepository.findReviewerByUserAccount(userAccount.getId());
		Assert.notNull(result);

		return result;
	}

	public void checkReviewer(final Reviewer reviewer) {
		Boolean result = true;

		if (reviewer.getAddress() == null || reviewer.getName() == null || reviewer.getSurname() == null)
			result = false;

		Assert.isTrue(result);
	}

}
