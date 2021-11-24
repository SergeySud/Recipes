package recipes.Recipe;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;
import recipes.User.MyUserDetails;
import recipes.User.UserEntity;
import recipes.User.UserService;

import javax.validation.Valid;
import java.util.ArrayList;
import java.util.Map;
import java.util.Optional;

@RestController
public class RecipeController {
    RecipeService recipeService;
    UserService userService;

    @Autowired
    public RecipeController(RecipeService recipeService, UserService userService) {
        this.recipeService = recipeService;
        this.userService = userService;
    }

    @PostMapping(path = "/api/recipe/new", produces = MediaType.APPLICATION_JSON_VALUE)
    public Map<String, Long> addNewRecipe(@Valid @RequestBody RecipeEntity newRecipe, @AuthenticationPrincipal MyUserDetails user) {
        recipeService.addRecipeEntity(newRecipe);
        UserEntity userEntity = userService.findUserEntityByUsername(user.getUsername()).get();
        long id = newRecipe.getId();
        userEntity.addRecipe(id);
        userService.updateUserEntity(userEntity);
        return Map.of("id", newRecipe.getId());
    }

    @GetMapping("/api/recipe/{id}")
    public Optional<RecipeEntity> getRecipeById(@PathVariable long id) {
        Optional<RecipeEntity> entity = recipeService.findRecipeEntityById(id);
        if (entity.isEmpty()) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND);
        }
        return entity;
    }

    @DeleteMapping("/api/recipe/{id}")
    public ResponseEntity<Void> deleteRecipeById(@PathVariable long id, @AuthenticationPrincipal MyUserDetails user) {
        UserEntity userEntity = userService.findUserEntityByUsername(user.getUsername()).get();
        Optional<RecipeEntity> entity = recipeService.findRecipeEntityById(id);
        if (entity.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        if (!(userEntity.getOwnedRecipes().contains(id))) {
            return new ResponseEntity<>(HttpStatus.FORBIDDEN);
        }
        recipeService.deleteRecipeEntity(entity.get());
        userEntity.deleteRecipe(id);
        userService.updateUserEntity(userEntity);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @PutMapping("/api/recipe/{id}")
    public ResponseEntity<Void> putRecipeById(@PathVariable long id, @Valid @RequestBody RecipeEntity newRecipe, @AuthenticationPrincipal MyUserDetails user) {

        Optional<RecipeEntity> entity = recipeService.findRecipeEntityById(id);
        UserEntity userEntity = userService.findUserEntityByUsername(user.getUsername()).get();
        if (entity.isEmpty()) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND);
        }
        if (!(userEntity.getOwnedRecipes().contains(id))) {
            return new ResponseEntity<>(HttpStatus.FORBIDDEN);
        }

        entity.get().updateEntity(newRecipe);
        recipeService.addRecipeEntity(entity.get());
        userEntity.addRecipe(id);
        userService.updateUserEntity(userEntity);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);

    }

    @GetMapping(value = "/api/recipe/search", params = "category")
    public ArrayList<RecipeEntity> getRecipesByCategory(@RequestParam String category) {
        return recipeService.findRecipesEntitiesByCategory(category);
    }

    @GetMapping(value = "/api/recipe/search", params = "name")
    public ArrayList<RecipeEntity> getRecipesByNameLike(@RequestParam String name) {
        return recipeService.findRecipesEntitiesByName(name);
    }
}