
package services;

import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.encoding.Md5PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.Assert;

import repositories.AuthorRepository;
import security.Authority;
import security.LoginService;
import security.UserAccount;
import security.UserAccountRepository;
import domain.Author;

@Service
@Transactional
public class AuthorService {

	// Managed Repository ---------------------------------------------------------
	@Autowired
	private AuthorRepository		authorRepository;

	// Supporting services ---------------------------------------------------------

	@Autowired
	private UserAccountRepository	userAccountRepository;


	// Constructor methods ---------------------------------------------------------
	public AuthorService() {
		super();
	}

	// Simple CRUD methods ---------------------------------------------------------

	public Author create() {

		final UserAccount userAccount = new UserAccount();
		final Author res = new Author();

		final Authority authority = new Authority();
		authority.setAuthority(Authority.AUTHOR);

		Collection<Authority> authorities;

		authorities = userAccount.getAuthorities();
		authorities.add(authority);
		userAccount.setAuthorities(authorities);

		res.setUserAccount(userAccount);

		return res;
	}

	public Author save(final Author author) {
		Assert.notNull(author);
		if (author.getId() == 0) {
			final Md5PasswordEncoder encoder = new Md5PasswordEncoder();
			final UserAccount userAccount = author.getUserAccount();
			final String password = userAccount.getPassword();
			final String hashedPassword = encoder.encodePassword(password, null);
			userAccount.setPassword(hashedPassword);
			final UserAccount ua = this.userAccountRepository.save(userAccount);
			author.setUserAccount(ua);

		}
		final Author res = this.authorRepository.save(author);
		return res;
	}

	public Author findOne(final int authorId) {
		Assert.isTrue(authorId != 0);
		Author result;

		result = this.authorRepository.findOne(authorId);
		Assert.notNull(result);

		return result;
	}

	public Collection<Author> findAll() {
		Collection<Author> result;

		result = this.authorRepository.findAll();
		Assert.notNull(result);

		return result;
	}

	// Check if the actual user is an admin
	public void checkIfAuthor() {
		boolean res = false;

		Collection<Authority> authority;
		authority = LoginService.getPrincipal().getAuthorities();
		for (final Authority a : authority)
			if (a.getAuthority().equals(Authority.AUTHOR))
				res = true;
		Assert.isTrue(res);
	}

	// Finds the actual author
	public Author findByPrincipal() {
		Author result;
		UserAccount userAccount;

		userAccount = LoginService.getPrincipal();
		Assert.notNull(userAccount);

		result = this.authorRepository.findAuthorByUserAccount(userAccount.getId());
		Assert.notNull(result);

		return result;
	}

	public void checkAuthor(final Author author) {
		Boolean result = true;

		if (author.getAddress() == null || author.getName() == null || author.getSurname() == null)
			result = false;

		Assert.isTrue(result);
	}

}
