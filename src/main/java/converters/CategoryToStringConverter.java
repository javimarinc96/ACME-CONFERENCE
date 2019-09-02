
package converters;

import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import domain.Category;

@Component
@Transactional
public class CategoryToStringConverter extends EntityToString<Category> {
}
