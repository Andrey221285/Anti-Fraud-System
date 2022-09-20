package antifraud.web.dto;

import antifraud.validation.CardConstraint;
import antifraud.validation.IpConstraint;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class TransactionDto {


    @NotNull
    @Min(1)
    private Long amount;
    @IpConstraint
    private String ip;
    @CardConstraint
    private String number;

}
