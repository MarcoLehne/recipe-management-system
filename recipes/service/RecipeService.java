package recipes.service;

import org.springframework.stereotype.Service;
import recipes.entity.Recipe;
import recipes.repository.RecipeRepository;

import javax.transaction.Transactional;
import java.util.Comparator;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.Stream;
import java.util.stream.StreamSupport;

@Service
public class RecipeService {

    private final RecipeRepository recipeRepository;

    public RecipeService(RecipeRepository recipeRepository) {
        this.recipeRepository = recipeRepository;
    }

    @Transactional
    public Long storeRecipe(Recipe recipe) {
        recipeRepository.save(recipe);
        return recipe.getId();
    }

    @Transactional
    public Optional<Recipe> retrieveRecipeById(Long id) {
        return recipeRepository.findById(id);
    }

    @Transactional
    public boolean deleteRecipeById(Long id) {
        if (recipeRepository.existsById(id)) {
            recipeRepository.deleteById(id);
            return !recipeRepository.existsById(id);
        } else {
            return false;
        }
    }


    @Transactional
    public List<Recipe> findAllByName(String name) {
        return recipeRepository.findByNameContainingIgnoreCase(name).stream()
                .sorted(Comparator.comparing(Recipe::getDate).reversed())
                .collect(Collectors.toList());
    }

    @Transactional
    public List<Recipe> findAllByCategory(String category) {
        return recipeRepository.findByCategoryIgnoreCase(category).stream()
                .sorted(Comparator.comparing(Recipe::getDate).reversed())
                .collect(Collectors.toList());
    }

}
