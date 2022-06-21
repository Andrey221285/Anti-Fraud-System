package antifraud.web.dto;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

// TODO: 21.06.2022 need remake
@Data
@NoArgsConstructor
@AllArgsConstructor
public class UserInfoDto {
    private String name;
    private String username;
}
