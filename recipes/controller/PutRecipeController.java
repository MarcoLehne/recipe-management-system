package recipes.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import recipes.DTO.PostRecipeResponse;
import recipes.DTO.RecipeDTO;
import recipes.entity.Recipe;
import recipes.route.v1.PutRoute;
import recipes.service.RecipeService;

import javax.validation.Valid;
import java.time.LocalDateTime;
import java.util.Optional;

@RestController
@Validated
public class PutRecipeController {

    private final RecipeService recipeService;

    public PutRecipeController(RecipeService recipeService) {
        this.recipeService = recipeService;
    }

    @PutMapping(path = PutRoute.PATH)
    public ResponseEntity<?> putRecipe(@PathVariable Long id, @RequestBody @Valid RecipeDTO recipeDTO) {

        Optional<Recipe> existingRecipe = recipeService.retrieveRecipeById(id);

        if (existingRecipe.isPresent()) {
            Recipe updatedRecipe = existingRecipe.get();
            updateRecipeWithDTO(updatedRecipe, recipeDTO);
            recipeService.storeRecipe(updatedRecipe);

            return ResponseEntity.noContent().build();
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    private Recipe convertToEntity(RecipeDTO recipeDTO) {
        Recipe recipe = new Recipe();

        recipe.setName(recipeDTO.getName());
        recipe.setDescription(recipeDTO.getDescription());
        recipe.setDate(LocalDateTime.now());
        recipe.setCategory(recipeDTO.getCategory());
        recipe.setIngredients(recipeDTO.getIngredients());
        recipe.setDirections(recipeDTO.getDirections());

        return recipe;
    }

    private void updateRecipeWithDTO(Recipe recipe, RecipeDTO recipeDTO) {

        recipe.setName(recipeDTO.getName());
        recipe.setDescription(recipeDTO.getDescription());
        recipe.setDate(LocalDateTime.now());
        recipe.setCategory(recipeDTO.getCategory());
        recipe.setIngredients(recipeDTO.getIngredients());
        recipe.setDirections(recipeDTO.getDirections());

    }
}
