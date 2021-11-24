package recipes.User;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;

import javax.persistence.*;
import javax.validation.constraints.*;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity(name = "USERS")
public class UserEntity {
    @Id
    @JsonIgnore
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    public long id;

    @NotBlank
    @Pattern(regexp = "(.*)@(.*)\\.(.*)")
    //@Email
    public String email;

    @NotBlank
    @Size(min = 8)
    public String password;

    @JsonIgnore
    @ElementCollection
    private List<Long> ownedRecipes;


    public void addRecipe(long recipeId) {
        this.ownedRecipes.add(recipeId);
    }

    public void deleteRecipe(long recipeId) {
        this.ownedRecipes.remove(recipeId);
    }

}
