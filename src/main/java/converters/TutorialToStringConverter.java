
package converters;

import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import domain.Tutorial;

@Component
@Transactional
public class TutorialToStringConverter extends EntityToString<Tutorial> {
}
