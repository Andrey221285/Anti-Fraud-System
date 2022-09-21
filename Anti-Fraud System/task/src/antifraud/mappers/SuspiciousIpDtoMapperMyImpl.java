package antifraud.mappers;

import antifraud.persistence.model.SuspiciousIp;
import antifraud.web.dto.SuspiciousIpDto;
import org.springframework.stereotype.Component;


public class SuspiciousIpDtoMapperMyImpl{


    public static SuspiciousIp toEntity(SuspiciousIpDto suspiciousIpDto) {
        if ( suspiciousIpDto == null ) {
            return null;
        }

        SuspiciousIp suspiciousIp = new SuspiciousIp();

        suspiciousIp.setIp( suspiciousIpDto.getIp() );

        return suspiciousIp;
    }
}
