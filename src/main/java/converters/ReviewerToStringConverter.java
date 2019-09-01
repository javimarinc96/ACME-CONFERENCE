
package converters;

import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import domain.Reviewer;

@Component
@Transactional
public class ReviewerToStringConverter extends EntityToString<Reviewer> {
}
