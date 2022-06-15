package antifraud.web.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class UserDto {

    @NotBlank
    @NotNull
    private String name;
    @NotBlank
    @NotNull
    private String username;
    @NotNull
    @NotBlank
    private String password;
}
