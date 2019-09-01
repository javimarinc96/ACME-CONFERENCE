
package converters;

import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import domain.Author;

@Component
@Transactional
public class AuthorToStringConverter extends EntityToString<Author> {
}
