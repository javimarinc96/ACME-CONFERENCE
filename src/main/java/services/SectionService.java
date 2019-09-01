
package services;

import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.Assert;

import repositories.SectionRepository;
import security.Authority;
import domain.Section;
import domain.Tutorial;

@Service
@Transactional
public class SectionService {

	//Managed repository ------------------------------------------------

	@Autowired
	private SectionRepository	sectionRepository;

	//Supported services ------------------------------------------------
	@Autowired
	private ActorService		actorService;

	@Autowired
	private TutorialService		tutorialService;


	// Constructor methods ---------------------------------------------------------
	public SectionService() {
		super();
	}

	//Simple CRUD methods ------------------------

	public Section create() {

		this.actorService.checkAuth(Authority.ADMIN);

		final Section s = new Section();

		return s;
	}

	public Section save(final Section s, final int tutorialId) {
		Assert.notNull(s);

		this.actorService.checkAuth(Authority.ADMIN);

		final Tutorial t = this.tutorialService.findOne(tutorialId);

		final Section section;

		section = this.sectionRepository.save(s);
		Assert.notNull(t);

		t.getSections().add(section);

		this.tutorialService.saveEdit(t);

		return section;
	}

	public void delete(final Section s) {

		Assert.notNull(s);
		Assert.isTrue(s.getId() != 0);

		this.actorService.checkAuth(Authority.ADMIN);

		final Tutorial t = this.tutorialService.findTutorialBySection(s.getId());

		t.getSections().remove(s);

		this.tutorialService.saveEdit(t);

		final Section result = this.sectionRepository.findOne(s.getId());
		Assert.notNull(result);

		this.sectionRepository.delete(result);

	}

	public Section findOne(final int sectionId) {
		Assert.isTrue(sectionId != 0);
		Section result;

		result = this.sectionRepository.findOne(sectionId);
		Assert.notNull(result);

		return result;
	}

	public Collection<Section> findAll() {

		Collection<Section> result;

		result = this.sectionRepository.findAll();
		Assert.notNull(result);

		return result;
	}

}
