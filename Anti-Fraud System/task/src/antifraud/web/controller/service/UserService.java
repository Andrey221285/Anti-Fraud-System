package antifraud.web.controller.service;

import antifraud.mappers.UserDTOMapper;
import antifraud.persistence.dao.UserRepository;
import antifraud.persistence.model.User;
import antifraud.web.dto.UserDto;
import antifraud.web.error.UserExistException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserService {
    @Autowired
    UserRepository userRepository;

    @Autowired
    UserDTOMapper userDTOMapper;

    public UserDto registerNewUser(UserDto userDto){
        if (userExist(userDto.getUsername())){
            throw new UserExistException();
        }

        User user = userDTOMapper.toUser(userDto);
        userRepository.save(user);
        return userDTOMapper.toUserDtoCreated(user);
    }


    private boolean userExist(String userName){
        return userRepository.findByUserNameIgnoreCase(userName) != null;
    }



}
