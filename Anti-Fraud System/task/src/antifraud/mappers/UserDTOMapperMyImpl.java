package antifraud.mappers;

import antifraud.persistence.model.User;
import antifraud.web.dto.ResponseNewUserDto;
import antifraud.web.dto.UserDto;

import java.util.ArrayList;
import java.util.List;

public class UserDTOMapperMyImpl{

    public static User toUser(UserDto userDto) {
        if ( userDto == null ) {
            return null;
        }

        User user = new User();

        user.setUserName( userDto.getUsername() );
        user.setName( userDto.getName() );
        user.setPassword( userDto.getPassword() );

        return user;
    }


    public static ResponseNewUserDto toResponseUserDto(User User) {
        if ( User == null ) {
            return null;
        }

        ResponseNewUserDto responseNewUserDto = new ResponseNewUserDto();

        responseNewUserDto.setUsername( User.getUserName() );
        responseNewUserDto.setId( User.getId() );
        responseNewUserDto.setName( User.getName() );
        responseNewUserDto.setRole( User.getRole() );

        return responseNewUserDto;
    }


    public static List<ResponseNewUserDto> toUserInfos(List<User> users) {
        if ( users == null ) {
            return null;
        }

        List<ResponseNewUserDto> list = new ArrayList<ResponseNewUserDto>( users.size() );
        for ( User user : users ) {
            list.add( toResponseUserDto( user ) );
        }

        return list;
    }
}
