package antifraud.web.controller;


import antifraud.mappers.UserDTOMapper;
import antifraud.persistence.model.User;
import antifraud.web.controller.service.TransactionService;
import antifraud.web.controller.service.UserService;
import antifraud.web.dto.ResponseNewUserDto;
import antifraud.web.dto.TransactionDto;
import antifraud.web.dto.UserDto;
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



}
