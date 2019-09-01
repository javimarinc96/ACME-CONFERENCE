
package converters;

import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import repositories.PanelRepository;
import domain.Panel;

@Component
@Transactional
public class StringToPanelConverter extends StringToEntity<Panel, PanelRepository> {

}
