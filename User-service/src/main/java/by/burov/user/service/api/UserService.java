package by.burov.user.service.api;

import by.burov.user.core.dto.CreateUserDto;
import by.burov.user.core.dto.LoginUserDto;
import by.burov.user.core.dto.ReadUserDto;
import by.burov.user.core.dto.RegistrationUserDto;
import by.burov.user.repository.entity.User;
import org.springframework.data.domain.Page;
import org.springframework.validation.annotation.Validated;

import javax.validation.Valid;
import java.time.LocalDateTime;
import java.util.UUID;


@Validated
public interface UserService{

    ReadUserDto register(@Valid RegistrationUserDto dto);

    User login(@Valid LoginUserDto loginUserDto);

    ReadUserDto getUserInfo();
}
