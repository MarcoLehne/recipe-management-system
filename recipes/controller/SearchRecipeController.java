package recipes.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import recipes.DTO.RecipeDTO;
import recipes.entity.Recipe;
import recipes.route.v1.RetrieveRoute;
import recipes.route.v1.SearchRoute;
import recipes.service.RecipeService;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@RestController
public class SearchRecipeController {

    private final RecipeService recipeService;

    public SearchRecipeController(RecipeService recipeService) {
        this.recipeService = recipeService;
    }

    @GetMapping(path = SearchRoute.PATH)
    public ResponseEntity<List<RecipeDTO>> getRecipe(
            @RequestParam(required=false) String category,
            @RequestParam(required=false) String name) {

        if ((category != null && name != null) || (category == null && name == null)) {
            return ResponseEntity.badRequest().build();
        }

        List<Recipe> recipes;
        if (category != null) {
            recipes = recipeService.findAllByCategory(category);
        } else {
            recipes = recipeService.findAllByName(name);
        }

        List<RecipeDTO> recipeDTOs = recipes.stream()
                .map(this::convertToDto)
                .collect(Collectors.toList());

        return ResponseEntity.ok(recipeDTOs);
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
