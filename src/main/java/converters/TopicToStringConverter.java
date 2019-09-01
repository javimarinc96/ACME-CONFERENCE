
package converters;

import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import domain.Topic;

@Component
@Transactional
public class TopicToStringConverter extends EntityToString<Topic> {
}
