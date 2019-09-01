
package converters;

import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import domain.Customization;

@Component
@Transactional
public class CustomizationToStringConverter extends EntityToString<Customization> {
}
