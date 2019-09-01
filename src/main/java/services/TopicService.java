
package services;

import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.Assert;

import repositories.TopicRepository;
import domain.Actor;
import domain.Administrator;
import domain.Topic;

@Service
@Transactional
public class TopicService {

	// Manage Repository
	@Autowired
	private TopicRepository	topicRepository;

	// Supporting services
	@Autowired
	private ActorService	actorService;


	/**
	 * CRUD methods
	 */
	public Topic create() {

		final Topic result = new Topic();

		return result;
	}

	public Topic findOne(final int id) {
		final Topic result = this.topicRepository.findOne(id);
		Assert.notNull(result);

		return result;
	}

	public Collection<Topic> findAll() {
		final Collection<Topic> result = this.topicRepository.findAll();

		Assert.notNull(result);

		return result;
	}

	public Topic findByEnglishName(final String englishName) {

		final Topic result = this.topicRepository.findTopicbyEnglishName(englishName);

		Assert.notNull(result);

		return result;
	}

	public void flush() {
		this.topicRepository.flush();
	}

	public Topic save(final Topic topic) {
		Assert.notNull(topic);
		Actor principal;

		// Principal must be an Admin
		principal = this.actorService.findByPrincipal();
		Assert.isInstanceOf(Administrator.class, principal);

		return this.topicRepository.save(topic);
	}

	public void delete(final Topic topic) {
		Assert.notNull(topic);
		Actor principal;

		// Principal must be an Admin
		principal = this.actorService.findByPrincipal();
		Assert.isInstanceOf(Administrator.class, principal);

		Assert.isTrue(topic.getId() != 0);

		// Topic must not be in use to be deleted
		Assert.isTrue(this.topicRepository.findMessagesByTopic(topic.getId()).isEmpty());

		this.topicRepository.delete(topic);
	}

}
