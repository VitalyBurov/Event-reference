package by.burov.event.service.api;

import by.burov.event.core.dto.*;
import by.burov.event.repository.entity.Concert;
import by.burov.event.repository.entity.Film;
import by.burov.event.service.UserHolder;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.time.LocalDateTime;

@Mapper(componentModel = "spring", imports = LocalDateTime.class)
public interface MapperService {

    @Mapping(target = "dtCreate", expression = "java(LocalDateTime.now())")
    @Mapping(target = "dtUpdate", expression = "java(LocalDateTime.now())")
    Film filmEntity(CreateFilmDto createDto);

    @Mapping(target = "dtCreate", expression = "java(LocalDateTime.now())")
    @Mapping(target = "dtUpdate", expression = "java(LocalDateTime.now())")
    Concert concertEntity(CreateConcertDto createDto);

    ReadFilmDto readFilmDto(Film filmEntity);

    ReadConcertDto readConcertDto(Concert concertEntity);

    Film filmEntity(ReadFilmDto readFilmDto);

    Concert concertEntity(ReadConcertDto readConcertDto);


}