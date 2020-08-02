package pckg.su.repositories;

import pckg.su.domains.Recipe;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.Locale;
import java.util.Optional;

/**
 * Created by Admin on 31.03.2019.
 */
@Repository
public interface RecipeRepository extends CrudRepository<Recipe,Long> {
    Optional<Recipe> findByDescription(String description);
}
