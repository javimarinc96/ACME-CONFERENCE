
package converters;

import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import domain.Presentation;

@Component
@Transactional
public class PresentationToStringConverter extends EntityToString<Presentation> {
}
