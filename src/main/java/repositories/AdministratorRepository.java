
package repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import domain.Administrator;

@Repository
public interface AdministratorRepository extends JpaRepository<Administrator, Integer> {

	@Query("select a from Administrator a where a.userAccount.id = ?1")
	Administrator findAdministratorByUserAccount(int userAccountId);

	@Query("select avg(c.fee) from Conference c")
	Double avgFeePerConference();

	@Query("select max(c.fee) from Conference c")
	Integer maxFeePerConference();

	@Query("select min(c.fee) from Conference c")
	Integer minFeePerConference();

	@Query("select stddev(c.fee) from Conference c")
	Double stddevFeePerConference();

	@Query("select avg(1.0*(select count(s) from Submission s where s.conference.id = c.id)) from Conference c")
	Double avgSubmissionsPerConference();

	@Query("select max(1.0*(select count(s) from Submission s where s.conference.id = c.id)) from Conference c")
	Integer maxSubmissionsPerConference();

	@Query("select min(1.0*(select count(s) from Submission s where s.conference.id = c.id)) from Conference c")
	Integer minSubmissionsPerConference();

	@Query("select stddev(1.0*(select count(s) from Submission s where s.conference.id = c.id)) from Conference c")
	Double stddevSubmissionsPerConference();

	@Query("select avg(1.0*(select count(r) from Registration r where r.conference.id = c.id)) from Conference c")
	Double avgRegistrationsPerConference();

	@Query("select max(1.0*(select count(r) from Registration r where r.conference.id = c.id)) from Conference c")
	Integer maxRegistrationsPerConference();

	@Query("select min(1.0*(select count(r) from Registration r where r.conference.id = c.id)) from Conference c")
	Integer minRegistrationsPerConference();

	@Query("select stddev(1.0*(select count(r) from Registration r where r.conference.id = c.id)) from Conference c")
	Double stddevRegistrationsPerConference();

	@Query("select avg(c.endDate-c.startDate) from Conference c")
	Double avgDaysPerConference();

	@Query("select max(c.endDate-c.startDate) from Conference c")
	Integer maxDaysPerConference();

	@Query("select min(c.endDate-c.startDate) from Conference c")
	Integer minDaysPerConference();

	@Query("select stddev(c.endDate-c.startDate) from Conference c")
	Double stddevDaysPerConference();

	@Query("select avg(1.0*(select count(c) from Conference c where c.category.id = cat.id)) from Category cat")
	Double avgConferenceCategory();

	@Query("select max(1.0*(select count(c) from Conference c where c.category.id = cat.id)) from Category cat")
	Integer maxConferenceCategory();

	@Query("select min(1.0*(select count(c) from Conference c where c.category.id = cat.id)) from Category cat")
	Integer minConferenceCategory();

	@Query("select stddev(1.0*(select count(c) from Conference c where c.category.id = cat.id)) from Category cat")
	Double stddevConferenceCategory();

	@Query("select avg(1.0*(select count(c) from Comment c where c.conference.id = con.id)) from Conference con")
	Double avgCommentsConference();

	@Query("select max(1.0*(select count(c) from Comment c where c.conference.id = con.id)) from Conference con")
	Integer maxCommentsConference();

	@Query("select min(1.0*(select count(c) from Comment c where c.conference.id = con.id)) from Conference con")
	Integer minCommentsConference();

	@Query("select stddev(1.0*(select count(c) from Comment c where c.conference.id = con.id)) from Conference con")
	Double stddevCommentsConference();

	@Query("select avg(1.0*(select count(c) from Comment c where c.conference.id = con.id)) from Conference con")
	Double avgCommentsActivity();

	@Query("select max(1.0*(select count(c) from Comment c where c.conference.id = con.id)) from Conference con")
	Integer maxCommentsActivity();

	@Query("select min(1.0*(select count(c) from Comment c where c.conference.id = con.id)) from Conference con")
	Integer minCommentsActivity();

	@Query("select stddev(1.0*(select count(c) from Comment c where c.conference.id = con.id)) from Conference con")
	Double stddevCommentsActivity();
}
