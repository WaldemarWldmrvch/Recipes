package recipes;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Indexed;

import java.util.List;

@Indexed
public interface RecipeRepository extends CrudRepository<Recipe, Long> {
    default void updateRecipe(Recipe recipe, Long id) {
            recipe.setId(id);
            recipe.setName(recipe.getName());
            recipe.setDate(recipe.getDate());
            recipe.setCategory(recipe.getCategory());
            recipe.setDescription(recipe.getDescription());
            recipe.setIngredients(recipe.getIngredients());
            recipe.setDirections(recipe.getDirections());
    }

    @Query(value = "SELECT * FROM recipe r WHERE LOWER(r.category) =:category ORDER BY r.data DESC", nativeQuery = true)
    List<Recipe> getByCategory(@Param("category") String category);

    @Query(value = "SELECT * FROM recipe r WHERE LOWER(r.name) LIKE %:name% ORDER BY r.data DESC", nativeQuery = true)
    List<Recipe> getByName(@Param("name") String name);
}
