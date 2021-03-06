package antifraud.mappers;

import antifraud.persistence.model.User;
import antifraud.web.dto.ResponseNewUserDto;
import antifraud.web.dto.UserDto;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.processing.Generated;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2022-06-22T18:40:29+0700",
    comments = "version: 1.5.1.Final, compiler: IncrementalProcessingEnvironment from gradle-language-java-7.3.3.jar, environment: Java 11.0.14.1 (JetBrains s.r.o.)"
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
    public List<ResponseNewUserDto> toUserInfos(List<User> users) {
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
