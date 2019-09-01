
package converters;

import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import repositories.CustomizationRepository;
import domain.Customization;

@Component
@Transactional
public class StringToCustomizationConverter extends StringToEntity<Customization, CustomizationRepository> {

}
