
package repositories;

import java.util.Collection;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import domain.Category;
import domain.Conference;

@Repository
public interface CategoryRepository extends JpaRepository<Category, Integer> {

	@Query("select c from Conference c where c.category.id =?1")
	Collection<Conference> findConferencesByCategory(int categoryId);

	@Query("select c from Category c where c.parent.id =?1")
	Collection<Category> findCategoryHijas(int categoryId);

}
