
package converters;

import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import repositories.SectionRepository;
import domain.Section;

@Component
@Transactional
public class StringToSectionConverter extends StringToEntity<Section, SectionRepository> {

}
