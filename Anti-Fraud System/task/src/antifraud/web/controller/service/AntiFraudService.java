package antifraud.web.controller.service;

import antifraud.mappers.SuspiciousIpDtoMapper;
import antifraud.persistence.dao.StolenCardRepository;
import antifraud.persistence.dao.SuspiciousIpRepository;
import antifraud.persistence.model.StolenCard;
import antifraud.persistence.model.SuspiciousIp;
import antifraud.web.dto.StolenCardDto;
import antifraud.web.dto.SuspiciousIpDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@Service
public class AntiFraudService {
    @Autowired
    private SuspiciousIpRepository suspiciousIpRepository;
    @Autowired
    private SuspiciousIpDtoMapper suspiciousIpDtoMapper;
    @Autowired
    private StolenCardRepository stolenCardRepository;

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

    public StolenCard addStolenCard(StolenCardDto stolenCardDto) {
        StolenCard stolenCard = stolenCardRepository.findByNumber(stolenCardDto.getNumber());

        if (stolenCard != null){
            throw  new ResponseStatusException(HttpStatus.CONFLICT, "такая карта уже существует");
        }

        StolenCard stolenCardNew = new StolenCard();
        stolenCardNew.setNumber(stolenCardDto.getNumber());
        stolenCardRepository.save(stolenCardNew);
        return stolenCardNew;

    }

    public List<StolenCard> getStolenCards() {
    return stolenCardRepository.findAll();
    }

    public boolean deleteStrolencard(String number) {
        StolenCard stolenCard = stolenCardRepository.findByNumber(number);
        if (stolenCard != null){
            stolenCardRepository.delete(stolenCard);
            return true;
        } else {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND);
        }
    }

    public boolean isCheckedIp(String ip){
        return suspiciousIpRepository.findByIp(ip) == null;
    }

    public boolean isCheckedCard(String number){
        return stolenCardRepository.findByNumber(number) == null;
    }
}
