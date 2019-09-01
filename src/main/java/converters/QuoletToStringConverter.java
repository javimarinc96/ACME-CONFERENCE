
package converters;

import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import domain.Quolet;

@Component
@Transactional
public class QuoletToStringConverter extends EntityToString<Quolet> {

}
