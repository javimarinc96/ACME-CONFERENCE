
package services;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.Assert;

import repositories.MessageRepository;
import repositories.RegistrationRepository;
import repositories.SubmissionRepository;
import security.Authority;
import domain.Actor;
import domain.Author;
import domain.Message;

@Service
@Transactional
public class MessageService {

	//Managed repositories-------------------------------------------
	@Autowired
	private MessageRepository		messageRepository;

	//Supported services---------------------------------------------

	@Autowired
	private ActorService			actorService;

	@Autowired
	private AuthorService			authorService;

	@Autowired
	private SubmissionRepository	submissionRepository;

	@Autowired
	private RegistrationRepository	registrationRepository;


	//Constructor----------------------------------------------------
	public MessageService() {
		super();
	}

	//Simple CRUD methods--------------------------------------------
	public Message create() {
		final Message res = new Message();
		final Actor sender = this.actorService.findByPrincipal();

		Assert.notNull(sender);
		Assert.notNull(this.actorService.findOne(sender.getId()));
		final Date moment = new Date(System.currentTimeMillis() - 1000);

		res.setSender(sender);
		res.setMoment(moment);

		return res;
	}

	public Message findOne(final int messageId) {
		Assert.isTrue(messageId != 0);
		Message res;

		res = this.messageRepository.findOne(messageId);
		Assert.notNull(res);
		return res;
	}

	public Collection<Message> findAll() {
		Collection<Message> res;

		res = this.messageRepository.findAll();
		Assert.notNull(res);
		return res;
	}

	public Collection<Message> findByActor(final int actorId) {
		Collection<Message> res;

		Assert.notNull(actorId);

		res = this.messageRepository.findMessagesActor(actorId);
		Assert.notNull(res);
		return res;
	}

	public Message save(final Message message) {

		Assert.notNull(message);

		final Message sended;
		final Message receivedNotSaved = message;
		final Message received;

		final Actor sender = this.actorService.findByPrincipal();
		Assert.notNull(sender);
		Assert.notNull(this.actorService.findOne(sender.getId()));

		final Actor recipient = message.getRecipient();
		Assert.notNull(recipient);
		Assert.notNull(this.actorService.findOne(recipient.getId()));

		sended = this.messageRepository.save(message);

		received = this.messageRepository.save(receivedNotSaved);

		return sended;
	}

	public void delete(final Message message) {
		Assert.isTrue(message.getId() != 0);

		this.messageRepository.delete(message);

	}
	//Other methods--------------------------------------------------

	public void broadcastAll(final Message message) {
		Assert.notNull(message);
		final Actor sender = message.getSender();
		Assert.notNull(sender);
		this.actorService.checkAuth(Authority.ADMIN);

		Assert.isTrue(sender.equals(this.actorService.findByPrincipal()));

		final Collection<Actor> actors = this.actorService.findAll();
		actors.remove(sender);

		for (final Actor recipient : actors) {
			final Message m = message;
			m.setRecipient(recipient);
			this.save(m);
		}
	}

	public void broadcastAuthors(final Message message) {
		Assert.notNull(message);
		final Actor sender = message.getSender();
		Assert.notNull(sender);
		this.actorService.checkAuth(Authority.ADMIN);

		Assert.isTrue(sender.equals(this.actorService.findByPrincipal()));

		final Collection<Author> authors = this.authorService.findAll();

		for (final Author recipient : authors) {
			final Message m = message;
			m.setRecipient(recipient);
			this.save(m);
		}
	}

	public void broadcastRegisteredAuthors(final Message message) {
		Assert.notNull(message);
		final Actor sender = message.getSender();
		Assert.notNull(sender);
		this.actorService.checkAuth(Authority.ADMIN);

		Assert.isTrue(sender.equals(this.actorService.findByPrincipal()));

		List<Author> authors = new ArrayList<Author>();
		authors = (List<Author>) this.registrationRepository.getRegistrationAuthors();

		final Set<Author> hashSet = new HashSet<Author>(authors);
		authors.clear();
		authors.addAll(hashSet);

		for (final Author recipient : authors) {
			final Message m = message;
			m.setRecipient(recipient);
			this.save(m);
		}

	}

	public void broadcastSubmissionAuthors(final Message message) {
		Assert.notNull(message);
		final Actor sender = message.getSender();
		Assert.notNull(sender);
		this.actorService.checkAuth(Authority.ADMIN);

		Assert.isTrue(sender.equals(this.actorService.findByPrincipal()));

		List<Author> authors = new ArrayList<Author>();
		authors = (List<Author>) this.submissionRepository.getSubmissionAuthors();

		final Set<Author> hashSet = new HashSet<Author>(authors);
		authors.clear();
		authors.addAll(hashSet);

		for (final Author recipient : authors) {
			final Message m = message;
			m.setRecipient(recipient);
			this.save(m);
		}

	}

}
