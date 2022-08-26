package antifraud.web.dto;

import antifraud.validation.CardConstraint;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class StolenCardDto {


    @CardConstraint
    private String number;
}
