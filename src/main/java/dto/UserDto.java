package dto;

import jakarta.validation.constraints.NotNull;
import org.hibernate.validator.constraints.Email;
import org.hibernate.validator.constraints.Length;

public class UserDto {
    private Long id;
    @Email(message="Email invalido")
    private String email;
    @Length(min=3, max=50, message = "O nome deve conter entre 3 e 50 caracteres")
    private String name;
    @NotNull
    @Length(min=6, message = "A senha deve conter no mínimo 6 caracteres")
    private String password;
}
