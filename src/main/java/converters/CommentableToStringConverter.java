
package converters;

import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import domain.Commentable;

@Component
@Transactional
public class CommentableToStringConverter extends EntityToString<Commentable> {
}
