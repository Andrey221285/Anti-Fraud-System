package antifraud.mappers;

import antifraud.persistence.model.SuspiciousIp;
import antifraud.web.dto.SuspiciousIpDto;
import javax.annotation.processing.Generated;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2022-08-23T18:27:39+0700",
    comments = "version: 1.5.1.Final, compiler: javac, environment: Java 18.0.1.1 (Oracle Corporation)"
)
@Component
public class SuspiciousIpDtoMapperImpl implements SuspiciousIpDtoMapper {

    @Override
    public SuspiciousIp toEntity(SuspiciousIpDto suspiciousIpDto) {
        if ( suspiciousIpDto == null ) {
            return null;
        }

        SuspiciousIp suspiciousIp = new SuspiciousIp();

        suspiciousIp.setIp( suspiciousIpDto.getIp() );

        return suspiciousIp;
    }
}
