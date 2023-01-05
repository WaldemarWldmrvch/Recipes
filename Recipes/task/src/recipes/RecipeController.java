package recipes;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;
import javax.validation.Valid;
import java.time.LocalDateTime;
import java.util.*;

@RestController
public class RecipeController {
    @Autowired
    RecipeRepository repository;


    @GetMapping("/api/recipe/{id}")
    public Map<String, Object> getRecipe (@PathVariable Long id) {
        if (!repository.existsById(id)) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND);
        } else {
            Map<String, Object> returningRecipe = new LinkedHashMap<>();
            returningRecipe.put("name", repository.findById(id).get().getName());
            returningRecipe.put("category", repository.findById(id).get().getCategory());
            returningRecipe.put("date", repository.findById(id).get().getDate());
            returningRecipe.put("description", repository.findById(id).get().getDescription());
            returningRecipe.put("ingredients", repository.findById(id).get().getIngredients());
            returningRecipe.put("directions", repository.findById(id).get().getDirections());
            return returningRecipe;
        }
    }

    @PostMapping("/api/recipe/new")
    public String saveRecipe(@Valid @RequestBody(required = false) Recipe recipe) {
        if (recipe.getDirections().length > 0 && recipe.getIngredients().length > 0) {
            recipe.setDate(LocalDateTime.now());
            repository.save(recipe);
            return "{\"id\": " + repository.count() + "}";
        } else {
            throw new ResponseStatusException(HttpStatus.BAD_GATEWAY);
        }
    }

    @DeleteMapping("/api/recipe/{id}")
    public HttpStatus deleteRecipe(@PathVariable Long id) {
        if (!repository.existsById(id)) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND);
        } else {
            repository.deleteById(id);
            throw new ResponseStatusException(HttpStatus.NO_CONTENT);
        }
    }

    @PutMapping("/api/recipe/{id}")
    public HttpStatus putRecipe(@Valid @RequestBody Recipe recipe, @PathVariable Long id) {
        if (!repository.existsById(id)) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND);
        } else {
            repository.updateRecipe(recipe, id);
            repository.save(recipe);
            throw new ResponseStatusException(HttpStatus.NO_CONTENT);
        }
    }

    @GetMapping("/api/recipe/search")
    public List<Recipe> searchByCategory(@RequestParam(required = false) String category, @RequestParam(required = false) String name) {
        if (category != null && name != null || category == null && name == null) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST);
        } else {
            return category == null ? repository.getByName(name.toLowerCase()) : repository.getByCategory(category.toLowerCase());
        }
    }

}
