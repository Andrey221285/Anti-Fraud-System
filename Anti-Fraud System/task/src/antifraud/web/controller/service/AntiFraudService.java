package antifraud.web.controller.service;

import antifraud.mappers.SuspiciousIpDtoMapper;
import antifraud.persistence.dao.SuspiciousIpRepository;
import antifraud.persistence.model.SuspiciousIp;
import antifraud.web.dto.SuspiciousIpDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@Service
public class AntiFraudService {
    @Autowired
    SuspiciousIpRepository suspiciousIpRepository;
    @Autowired
    SuspiciousIpDtoMapper suspiciousIpDtoMapper;

    public SuspiciousIp addSuspiciousIp(SuspiciousIpDto suspiciousIpDto) {
        SuspiciousIp suspiciousIp = suspiciousIpRepository.findByIp(suspiciousIpDto.getIp());

        if (suspiciousIp != null){
            throw new ResponseStatusException(HttpStatus.CONFLICT, "IP is already in the database");
        }

        SuspiciousIp newSuspiciousIp = suspiciousIpDtoMapper.toEntity(suspiciousIpDto);
        suspiciousIpRepository.save(newSuspiciousIp);
        return newSuspiciousIp;
    }


    public boolean deleteIp(String ip) {
        SuspiciousIp suspiciousIp = suspiciousIpRepository.findByIp(ip);
        if (suspiciousIp!= null){
            suspiciousIpRepository.delete(suspiciousIp);
            return true;
        } else {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND);

        }

    }

    public List<SuspiciousIp> getIps() {
        return suspiciousIpRepository.findAll();
    }
}
