
package services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.Assert;

import repositories.CustomizationRepository;
import domain.Actor;
import domain.Administrator;
import domain.Customization;

@Service
@Transactional
public class CustomizationService {

	// Manage Repository
	@Autowired
	private CustomizationRepository	customizationRepository;

	// Supporting services
	@Autowired
	private ActorService			actorService;


	// CRUD methods
	public Customization getCustomization() {
		final Customization result = this.customizationRepository.findAll().get(0);

		Assert.notNull(result);

		return result;
	}

	public Customization save(final Customization config) {
		Assert.notNull(config);

		Actor principal;

		// Principal must be a Administrator
		principal = this.actorService.findByPrincipal();
		Assert.isInstanceOf(Administrator.class, principal);

		return this.customizationRepository.save(config);
	}

	public void flush() {

		this.customizationRepository.flush();
	}

	// Other business methods ---------------------------------------------------------

}
