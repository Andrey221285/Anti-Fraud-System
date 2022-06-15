package antifraud.web.controller;


import antifraud.service.TransactionService;
import antifraud.web.dto.TransactionDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import java.util.HashMap;

@RestController
public class ApiController {

    @Autowired
    TransactionService transactionService;

@PostMapping("/api/antifraud/transaction")
    public ResponseEntity<?> addTransaction(@Valid @RequestBody TransactionDto transactionDto){
    HashMap<String, TransactionService.STATUS> map = new HashMap<>();
    map.put("result",transactionService.getStatus(transactionDto.getAmount()));

     return new ResponseEntity<>(map, HttpStatus.OK);
}
}
