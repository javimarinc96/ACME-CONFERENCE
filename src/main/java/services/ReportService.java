
package services;

import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.Assert;

import repositories.ReportRepository;
import domain.Actor;
import domain.Author;
import domain.Report;
import domain.Reviewer;

@Service
@Transactional
public class ReportService {

	//Managed repository ------------------------------------------------

	@Autowired
	private ReportRepository	reportRepository;

	//Services

	@Autowired
	private ReviewerService		reviewerService;

	@Autowired
	private ActorService		actorService;


	// Constructor methods ---------------------------------------------------------
	public ReportService() {
		super();
	}

	//Simple CRUD methods ------------------------

	public Report create() {

		Actor principal;
		// Principal must be an auditor
		principal = this.actorService.findByPrincipal();
		Assert.isInstanceOf(Reviewer.class, principal);
		final Reviewer principalReviewer = this.reviewerService.findByPrincipal();

		final Report s = new Report();

		s.setReviewer(principalReviewer);
		s.setComments("");
		s.setOriginalityScore(0.0);
		s.setQualityScore(0.0);
		s.setReadabilityScore(0.0);

		return s;
	}

	public Report save(final Report s) {
		Assert.notNull(s);

		Actor principal;

		// Principal must be an Admin
		principal = this.actorService.findByPrincipal();
		Assert.isInstanceOf(Reviewer.class, principal);

		Report result;
		result = this.reportRepository.save(s);

		return result;
	}

	public void delete(final Report s) {

		Assert.notNull(s);
		Assert.isTrue(s.getId() != 0);

		Actor principal;

		// Principal must be an Admin
		principal = this.actorService.findByPrincipal();
		Assert.isInstanceOf(Reviewer.class, principal);

		Assert.isTrue(s.getReviewer().equals(principal));

		final Report result = this.reportRepository.findOne(s.getId());
		Assert.notNull(result);

		this.reportRepository.delete(result);

	}

	public Report findOne(final int ReportId) {
		Assert.isTrue(ReportId != 0);
		Report result;

		result = this.reportRepository.findOne(ReportId);
		Assert.notNull(result);

		return result;
	}

	public Collection<Report> findAll() {

		Collection<Report> result;

		result = this.reportRepository.findAll();
		Assert.notNull(result);

		return result;
	}

	public Collection<Report> findBySubmission(final int submissionId) {

		Collection<Report> result;

		Actor principal;

		// Principal must be an Admin
		principal = this.actorService.findByPrincipal();
		Assert.isInstanceOf(Author.class, principal);

		result = this.reportRepository.findBySubmissionId(submissionId);
		Assert.notNull(result);

		return result;
	}

	public Collection<Report> findByReviewer(final int reviewerId) {

		Collection<Report> result;

		Actor principal;

		// Principal must be an Admin
		principal = this.actorService.findByPrincipal();
		Assert.isInstanceOf(Reviewer.class, principal);

		result = this.reportRepository.findByReviewerId(reviewerId);
		Assert.notNull(result);

		return result;
	}

}
