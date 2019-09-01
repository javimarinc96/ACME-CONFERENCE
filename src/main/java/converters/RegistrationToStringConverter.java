
package converters;

import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import domain.Registration;

@Component
@Transactional
public class RegistrationToStringConverter extends EntityToString<Registration> {
}
