
package repositories;

import java.util.Collection;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import domain.Quolet;

@Repository
public interface QuoletRepository extends JpaRepository<Quolet, Integer> {

	@Query("select t from Quolet t join t.administrator r1 where r1.id = ?1")
	public Collection<Quolet> getQuoletsByAdministrator(int adminId);

	@Query("select t from Quolet t join t.conference r2 where r2.id = ?1")
	public Collection<Quolet> getQuoletsByConference(int conferenceId);

}
