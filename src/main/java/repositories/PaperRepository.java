
package repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import domain.Paper;

@Repository
public interface PaperRepository extends JpaRepository<Paper, Integer> {

}
