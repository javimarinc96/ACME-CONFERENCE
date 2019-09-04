
package repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import domain.Commentable;

@Repository
public interface CommentableRepository extends JpaRepository<Commentable, Integer> {

}
