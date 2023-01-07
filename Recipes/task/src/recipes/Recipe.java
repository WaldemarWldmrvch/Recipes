package recipes;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;
import org.hibernate.annotations.DynamicUpdate;
import org.hibernate.annotations.UpdateTimestamp;
import javax.persistence.*;
import javax.validation.constraints.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "recipe")
@RequiredArgsConstructor
@Getter
@Setter
@ToString
public class Recipe {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    @JsonIgnore
    private Long id;

    @NotBlank
    @Column(name = "name")
    private String name;

    @NotBlank
    @Column(name = "category")
    private String category;

    @Column(name = "data")
    @UpdateTimestamp
    private LocalDateTime date;

    @NotBlank
    @Column(name = "description")
    private String description;

    @NotEmpty
    @NotNull
    @Column(name = "ingredients")
    private String[] ingredients;

    @NotEmpty
    @NotNull
    @Column(name = "directions")
    private String[] directions;

    @JsonIgnore
    @Column(name = "creator")
    private String creator;

}

