
package domain;

import java.util.Collection;
import java.util.Date;

import javax.persistence.Access;
import javax.persistence.AccessType;
import javax.persistence.Entity;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.Min;

import org.springframework.format.annotation.DateTimeFormat;

@Entity
@Access(AccessType.PROPERTY)
public class Finder extends DomainEntity {

	// Attributes
	private String					keyWord;
	private Date					startDate;
	private Date					endDate;
	private Double					maximumFee;
	private Category				category;

	private Collection<Conference>	conferences;


	// Getters & setters

	public String getKeyWord() {
		return this.keyWord;
	}

	public void setKeyWord(final String keyWord) {
		this.keyWord = keyWord;
	}

	@Temporal(TemporalType.TIMESTAMP)
	@DateTimeFormat(pattern = "dd/MM/yyyy HH:mm")
	public Date getStartDate() {
		return this.startDate;
	}

	public void setStartDate(final Date startDate) {
		this.startDate = startDate;
	}

	@Temporal(TemporalType.TIMESTAMP)
	@DateTimeFormat(pattern = "dd/MM/yyyy HH:mm")
	public Date getEndDate() {
		return this.endDate;
	}

	public void setEndDate(final Date endDate) {
		this.endDate = endDate;
	}

	@ManyToOne
	public Category getCategory() {
		return this.category;
	}

	public void setCategory(final Category category) {
		this.category = category;
	}

	@Min(0)
	public Double getMaximumFee() {
		return this.maximumFee;
	}

	public void setMaximumFee(final Double maximumFee) {
		this.maximumFee = maximumFee;
	}

	// Relationships ----------------------------------------------------------

	@ManyToMany
	public Collection<Conference> getConferences() {
		return this.conferences;
	}

	public void setConferences(final Collection<Conference> conferences) {
		this.conferences = conferences;
	}

}
