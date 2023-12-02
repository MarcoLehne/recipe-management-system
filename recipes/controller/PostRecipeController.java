package recipes.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import recipes.DTO.PostRecipeResponse;
import recipes.DTO.RecipeDTO;
import recipes.entity.Recipe;
import recipes.route.v1.StoreRoute;
import recipes.service.RecipeService;

import javax.validation.Valid;
import java.time.LocalDateTime;

@RestController
@Validated
public class PostRecipeController {

    private final RecipeService recipeService;

    public PostRecipeController(RecipeService recipeService) {
        this.recipeService = recipeService;
    }

    @PostMapping(path = StoreRoute.PATH)
    public ResponseEntity<PostRecipeResponse> storeRecipe(@RequestBody @Valid RecipeDTO recipeDTO) {
        Long id = recipeService.storeRecipe(convertToEntity(recipeDTO));
        return new ResponseEntity<>(new PostRecipeResponse(id), HttpStatus.OK);
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
}
