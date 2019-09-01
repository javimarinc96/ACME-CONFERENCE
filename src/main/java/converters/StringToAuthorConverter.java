
package converters;

import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import repositories.AuthorRepository;
import domain.Author;

@Component
@Transactional
public class StringToAuthorConverter extends StringToEntity<Author, AuthorRepository> {

}
