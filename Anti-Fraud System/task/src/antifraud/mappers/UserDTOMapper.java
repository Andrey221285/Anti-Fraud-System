package antifraud.mappers;

import antifraud.persistence.model.User;
import antifraud.web.dto.UserDto;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface UserDTOMapper {
    @Mapping(ignore = true, target = "id")
    @Mapping(source = "username", target = "userName")
    User toUser (UserDto userDto);

    @Mapping(ignore = true, target = "password")
    @Mapping(source = "userName", target = "username")
    UserDto toUserDtoCreated(User user);


}
