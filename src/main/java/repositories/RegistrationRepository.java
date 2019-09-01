
package repositories;

import java.util.Collection;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import domain.Author;
import domain.Registration;

@Repository
public interface RegistrationRepository extends JpaRepository<Registration, Integer> {

	@Query("select r from Registration r where r.author.id = ?1")
	Collection<Registration> findByAuthorId(int authorId);

	@Query("select r.author from Registration r")
	Collection<Author> getRegistrationAuthors();

}
