
package converters;

import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import domain.Section;

@Component
@Transactional
public class SectionToStringConverter extends EntityToString<Section> {
}
