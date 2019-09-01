
package converters;

import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import repositories.ReviewerRepository;
import domain.Reviewer;

@Component
@Transactional
public class StringToReviewerConverter extends StringToEntity<Reviewer, ReviewerRepository> {

}
