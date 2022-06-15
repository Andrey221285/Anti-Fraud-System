package antifraud.web.controller;


import antifraud.persistence.model.User;
import antifraud.web.controller.service.TransactionService;
import antifraud.web.controller.service.UserService;
import antifraud.web.dto.TransactionDto;
import antifraud.web.dto.UserDto;
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
    UserService userService;
    @Autowired
    TransactionService transactionService;

    @PostMapping("/api/antifraud/transaction")
    public ResponseEntity<?> addTransaction(@Valid @RequestBody TransactionDto transactionDto) {
        HashMap<String, TransactionService.STATUS> map = new HashMap<>();
        map.put("result", transactionService.getStatus(transactionDto.getAmount()));

        return new ResponseEntity<>(map, HttpStatus.OK);
    }

    @PostMapping("/api/auth/user")
    public ResponseEntity<?> registerUserAccount(@Valid @RequestBody UserDto userDto){
        UserDto dto = userService.registerNewUser(userDto);
        return new ResponseEntity<>(dto, HttpStatus.OK);
    }


}
