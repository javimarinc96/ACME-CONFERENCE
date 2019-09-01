
package converters;

import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import domain.Submission;

@Component
@Transactional
public class SubmissionToStringConverter extends EntityToString<Submission> {
}
