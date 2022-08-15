package antifraud.web.dto;

import antifraud.web.controller.service.UserService;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ChangeRoleDto {
    @NotBlank
    private String username;

    @NotNull
    private UserService.ROLES role;

}
