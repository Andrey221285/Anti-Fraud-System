package antifraud.mappers;

import antifraud.persistence.model.User;
import antifraud.web.dto.UserDto;
import javax.annotation.processing.Generated;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2022-06-15T18:44:52+0700",
    comments = "version: 1.5.1.Final, compiler: javac, environment: Java 18.0.1.1 (Oracle Corporation)"
)
@Component
public class UserDTOMapperImpl implements UserDTOMapper {

    @Override
    public User toUser(UserDto userDto) {
        if ( userDto == null ) {
            return null;
        }

        User user = new User();

        user.setUserName( userDto.getUsername() );
        user.setName( userDto.getName() );
        user.setPassword( userDto.getPassword() );

        return user;
    }

    @Override
    public UserDto toUserDtoCreated(User user) {
        if ( user == null ) {
            return null;
        }

        UserDto userDto = new UserDto();

        userDto.setUsername( user.getUserName() );
        userDto.setId( user.getId() );
        userDto.setName( user.getName() );

        return userDto;
    }
}
