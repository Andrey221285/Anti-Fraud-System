package antifraud.web.controller.service;

import antifraud.mappers.UserDTOMapper;
import antifraud.mappers.UserDTOMapperMyImpl;
import antifraud.persistence.dao.UserRepository;
import antifraud.persistence.model.User;
import antifraud.web.dto.ChangeAccessto;
import antifraud.web.dto.ChangeRoleDto;
import antifraud.web.dto.UserDto;
import antifraud.web.error.UserExistException;
import antifraud.web.error.UserNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@Service
public class UserService {
    @Autowired
    private UserRepository userRepository;

//    @Autowired
//    private UserDTOMapperMyImpl userDTOMapper;

    @Autowired
    PasswordEncoder encoder;

    public User registerNewUser(UserDto userDto){
        if (userExist(userDto.getUsername())){
            throw new UserExistException();
        }

        User user = UserDTOMapperMyImpl.toUser(userDto);
        user.setPassword(encoder.encode(userDto.getPassword()));

        if (userRepository.count() == 0){
            user.setRole(ROLES.ADMINISTRATOR);
        } else {
            user.setRole(ROLES.MERCHANT);
            user.setLocked(true);
        }

        userRepository.save(user);
        return user;
    }


    private boolean userExist(String userName){
        return userRepository.findByUserNameIgnoreCase(userName) != null;
    }


    public List<User> getAllUsers() {
        return userRepository.findAll();
    }

    public User deleteUser(String userName) {
        User user = userRepository.findByUserNameIgnoreCase(userName);
        if (user != null){
            userRepository.delete(user);
            return user;
        }else {
            throw new UserNotFoundException();
        }

    }

    public User changeRole(ChangeRoleDto changeRoleDto) {
        if (changeRoleDto.getRole() == ROLES.ADMINISTRATOR){
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST);
        }
        User user = userRepository.findByUserNameIgnoreCase(changeRoleDto.getUsername());
        if (user == null){
            throw new UserNotFoundException();
        }
        if (user.getRole() == ROLES.ADMINISTRATOR){
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST);
        }
        if (changeRoleDto.getRole() != ROLES.MERCHANT && changeRoleDto.getRole() != ROLES.SUPPORT){
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST);
        }

        if (changeRoleDto.getRole() == user.getRole()){
            throw new ResponseStatusException(HttpStatus.CONFLICT);
        }

        user.setRole(changeRoleDto.getRole());
        userRepository.save(user);
        return user;

    }

    public User changeAccess(ChangeAccessto changeAccessto) {
        User user = userRepository.findByUserNameIgnoreCase(changeAccessto.getUsername());

        if (user == null){
            throw new UserNotFoundException();
        }
        if (user.getRole()==ROLES.ADMINISTRATOR){
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST);
        }

        if (changeAccessto.getOperation().equals("LOCK")){
            user.setLocked(true);
        } else if (changeAccessto.getOperation().equals("UNLOCK")){
            user.setLocked(false);
        }

        userRepository.save(user);
        return user;

    }


    public enum ROLES {
        ADMINISTRATOR,
        MERCHANT,
        SUPPORT
    }
}
