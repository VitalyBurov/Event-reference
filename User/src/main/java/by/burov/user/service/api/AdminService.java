package by.burov.user.service.api;

import by.burov.user.core.dto.CreateUserDto;
import by.burov.user.core.dto.ReadUserDto;
import by.burov.user.core.dto.RegistrationUserDto;
import org.springframework.data.domain.Page;
import org.springframework.validation.annotation.Validated;

import javax.validation.Valid;
import java.time.LocalDateTime;
import java.util.UUID;


@Validated
public interface AdminService {

    ReadUserDto save(@Valid CreateUserDto dto);

    Page<ReadUserDto> readAll(int pageNo, int pageSize);

    ReadUserDto getUserByUuid(UUID uuid);

    ReadUserDto update(UUID uuid, LocalDateTime dtUpdate, @Valid CreateUserDto dto);
}
