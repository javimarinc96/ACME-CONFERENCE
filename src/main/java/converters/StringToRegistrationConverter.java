
package converters;

import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import repositories.RegistrationRepository;
import domain.Registration;

@Component
@Transactional
public class StringToRegistrationConverter extends StringToEntity<Registration, RegistrationRepository> {

}
