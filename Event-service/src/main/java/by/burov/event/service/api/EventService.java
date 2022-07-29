package by.burov.event.service.api;

import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;

import javax.validation.Valid;
import java.time.LocalDateTime;
import java.util.UUID;


@Validated
public interface EventService<C,R>{

    R save (@Valid C dto);

    Page<R> readAll(int pageNo, int pageSize);

    R getEventByUuid(UUID uuid);

    R update (UUID uuid, LocalDateTime dtUpdate,@Valid C dto);
}
