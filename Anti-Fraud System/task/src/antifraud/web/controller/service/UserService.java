package antifraud.web.controller.service;

import antifraud.mappers.UserDTOMapper;
import antifraud.persistence.dao.UserRepository;
import antifraud.persistence.model.User;
import antifraud.web.dto.UserDto;
import antifraud.web.error.UserExistException;
import antifraud.web.error.UserNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserService {
    @Autowired
    private UserRepository userRepository;

    @Autowired
    private UserDTOMapper userDTOMapper;

    @Autowired
    PasswordEncoder encoder;

    public User registerNewUser(UserDto userDto){
        if (userExist(userDto.getUsername())){
            throw new UserExistException();
        }

        User user = userDTOMapper.toUser(userDto);
        user.setPassword(encoder.encode(userDto.getPassword()));
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
}
