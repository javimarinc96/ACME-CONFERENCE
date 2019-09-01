
package repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import domain.Customization;

@Repository
public interface CustomizationRepository extends JpaRepository<Customization, Integer> {

}
