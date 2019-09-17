
package repositories;

import java.util.Collection;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import domain.Domp;

@Repository
public interface DompRepository extends JpaRepository<Domp, Integer> {

	@Query("select t from Domp t join t.administrator r1 where r1.id = ?1")
	public Collection<Domp> getDompsByAdministrator(int adminId);

	@Query("select t from Domp t join t.conference r2 where r2.id = ?1")
	public Collection<Domp> getDompsByConference(int conferenceId);

}
