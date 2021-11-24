package recipes.Recipe;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;
import org.hibernate.annotations.UpdateTimestamp;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.time.LocalDateTime;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity(name = "RECIPES")
public class RecipeEntity {
    @Id
    @JsonIgnore
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    public long id;

    @NotBlank
    public String name;

    @NotBlank
    public String category;

    @UpdateTimestamp
    public LocalDateTime date = LocalDateTime.now();

    @NotBlank
    public String description;

    @ElementCollection
    @NotNull
    @Size(min = 1)
    @CollectionTable(name = "RECIPES_LINKED_INGREDIENTS")
    public List<String> ingredients;

    @ElementCollection
    @NotNull
    @Size(min = 1)
    @CollectionTable(name = "RECIPES_LINKED_DIRECTIONS")
    public List<String> directions;

    public void updateEntity(RecipeEntity newRecipe){
        setName(newRecipe.name);
        setCategory(newRecipe.category);
        setDescription(newRecipe.description);
        setIngredients(newRecipe.ingredients);
        setDirections(newRecipe.directions);

    }
}