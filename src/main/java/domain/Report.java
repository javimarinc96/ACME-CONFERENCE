
package domain;

import javax.persistence.Access;
import javax.persistence.AccessType;
import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;

import org.hibernate.validator.constraints.NotBlank;
import org.hibernate.validator.constraints.Range;

@Entity
@Access(AccessType.PROPERTY)
public class Report extends DomainEntity {

	// Attributes

	private Double	originalityScore;
	private Double	qualityScore;
	private Double	readabilityScore;
	private String	decision;
	private String	comments;


	// Getters & setters

	@NotNull
	@Range(min = 0, max = 10)
	public Double getOriginalityScore() {
		return this.originalityScore;
	}

	public void setOriginalityScore(final Double originalityScore) {
		this.originalityScore = originalityScore;
	}

	@NotNull
	@Range(min = 0, max = 10)
	public Double getQualityScore() {
		return this.qualityScore;
	}
	public void setQualityScore(final Double qualityScore) {
		this.qualityScore = qualityScore;
	}

	@NotNull
	@Range(min = 0, max = 10)
	public Double getReadabilityScore() {
		return this.readabilityScore;
	}
	public void setReadabilityScore(final Double readabilityScore) {
		this.readabilityScore = readabilityScore;
	}

	@Pattern(regexp = "^(REJECT|BORDER-LINE|ACCEPT)$")
	@NotBlank
	public String getDecision() {
		return this.decision;
	}

	public void setDecision(final String decision) {
		this.decision = decision;
	}

	public String getComments() {
		return this.comments;
	}

	public void setComments(final String comments) {
		this.comments = comments;
	}


	// Relationships ----------------------------------------------------------

	private Reviewer	reviewer;
	private Submission	submission;


	@Valid
	@NotNull
	@ManyToOne(optional = false)
	public Reviewer getReviewer() {
		return this.reviewer;
	}

	public void setReviewer(final Reviewer reviewer) {
		this.reviewer = reviewer;
	}

	@Valid
	@NotNull
	@ManyToOne(optional = false)
	public Submission getSubmission() {
		return this.submission;
	}

	public void setSubmission(final Submission submission) {
		this.submission = submission;
	}

}
