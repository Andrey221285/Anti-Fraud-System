package antifraud.mappers;

import antifraud.persistence.model.SuspiciousIp;
import antifraud.web.dto.SuspiciousIpDto;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface SuspiciousIpDtoMapper {

    SuspiciousIp toEntity (SuspiciousIpDto suspiciousIpDto);
}
