
package converters;

import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import domain.Activity;

@Component
@Transactional
public class ActivityToStringConverter extends EntityToString<Activity> {
}
