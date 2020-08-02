package pckg.su.repositories;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import pckg.su.domains.Category;

import java.util.Optional;

/**
 * Created by Admin on 31.03.2019.
 */
@Repository
public interface CategoryRepository extends CrudRepository<Category, Long> {

    Optional<Category> findByDescription(String description);
}
