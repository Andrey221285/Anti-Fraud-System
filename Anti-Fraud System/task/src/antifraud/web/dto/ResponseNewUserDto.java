package antifraud.web.dto;

import antifraud.web.controller.service.UserService;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ResponseNewUserDto {
    private Long id;
    private String name;
    private String username;
    private UserService.ROLES role;
}
