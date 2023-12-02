package recipes.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class Recipe {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    String name;
    String description;
    LocalDateTime date;
    String category;

    @ElementCollection
    @CollectionTable(name = "recipe_ingredients")
    @Column(name = "ingredient")
    List<String> ingredients;

    @ElementCollection
    @CollectionTable(name = "recipe_directions")
    @Column(name = "direction")

    List<String> directions;
}
