package antifraud.mappers;

import antifraud.persistence.model.User;
import antifraud.web.dto.ResponseNewUserDto;
import antifraud.web.dto.UserDto;
import antifraud.web.dto.UserInfoDto;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.processing.Generated;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2022-06-22T16:03:19+0700",
    comments = "version: 1.5.1.Final, compiler: javac, environment: Java 15.0.2 (Oracle Corporation)"
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
    public ResponseNewUserDto toResponseUserDto(User User) {
        if ( User == null ) {
            return null;
        }

        ResponseNewUserDto responseNewUserDto = new ResponseNewUserDto();

        responseNewUserDto.setUsername( User.getUserName() );
        responseNewUserDto.setId( User.getId() );
        responseNewUserDto.setName( User.getName() );

        return responseNewUserDto;
    }

    @Override
    public UserInfoDto toUserInfDto(User user) {
        if ( user == null ) {
            return null;
        }

        UserInfoDto userInfoDto = new UserInfoDto();

        userInfoDto.setUsername( user.getUserName() );
        userInfoDto.setName( user.getName() );

        return userInfoDto;
    }

    @Override
    public List<UserInfoDto> toUserInfos(List<User> users) {
        if ( users == null ) {
            return null;
        }

        List<UserInfoDto> list = new ArrayList<UserInfoDto>( users.size() );
        for ( User user : users ) {
            list.add( toUserInfDto( user ) );
        }

        return list;
    }
}
