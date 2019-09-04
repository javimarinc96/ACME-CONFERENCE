
package services;

import java.util.Collection;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

import org.apache.commons.lang.RandomStringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.Assert;

import repositories.PaperRepository;
import repositories.ReportRepository;
import repositories.SubmissionRepository;
import domain.Actor;
import domain.Administrator;
import domain.Author;
import domain.Message;
import domain.Paper;
import domain.Report;
import domain.Reviewer;
import domain.Submission;
import domain.Topic;

@Service
@Transactional
public class SubmissionService {

	//Managed repository ------------------------------------------------

	@Autowired
	private SubmissionRepository	submissionRepository;

	@Autowired
	private ReportRepository		reportRepository;

	@Autowired
	private PaperRepository			paperRepository;

	//Services

	@Autowired
	private AuthorService			authorService;

	@Autowired
	private ReviewerService			reviewerService;

	@Autowired
	private ActorService			actorService;

	@Autowired
	private MessageService			messageService;

	@Autowired
	private TopicService			topicService;


	// Constructor methods ---------------------------------------------------------
	public SubmissionService() {
		super();
	}

	//Simple CRUD methods ------------------------

	public Submission create() {

		Actor principal;
		// Principal must be an auditor
		principal = this.actorService.findByPrincipal();
		Assert.isInstanceOf(Author.class, principal);
		final Author principalAuthor = this.authorService.findByPrincipal();

		final Submission s = new Submission();

		s.setTicker(this.generateTicker());
		s.setAssignment(false);
		s.setAuthor(principalAuthor);
		s.setMoment(new Date());
		s.setStatus("UNDER-REVIEW");
		s.setPaper(new Paper());

		return s;
	}

	public Submission save(final Submission s) {
		Assert.notNull(s);

		Actor principal;

		// Principal must be an Admin
		principal = this.actorService.findByPrincipal();
		Assert.isInstanceOf(Author.class, principal);

		if (s.getId() == 0) {
			final Paper p = s.getPaper();
			final Paper saved = this.paperRepository.save(p);
			s.setPaper(saved);
		}

		if (s.getCameraReady() != null && s.getCameraReady().getId() == 0) {
			final Paper camera = s.getCameraReady();
			final Paper saved = this.paperRepository.save(camera);
			s.setCameraReady(saved);
		}

		Submission result;
		result = this.submissionRepository.save(s);

		return result;
	}
	public void delete(final Submission s) {

		Assert.notNull(s);
		Assert.isTrue(s.getId() != 0);

		Actor principal;

		// Principal must be an Admin
		principal = this.actorService.findByPrincipal();
		Assert.isInstanceOf(Administrator.class, principal);

		final Submission result = this.submissionRepository.findOne(s.getId());
		Assert.notNull(result);

		this.submissionRepository.delete(result);

	}

	public Submission findOne(final int SubmissionId) {
		Assert.isTrue(SubmissionId != 0);
		Submission result;

		result = this.submissionRepository.findOne(SubmissionId);
		Assert.notNull(result);

		return result;
	}

	public Collection<Submission> findAll() {

		Collection<Submission> result;

		result = this.submissionRepository.findAll();
		Assert.notNull(result);

		return result;
	}

	public Collection<Submission> findByAuthor(final int authorId) {

		Assert.notNull(authorId);
		Collection<Submission> result;

		// Principal must be an Author
		final Actor principal = this.actorService.findByPrincipal();
		Assert.isInstanceOf(Author.class, principal);

		result = this.submissionRepository.findByAuthorId(authorId);
		Assert.notNull(result);

		return result;
	}

	public Collection<Submission> findStatusByConferenceId(final int conferenceId, final String status) {

		Collection<Submission> result;

		Actor principal;

		// Principal must be an Admin
		principal = this.actorService.findByPrincipal();
		Assert.isInstanceOf(Administrator.class, principal);

		result = this.submissionRepository.findStatusByConferenceId(conferenceId, status);
		Assert.notNull(result);

		return result;
	}

