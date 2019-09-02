
package converters;

import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import domain.Finder;

@Component
@Transactional
public class FinderToStringConverter extends EntityToString<Finder> {
}
