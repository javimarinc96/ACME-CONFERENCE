
package domain;

import javax.persistence.Access;
import javax.persistence.AccessType;
import javax.persistence.Entity;
import javax.persistence.ManyToOne;

@Entity
@Access(AccessType.PROPERTY)
public class Commentable extends DomainEntity {

	// Attibutes

	private Activity	activity;
	private Conference	conference;


	// Getters & setters

	@ManyToOne(optional = true)
	public Activity getActivity() {
		return this.activity;
	}
	public void setActivity(final Activity activity) {
		this.activity = activity;
	}

	@ManyToOne(optional = true)
	public Conference getConference() {
		return this.conference;
	}
	public void setConference(final Conference conference) {
		this.conference = conference;
	}

}
