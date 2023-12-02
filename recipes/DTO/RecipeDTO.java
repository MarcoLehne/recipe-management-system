package recipes.DTO;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.NonNull;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.time.LocalDateTime;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class RecipeDTO {

    @NotBlank(message = "Name is required")
    @NonNull
    @NotNull
    private String name;

    @NotBlank(message = "Description is required")
    @NonNull
    @NotNull
    private String description;

    private LocalDateTime date;

    @NotBlank(message = "category is required")
    @NonNull
    @NotNull
    private String category;

    @Size(min = 1, message = "At least one ingredient is required")
    @NonNull
    @NotNull
    private List<String> ingredients;

    @Size(min = 1, message = "At least one direction is required")
    @NonNull
    @NotNull
    private List<String> directions;
}
