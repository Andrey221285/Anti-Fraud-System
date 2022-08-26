package antifraud.web.dto;

import antifraud.validation.IpConstraint;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class SuspiciousIpDto {

    @IpConstraint
    private String ip;
}

