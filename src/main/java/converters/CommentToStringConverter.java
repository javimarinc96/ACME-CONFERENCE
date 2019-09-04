
package converters;

import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import domain.Comment;

@Component
@Transactional
public class CommentToStringConverter extends EntityToString<Comment> {
}
