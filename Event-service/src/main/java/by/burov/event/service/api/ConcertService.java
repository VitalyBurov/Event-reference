package by.burov.event.service.api;


import by.burov.event.core.dto.CreateConcertDto;
import by.burov.event.core.dto.ReadConcertDto;
import by.burov.event.core.dto.ReadEventDto;
import by.burov.event.repository.entity.Concert;
import org.springframework.stereotype.Service;


public interface ConcertService extends EventService<CreateConcertDto, ReadConcertDto> {

}
