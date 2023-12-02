package recipes.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import recipes.DTO.RecipeDTO;
import recipes.entity.Recipe;
import recipes.route.v1.RetrieveRoute;
import recipes.service.RecipeService;

import java.util.Optional;

@RestController
public class GetRecipeController {

    private final RecipeService recipeService;

    public GetRecipeController(RecipeService recipeService) {
        this.recipeService = recipeService;
    }

    @GetMapping(path = RetrieveRoute.PATH)
    public ResponseEntity<RecipeDTO> getRecipe(@PathVariable Long id) {

        Optional<Recipe> retrievedRecipe = recipeService.retrieveRecipeById(id);


        return retrievedRecipe.map(recipe -> ResponseEntity.ok(convertToDto(recipe)))
                .orElse(new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    private RecipeDTO convertToDto(Recipe recipe) {
        return new RecipeDTO(
                recipe.getName(),
                recipe.getDescription(),
                recipe.getDate(),
                recipe.getCategory(),
                recipe.getIngredients(),
                recipe.getDirections()
        );
    }
}
