package recipes.Recipe;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Optional;

@Service
public class RecipeService {

    public final RecipeRepository recipeRepository;

    @Autowired
    public RecipeService(RecipeRepository recipeRepository) {
        this.recipeRepository = recipeRepository;
    }

    public Optional<RecipeEntity> findRecipeEntityById(long id) {
        return recipeRepository.findRecipeEntityById(id);
    }

    public void addRecipeEntity(RecipeEntity toSave) {
        recipeRepository.save(toSave);
    }

    public ArrayList<RecipeEntity> findRecipesEntitiesByCategory(String category) {
        return recipeRepository.findRecipeEntitiesByCategoryIgnoreCaseOrderByDateDesc(category);
    }

    public ArrayList<RecipeEntity> findRecipesEntitiesByName(String name) {
        return recipeRepository.findRecipeEntitiesByNameContainingIgnoreCaseOrderByDateDesc(name);
    }

    public void deleteRecipeEntity(RecipeEntity toDelete) {
        recipeRepository.delete(toDelete);
    }
}