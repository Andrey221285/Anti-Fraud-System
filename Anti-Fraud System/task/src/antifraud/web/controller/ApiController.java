package antifraud.web.controller;


import antifraud.mappers.UserDTOMapper;
import antifraud.persistence.model.StolenCard;
import antifraud.persistence.model.SuspiciousIp;
import antifraud.persistence.model.User;
import antifraud.web.controller.service.AntiFraudService;
import antifraud.web.controller.service.TransactionService;
import antifraud.web.controller.service.UserService;
import antifraud.web.dto.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.HashMap;
import java.util.List;

@RestController
public class ApiController {
    @Autowired
    UserDTOMapper userDTOMapper;
    @Autowired
    UserService userService;
    @Autowired
    TransactionService transactionService;
    @Autowired
    AntiFraudService antiFraudService;

    @PostMapping("/api/antifraud/transaction")
    public ResponseEntity<?> addTransaction(@Valid @RequestBody TransactionDto transactionDto) {
        HashMap<String, TransactionService.STATUS> map = new HashMap<>();
        map.put("result", transactionService.getStatus(transactionDto.getAmount()));

        return new ResponseEntity<>(map, HttpStatus.OK);
    }

    @PostMapping("/api/auth/user")
    public ResponseEntity<?> registerUserAccount(@Valid @RequestBody UserDto userDto){
        User user = userService.registerNewUser(userDto);

        ResponseNewUserDto responseNewUserDto = userDTOMapper.toResponseUserDto(user);
        return new ResponseEntity<>(responseNewUserDto, HttpStatus.CREATED);
    }

    @GetMapping("/api/auth/list")
    public ResponseEntity<?> getUsers (){
        List<User> userList = userService.getAllUsers();
        return new ResponseEntity<>(userDTOMapper.toUserInfos(userList), HttpStatus.OK);
    }

    @DeleteMapping ("/api/auth/user/{userName}")
    public ResponseEntity<?> deleteUser(@PathVariable String userName){
      User user = userService.deleteUser(userName);
        HashMap<String, String> map = new HashMap<>();
        map.put("username", user.getUserName());
        map.put("status", "Deleted successfully!");
        return new ResponseEntity<>(map, HttpStatus.OK);
    }

    @PutMapping("/api/auth/role")
    public ResponseEntity<?> changeRole(@Valid @RequestBody ChangeRoleDto changeRoleDto){

        ResponseNewUserDto responseNewUserDto = userDTOMapper.toResponseUserDto(userService.changeRole(changeRoleDto));;
        return new ResponseEntity<>(responseNewUserDto, HttpStatus.OK);

    }

    @PutMapping("/api/auth/access")
    public ResponseEntity<?> changeAccess (@Valid @RequestBody ChangeAccessto changeAccessto){

        User user = userService.changeAccess(changeAccessto);
        HashMap<String, String> map = new HashMap<>();
        map.put("status", String.format("User %s %s!", user.getUserName(), user.isLocked() ? "locked" : "unlocked"));
        return new ResponseEntity<>(map, HttpStatus.OK);

    }

    @PostMapping("/api/antifraud/suspicious-ip")
    public ResponseEntity<?> addSuspiciousIp(@Valid @RequestBody SuspiciousIpDto suspiciousIpDto){
        return new ResponseEntity<SuspiciousIp>(antiFraudService.addSuspiciousIp(suspiciousIpDto) ,HttpStatus.OK);
    }

    @DeleteMapping("/api/antifraud/suspicious-ip/{ip}")
    public ResponseEntity<?> delSuspiciousIp(@PathVariable String ip){
        antiFraudService.deleteIp(ip);
        HashMap<String, String> map = new HashMap<>();
        map.put("status", String.format("IP %s successfully removed!", ip));
        return new ResponseEntity<>(map, HttpStatus.OK);
    }

    @GetMapping("/api/antifraud/suspicious-ip")
    public ResponseEntity<List<SuspiciousIp>> getIPs(){
        return new ResponseEntity<>(antiFraudService.getIps(), HttpStatus.OK);
    }

    @PostMapping("/api/antifraud/stolencard")
    public  ResponseEntity<?> addStrolencard(@Valid @RequestBody StolenCardDto stolenCardDto){
       return new ResponseEntity<>( antiFraudService.addStolenCard(stolenCardDto), HttpStatus.OK);
    }

    @GetMapping("/api/antifraud/stolencard")
    public  ResponseEntity<List<StolenCard>> getStrolencards(){
       return new ResponseEntity<>( antiFraudService.getStolenCards(), HttpStatus.OK);
    }

    @DeleteMapping("/api/antifraud/stolencard/{number}")
    public ResponseEntity<?> delStrolencard(@PathVariable String number){
        antiFraudService.deleteStrolencard(number);
        HashMap<String, String> map = new HashMap<>();
        map.put("status", String.format("Card %s successfully removed!", number));
        return new ResponseEntity<>(map, HttpStatus.OK);
    }
}
