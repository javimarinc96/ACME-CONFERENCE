
package repositories;

import java.util.Collection;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import domain.Message;
import domain.Topic;

@Repository
public interface TopicRepository extends JpaRepository<Topic, Integer> {

	@Query("select m from Message m where m.topic.id = ?1")
	Collection<Message> findMessagesByTopic(int topicId);

	@Query("select t from Topic t where t.englishName = ?1")
	Topic findTopicbyEnglishName(String englishName);

}