	public String generateTicker() {

		String result = "";

		final Actor principal = this.actorService.findByPrincipal();
		Assert.isInstanceOf(Author.class, principal);
		final Author prin = this.authorService.findByPrincipal();

		final String name = prin.getName();
		final String middleName = prin.getMiddleName();
		final String surName = prin.getSurname();

		if (middleName == null || middleName.equals(""))
			result = result.concat(name.substring(0, 1)).concat("X").concat(surName.substring(0, 1));
		else
			result = result.concat(name.substring(0, 1)).concat(middleName.substring(0, 1)).concat(surName.substring(0, 1));

		final String tickerAlphanumeric = RandomStringUtils.randomAlphanumeric(4).toUpperCase();
		result = result.concat("-").concat(tickerAlphanumeric);

		return result;
	}

	public void makeDecission(final int submissionId) {
		Assert.isTrue(submissionId != 0);
		final Submission s = this.findOne(submissionId);
		Assert.notNull(s);

		Actor principal;

		// Principal must be an Admin
		principal = this.actorService.findByPrincipal();
		Assert.isInstanceOf(Administrator.class, principal);

		Assert.isTrue(s.getStatus().equals("UNDER-REVIEW"));
		Assert.isTrue(s.getConference().getSubmissionDeadline().before(new Date()));
		Assert.isTrue(s.getConference().getNotificationDeadline().after(new Date()));

		final Collection<Report> accept = this.reportRepository.findStatusBySubmissionId(submissionId, "ACCEPT");
		final Collection<Report> borderline = this.reportRepository.findStatusBySubmissionId(submissionId, "BORDER-LINE");
		final Collection<Report> reject = this.reportRepository.findStatusBySubmissionId(submissionId, "REJECT");

		final Message notification = new Message();
		final Topic t = this.topicService.findByEnglishName("OTHER");

		notification.setSubject("A decision has been made about your submission");
		notification.setMoment(new Date());
		notification.setTopic(t);
		notification.setSender(principal);
		notification.setRecipient(s.getAuthor());

		if (accept.size() > reject.size()) {
			s.setStatus("ACCEPTED");
			notification.setBody("Your submission to conference" + s.getConference().getTitle() + " has been accepted.");
		} else if (reject.size() > accept.size()) {
			s.setStatus("REJECTED");
			notification.setBody("Your submission to conference" + s.getConference().getTitle() + " has been rejected.");
		} else if (reject.size() == accept.size()) {
			s.setStatus("ACCEPTED");
			notification.setBody("Your submission to conference" + s.getConference().getTitle() + " has been accepted.");
		}

		this.messageService.save(notification);

		this.submissionRepository.save(s);
	}

	public void assignReviewers(final int submissionId) {

		// Principal must be an Admin
		final Actor principal = this.actorService.findByPrincipal();
		Assert.isInstanceOf(Administrator.class, principal);

		final Submission s = this.findOne(submissionId);

		Assert.isTrue(s.getStatus().equals("UNDER-REVIEW"));
		Assert.isTrue(s.getConference().getNotificationDeadline().after(new Date()));

		final String summary = s.getConference().getSummary().toLowerCase();
		final String title = s.getConference().getTitle().toLowerCase();

		final Collection<Reviewer> all = this.reviewerService.findAll();

		for (final Reviewer r : all) {
			
			final String[] words = r.getKeywords().split(",");
			
			for (final String word : words){
				if (title.contains(word.toLowerCase()) || summary.contains(word.toLowerCase())) {
					final Collection<Submission> subs = r.getSubmissions();
					subs.add(s);
					r.setSubmissions(subs);
					this.reviewerService.save(r);
				}
			}
	        Set<Submission> hashSet = new HashSet<Submission>(r.getSubmissions());
	        r.getSubmissions().clear();
	        r.getSubmissions().addAll(hashSet);
		}

		s.setAssignment(true);
		this.submissionRepository.save(s);
	}
}
