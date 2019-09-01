
package repositories;

import java.util.Collection;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import domain.Author;
import domain.Submission;

@Repository
public interface SubmissionRepository extends JpaRepository<Submission, Integer> {

	@Query("select s from Submission s where s.author.id = ?1")
	Collection<Submission> findByAuthorId(int authorId);

	@Query("select s.author from Submission s")
	Collection<Author> getSubmissionAuthors();

	@Query("select s from Submission s where s.conference.id = ?1")
	Collection<Submission> findByConferenceId(int conferenceId);

	@Query("select s from Submission s where s.conference.id = ?1 and s.status = ?2 ")
	Collection<Submission> findStatusByConferenceId(int conferenceId, String status);

}
