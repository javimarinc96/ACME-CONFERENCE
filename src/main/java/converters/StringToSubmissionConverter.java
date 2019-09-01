
package converters;

import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import repositories.SubmissionRepository;
import domain.Submission;

@Component
@Transactional
public class StringToSubmissionConverter extends StringToEntity<Submission, SubmissionRepository> {

}
