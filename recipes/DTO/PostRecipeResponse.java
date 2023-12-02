package recipes.DTO;

import lombok.Data;
import lombok.Getter;

@Data
@Getter
public class PostRecipeResponse {
    private Long id;
    public PostRecipeResponse(Long id) {
        this.id = id;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }
}
