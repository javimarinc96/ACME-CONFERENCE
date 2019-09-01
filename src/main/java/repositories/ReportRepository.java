
package repositories;

import java.util.Collection;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import domain.Report;

@Repository
public interface ReportRepository extends JpaRepository<Report, Integer> {

	@Query("select r from Report r where r.submission.id = ?1")
	Collection<Report> findBySubmissionId(int submissionId);

	@Query("select r from Report r where r.reviewer.id = ?1")
	Collection<Report> findByReviewerId(int reviewerId);

	@Query("select r from Report r where r.submission.id = ?1 and r.decision = ?2")
	Collection<Report> findStatusBySubmissionId(int submissionId, String status);

}
