package recipes.Recipe;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.Optional;

@Repository
public interface RecipeRepository extends CrudRepository<RecipeEntity, Long> {
    Optional<RecipeEntity> findRecipeEntityById(long id);
    ArrayList<RecipeEntity> findRecipeEntitiesByNameContainingIgnoreCaseOrderByDateDesc(String name);
    ArrayList<RecipeEntity> findRecipeEntitiesByCategoryIgnoreCaseOrderByDateDesc(String category);
}
