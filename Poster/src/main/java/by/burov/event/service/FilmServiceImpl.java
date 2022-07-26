package by.burov.event.service;

import by.burov.event.core.dto.CreateFilmDto;
import by.burov.event.core.dto.ReadFilmDto;
import by.burov.event.core.enums.EventStatus;
import by.burov.event.core.exception.EntityNotFoundException;
import by.burov.event.repository.FilmRepository;
import by.burov.event.repository.entity.Film;
import by.burov.event.service.api.FilmService;
import by.burov.event.service.api.MapperService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.client.RestTemplate;

import java.time.LocalDateTime;
import java.util.UUID;

@Service
@Transactional(readOnly = true)
public class FilmServiceImpl implements FilmService {

    private final FilmRepository filmsDao;
    private final MapperService mapperService;
    private final UserHolder userHolder;

    public FilmServiceImpl(FilmRepository filmsDao, MapperService mapperService, UserHolder userHolder) {
        this.filmsDao = filmsDao;
        this.mapperService = mapperService;
        this.userHolder = userHolder;
    }

    @Override
    @Transactional
    public ReadFilmDto save(CreateFilmDto dto) {

        RestTemplate restTemplate = new RestTemplate();
        String concertResourceUrl = "http://localhost:80/api/v1/classifier/country";
        ResponseEntity<String> response = restTemplate.getForEntity(concertResourceUrl + "/" + dto.getCountry(), String.class);
        if (response.getStatusCode() == HttpStatus.OK) {
            Film film = mapperService.filmEntity(dto);
            film.setOwner(userHolder.getUser().getUsername());
            ReadFilmDto readFilmDto = mapperService.readFilmDto(filmsDao.save(film));
            return readFilmDto;
        } else {
            throw new IllegalArgumentException("No such country in country classifier!");
        }

    }

    @Override
    @Transactional
    public Page<ReadFilmDto> readAll(int pageNo, int pageSize) {
        UserDetails user = userHolder.getUser();
        Pageable paging = PageRequest.of(pageNo, pageSize);
        Page<ReadFilmDto> pagedResult;
        Page<Film> filmsFromDB;

        if (user == null) {
            filmsFromDB = filmsDao.findByStatus(EventStatus.PUBLISHED, paging);
        } else if (user.getAuthorities().contains(new SimpleGrantedAuthority("ADMIN"))) {
            filmsFromDB = filmsDao.findAll(paging);
        } else {
            filmsFromDB = filmsDao.findByStatusORAuthor(EventStatus.PUBLISHED, user.getUsername(), paging);
        }

        pagedResult = filmsFromDB.map(entity -> {
            ReadFilmDto dto = mapperService.readFilmDto(entity);
            return dto;
        });

        return pagedResult;
    }

    @Override
    public ReadFilmDto getEventByUuid(UUID uuid) {

        UserDetails user = userHolder.getUser();
        Film film;

        if (user == null) {
            film = filmsDao.findByUuidAndStatus(uuid, EventStatus.PUBLISHED)
                    .orElseThrow(() -> new by.burov.event.core.exception.EntityNotFoundException("error", "Not found!!" ));

        } else if (user.getAuthorities().contains(new SimpleGrantedAuthority("ADMIN"))) {
            film = filmsDao.findById(uuid).orElseThrow(() -> new EntityNotFoundException("error", "Not found!!" ));;
        } else {
            film = filmsDao.findByUuidAndStatusOrAuthor(uuid, EventStatus.PUBLISHED, user.getUsername())
                    .orElseThrow(() -> new EntityNotFoundException("error", "Not found!!" ));
        }

        ReadFilmDto dto = mapperService.readFilmDto(film);
        return dto;
    }

    @Override
    public ReadFilmDto update(UUID uuid, LocalDateTime dtUpdate, CreateFilmDto dto) {
        UserDetails user = userHolder.getUser();

        if (uuid == null) {
            throw new IllegalArgumentException("The field cannot be null");
        }

        RestTemplate restTemplate = new RestTemplate();
        String concertResourceUrl = "http://localhost:80/api/v1/classifier/country";
        ResponseEntity<String> response = restTemplate.getForEntity(concertResourceUrl + "/" + dto.getCountry(), String.class);
        if (response.getStatusCode() == HttpStatus.OK) {
            ReadFilmDto dtoFromDB = this.getEventByUuid(uuid);

            if (!user.getUsername().equals(dtoFromDB.getAuthor())) {
                throw new IllegalArgumentException("You can't edit this event!");
            }

            if (!dtoFromDB.getDtUpdate().equals(dtUpdate)) {
                throw new IllegalArgumentException("The Film was updated before you!!!");
            }
            //should refactor check to null every field
            Film film = mapperService.filmEntity(dtoFromDB);
            addFields(dto, film);
            ReadFilmDto readFilmDto = mapperService.readFilmDto(filmsDao.save(film));
            return readFilmDto;
        } else {
            throw new IllegalArgumentException("No such country in country classifier!");
        }
    }

    private void addFields(CreateFilmDto dto, Film film) {
        film.setTitle(dto.getTitle());
        film.setDescription(dto.getDescription());
        film.setStatus(dto.getStatus());
        film.setType(dto.getType());
        film.setDtEvent(dto.getDtEvent());
        film.setDtEndOfSale(dto.getDtEndOfSale());
        film.setCountry(dto.getCountry());
        film.setReleaseDate(dto.getReleaseDate());
        film.setReleaseYear(dto.getReleaseYear());
        film.setDuration(dto.getDuration());
    }

}
