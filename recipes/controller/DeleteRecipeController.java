package recipes.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import recipes.entity.Recipe;
import recipes.route.v1.DeleteRoute;
import recipes.service.RecipeService;

import java.util.Optional;

@RestController
public class DeleteRecipeController {

    private final RecipeService recipeService;

    public DeleteRecipeController(RecipeService recipeService) {
        this.recipeService = recipeService;
    }

    @DeleteMapping(path = DeleteRoute.PATH)
    public ResponseEntity<?> deleteRecipe(@PathVariable Long id) {
        boolean wasDeleted = recipeService.deleteRecipeById(id);
        return new ResponseEntity<>( wasDeleted ? HttpStatus.NO_CONTENT : HttpStatus.NOT_FOUND);
    }
}
