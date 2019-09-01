
package repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import domain.Panel;

@Repository
public interface PanelRepository extends JpaRepository<Panel, Integer> {

}
