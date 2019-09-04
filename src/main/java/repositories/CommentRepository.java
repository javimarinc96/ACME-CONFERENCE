
package repositories;

import java.util.Collection;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import domain.Comment;

@Repository
public interface CommentRepository extends JpaRepository<Comment, Integer> {

	@Query("select c from Comment c join c.activity a where a.id=?1")
	Collection<Comment> findByActivity(int activityId);

	@Query("select c from Comment c where c.conference.id=?1")
	Collection<Comment> findByConference(int conferenceId);

}
