
package domain;

import javax.persistence.Access;
import javax.persistence.AccessType;
import javax.persistence.Entity;

import org.hibernate.validator.constraints.NotBlank;
import org.hibernate.validator.constraints.URL;

@Entity
@Access(AccessType.PROPERTY)
public class Paper extends DomainEntity {

	// Attributes

	private String	title;
	private String	authors;
	private String	summary;
	private String	document;


	// Getters & setters

	@NotBlank
	public String getTitle() {
		return this.title;
	}
	public void setTitle(final String title) {
		this.title = title;
	}

	@NotBlank
	public String getAuthors() {
		return this.authors;
	}

	public void setAuthors(final String authors) {
		this.authors = authors;
	}

	@NotBlank
	public String getSummary() {
		return this.summary;
	}
	public void setSummary(final String summary) {
		this.summary = summary;
	}

	@NotBlank
	@URL
	public String getDocument() {
		return this.document;
	}
	public void setDocument(final String document) {
		this.document = document;
	}

}
