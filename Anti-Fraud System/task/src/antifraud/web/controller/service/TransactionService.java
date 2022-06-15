package antifraud.web.controller.service;


import org.springframework.stereotype.Service;

@Service
public class TransactionService {
    public enum STATUS{
        ALLOWED,
        MANUAL_PROCESSING,
        PROHIBITED
    }

    public STATUS getStatus(long amount){
        if (amount <=200){
            return STATUS.ALLOWED;
        } else if (amount <=1500){
            return STATUS.MANUAL_PROCESSING;
        } else {
            return STATUS.PROHIBITED;
        }
    }


}
