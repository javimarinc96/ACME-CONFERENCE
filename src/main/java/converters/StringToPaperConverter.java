
package converters;

import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import repositories.PaperRepository;
import domain.Paper;

@Component
@Transactional
public class StringToPaperConverter extends StringToEntity<Paper, PaperRepository> {

}
