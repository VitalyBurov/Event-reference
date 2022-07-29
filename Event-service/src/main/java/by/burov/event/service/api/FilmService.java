package by.burov.event.service.api;

import by.burov.event.core.dto.CreateFilmDto;
import by.burov.event.core.dto.ReadFilmDto;

;

public interface FilmService extends EventService<CreateFilmDto, ReadFilmDto> {
}
