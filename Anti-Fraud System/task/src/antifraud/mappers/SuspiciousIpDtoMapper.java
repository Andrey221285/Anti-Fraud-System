package antifraud.mappers;

import antifraud.persistence.model.SuspiciousIp;
import antifraud.web.dto.SuspiciousIpDto;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface SuspiciousIpDtoMapper {

    SuspiciousIp toEntity (SuspiciousIpDto suspiciousIpDto);
}
