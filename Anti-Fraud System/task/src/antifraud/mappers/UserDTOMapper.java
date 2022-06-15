package antifraud.mappers;

import antifraud.persistence.model.User;
import antifraud.web.dto.UserDto;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface UserDTOMapper {

    User toUser (UserDto userDto);
}
