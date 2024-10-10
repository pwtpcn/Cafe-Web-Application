package ku.cs.cafe.request;

import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import ku.cs.cafe.validation.ValidPassword;
import lombok.Data;


@Data
public class SignupRequest {


    @NotBlank
    @Size(min=4, message = "Username must have at least 4 characters")
    private String username;


    @NotBlank
    @ValidPassword
    private String password;


    @NotBlank(message = "Name is required")
    @Pattern(regexp = "^[a-zA-Z]+$", message = "Name only contain letters")
    private String name;
}

