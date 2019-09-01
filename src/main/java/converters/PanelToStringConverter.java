
package converters;

import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import domain.Panel;

@Component
@Transactional
public class PanelToStringConverter extends EntityToString<Panel> {
}
