package by.burov.user.service.api;

import by.burov.user.core.dto.CreateUserDto;
import by.burov.user.core.dto.ReadUserDto;
import by.burov.user.core.dto.RegistrationUserDto;
import by.burov.user.repository.entity.User;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface UserMapper {

    @Mapping(target = "roles", ignore = true)
    ReadUserDto toReadDto(User entity);

    @Mapping(target = "authorities", ignore = true)
    User toCreateEntity(CreateUserDto dto);

    User toRegistrationEntity(RegistrationUserDto dto);


}

