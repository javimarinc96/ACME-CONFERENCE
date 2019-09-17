
package converters;

import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import domain.Domp;

@Component
@Transactional
public class DompToStringConverter extends EntityToString<Domp> {

}
