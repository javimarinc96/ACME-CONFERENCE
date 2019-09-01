
package domain;

import javax.persistence.Access;
import javax.persistence.AccessType;
import javax.persistence.Entity;
import javax.persistence.OneToOne;
import javax.validation.Valid;
import javax.validation.constraints.NotNull;

@Entity
@Access(AccessType.PROPERTY)
public class Presentation extends Activity {

	// Attributes

	private Paper	cameraReady;


	// Getters & setters

	@Valid
	@NotNull
	@OneToOne(optional = false)
	public Paper getCameraReady() {
		return this.cameraReady;
	}

	public void setCameraReady(final Paper cameraReady) {
		this.cameraReady = cameraReady;
	}

	// Relationships ----------------------------------------------------------

}
