
package converters;

import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import domain.Conference;

@Component
@Transactional
public class ConferenceToStringConverter extends EntityToString<Conference> {
}
