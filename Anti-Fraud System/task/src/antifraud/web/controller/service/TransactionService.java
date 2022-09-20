package antifraud.web.controller.service;


import antifraud.web.dto.ResponseAddTransactionDto;
import antifraud.web.dto.TransactionDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.lang.reflect.Array;
import java.util.ArrayList;

@Service
public class TransactionService {
    @Autowired
    private AntiFraudService antiFraudService;

    public ResponseAddTransactionDto addTransaction(TransactionDto transactionDto) {
        ArrayList<String> list =new ArrayList<>();
        STATUS status = null;

        if (!antiFraudService.isCheckedCard(transactionDto.getNumber())){
            list.add("number");
        }
        if (!antiFraudService.isCheckedIp(transactionDto.getIp())){
            list.add("ip");
        }

        if (!list.isEmpty()){
            status = STATUS.PROHIBITED;
        }

        if (getStatus(transactionDto.getAmount())==STATUS.MANUAL_PROCESSING || getStatus(transactionDto.getAmount())==STATUS.PROHIBITED){
            list.add("amount");
        }

        if (status == null){
            status = getStatus(transactionDto.getAmount());
        }

        list.sort(String::compareTo);

        ResponseAddTransactionDto re = new ResponseAddTransactionDto();
        re.setResult(status.name());
        re.setInfo(status == STATUS.ALLOWED ? "none" : String.join(", ", list) );

        return re;


    }

    public enum STATUS{
        ALLOWED,
        MANUAL_PROCESSING,
        PROHIBITED
    }

    private STATUS getStatus(long amount){
        if (amount <=200){
            return STATUS.ALLOWED;
        } else if (amount <=1500){
            return STATUS.MANUAL_PROCESSING;
        } else {
            return STATUS.PROHIBITED;
        }
    }


}
