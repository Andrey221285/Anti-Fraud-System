package antifraud.web.dto;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ChangeAccessto {
    @NotBlank
    private String username;
    @NotBlank
    private String operation;

}
