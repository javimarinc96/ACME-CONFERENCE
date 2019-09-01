
package domain;

import java.util.Collection;

import javax.persistence.Access;
import javax.persistence.AccessType;
import javax.persistence.Entity;
import javax.persistence.ManyToMany;

import org.hibernate.validator.constraints.NotBlank;

@Entity
@Access(AccessType.PROPERTY)
public class Reviewer extends Actor {

	// Attributes

	private String					keywords;
	private Collection<Submission>	submissions;


	@NotBlank
	public String getKeywords() {
		return this.keywords;
	}

	public void setKeywords(final String keywords) {
		this.keywords = keywords;
	}

	@ManyToMany
	public Collection<Submission> getSubmissions() {
		return this.submissions;
	}

	public void setSubmissions(final Collection<Submission> submissions) {
		this.submissions = submissions;
	}

	// Getters & setters

	// Relationships ----------------------------------------------------------

}
