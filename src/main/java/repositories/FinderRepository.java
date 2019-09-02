
package repositories;

import java.util.Collection;
import java.util.Date;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import domain.Category;
import domain.Conference;
import domain.Finder;

@Repository
public interface FinderRepository extends JpaRepository<Finder, Integer> {

	@Query("select t from Conference t where t.category = ?1 and t.draftMode=false")
	Collection<Conference> findConferencesByCategory(Category category);

	@Query("select t from Conference t where t.fee < ?1 and t.draftMode=false")
	Collection<Conference> findConferencesByMaxFee(Double maxFee);

	@Query("select t from Conference t where t.startDate >= ?1 and t.draftMode=false")
	Collection<Conference> findConferencesByStartDate(Date startDate);

	@Query("select t from Conference t where t.endDate <= ?1 and t.draftMode=false")
	Collection<Conference> findConferencesByEndDate(Date endDate);

}
