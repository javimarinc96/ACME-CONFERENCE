
package domain;

import java.util.Date;

import javax.persistence.Access;
import javax.persistence.AccessType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;

import org.hibernate.validator.constraints.NotBlank;
import org.springframework.format.annotation.DateTimeFormat;

@Entity
@Access(AccessType.PROPERTY)
public class Submission extends DomainEntity {

	// Attributes

	private String	ticker;
	private Date	moment;
	private Paper	paper;
	private Paper	cameraReady;
	private String	status;
	private Boolean	assignment;


	// Getters & setters

	@Column(unique = true)
	@NotBlank
	@Pattern(regexp = "^[A-Z]{3}\\-[0-9A-Z]{4}$")
	public String getTicker() {
		return this.ticker;
	}

	public void setTicker(final String ticker) {
		this.ticker = ticker;
	}

	@NotNull
	@Temporal(TemporalType.TIMESTAMP)
	@DateTimeFormat(pattern = "dd/MM/yyyy HH:mm")
	public Date getMoment() {
		return this.moment;
	}

	public void setMoment(final Date moment) {
		this.moment = moment;
	}

	@Valid
	@NotNull
	@OneToOne(optional = false)
	public Paper getPaper() {
		return this.paper;
	}

	public void setPaper(final Paper paper) {
		this.paper = paper;
	}

	@Valid
	@OneToOne
	public Paper getCameraReady() {
		return this.cameraReady;
	}

	public void setCameraReady(final Paper cameraReady) {
		this.cameraReady = cameraReady;
	}

	@NotBlank
	@Pattern(regexp = "^(UNDER-REVIEW|REJECTED|ACCEPTED)$")
	public String getStatus() {
		return this.status;
	}

	public void setStatus(final String status) {
		this.status = status;
	}

	public Boolean getAssignment() {
		return this.assignment;
	}

	public void setAssignment(final Boolean assignment) {
		this.assignment = assignment;
	}


	// Relationships ----------------------------------------------------------

	private Author		author;
	private Conference	conference;


	@ManyToOne(optional = false)
	@NotNull
	@Valid
	public Author getAuthor() {
		return this.author;
	}

	public void setAuthor(final Author author) {
		this.author = author;
	}

	@ManyToOne(optional = false)
	@NotNull
	@Valid
	public Conference getConference() {
		return this.conference;
	}

	public void setConference(final Conference conference) {
		this.conference = conference;
	}

}
