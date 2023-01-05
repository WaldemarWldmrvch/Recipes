package recipes;

import lombok.*;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString
@Table(name = "users")
public class User {

    @Id
    @NotBlank
    @Column(name = "email")
    private String email;

    @NotBlank
    @Size(min = 8)
    @Column(name = "password")
    private String password;


}
