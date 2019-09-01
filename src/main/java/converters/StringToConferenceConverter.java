
package converters;

import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import repositories.ConferenceRepository;
import domain.Conference;

@Component
@Transactional
public class StringToConferenceConverter extends StringToEntity<Conference, ConferenceRepository> {

}
