
package converters;

import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import repositories.CommentableRepository;
import domain.Commentable;

@Component
@Transactional
public class StringToCommentableConverter extends StringToEntity<Commentable, CommentableRepository> {

}
