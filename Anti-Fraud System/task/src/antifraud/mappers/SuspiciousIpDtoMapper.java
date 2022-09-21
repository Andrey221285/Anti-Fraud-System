package antifraud.mappers;

import antifraud.persistence.model.SuspiciousIp;
import antifraud.web.dto.SuspiciousIpDto;


public interface SuspiciousIpDtoMapper {

    SuspiciousIp toEntity (SuspiciousIpDto suspiciousIpDto);
}
