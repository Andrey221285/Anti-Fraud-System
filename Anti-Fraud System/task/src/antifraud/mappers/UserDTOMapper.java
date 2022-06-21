package antifraud.mappers;

import antifraud.persistence.model.User;
import antifraud.web.dto.ResponseNewUserDto;
import antifraud.web.dto.UserDto;
import antifraud.web.dto.UserInfoDto;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.List;

@Mapper(componentModel = "spring")
public interface UserDTOMapper {

    @Mapping(target="userName", source="username")
    User toUser (UserDto userDto);

    @Mapping(target="username", source="userName")
    ResponseNewUserDto toResponseUserDto (User User);

    @Mapping(target="username", source="userName")
    UserInfoDto toUserInfDto (User user);

    List<UserInfoDto> toUserInfos(List<User> users);

}
