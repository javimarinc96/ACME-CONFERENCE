
package converters;

import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import repositories.QuoletRepository;
import domain.Quolet;

@Component
@Transactional
public class StringToQuoletConverter extends StringToEntity<Quolet, QuoletRepository> {

}
