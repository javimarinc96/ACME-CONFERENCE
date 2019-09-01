
package converters;

import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import repositories.TopicRepository;
import domain.Topic;

@Component
@Transactional
public class StringToTopicConverter extends StringToEntity<Topic, TopicRepository> {

}
