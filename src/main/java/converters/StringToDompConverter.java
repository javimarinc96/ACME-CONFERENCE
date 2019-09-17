
package converters;

import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import repositories.DompRepository;
import domain.Domp;

@Component
@Transactional
public class StringToDompConverter extends StringToEntity<Domp, DompRepository> {

}
