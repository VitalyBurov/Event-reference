package by.burov.event.service;

import by.burov.event.core.dto.CreateConcertDto;
import by.burov.event.core.dto.ReadConcertDto;
import by.burov.event.core.enums.EventStatus;
import by.burov.event.core.exception.EntityNotFoundException;
import by.burov.event.repository.ConcertRepository;
import by.burov.event.repository.entity.Concert;
import by.burov.event.service.api.ConcertService;
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
public class ConcertServiceImpl implements ConcertService {

    private final ConcertRepository concertsDao;
    private final MapperService mapperService;
    private final UserHolder userHolder;


    public ConcertServiceImpl(ConcertRepository concertsDao, MapperService mapperService, UserHolder userHolder) {
        this.concertsDao = concertsDao;
        this.mapperService = mapperService;
        this.userHolder = userHolder;
    }

    @Override
    @Transactional
    public ReadConcertDto save(CreateConcertDto dto) {
        RestTemplate restTemplate = new RestTemplate();
        String concertResourceUrl
                //add to properties
                = "http://classifier-service:81/classifier/concert/category";
        ResponseEntity<String> response
                = restTemplate.getForEntity(concertResourceUrl + "/" + dto.getCategory(),
                String.class);
        if (response.getStatusCode() == HttpStatus.OK) {
            Concert concert = mapperService.concertEntity(dto);
            concert.setOwner(userHolder.getUser().getUsername());
            ReadConcertDto readConcertDto = mapperService.readConcertDto(concertsDao.save(concert));
            return readConcertDto;
        } else {
            throw new IllegalArgumentException("No such category in concert category classifier!");
        }
    }


    @Override
    @Transactional
    public Page<ReadConcertDto> readAll(int pageNo, int pageSize) {
        UserDetails user = userHolder.getUser();
        Pageable paging = PageRequest.of(pageNo, pageSize);
        Page<ReadConcertDto> pagedResult;
        Page<Concert> concertsFromDB;

        if (user == null) {
            concertsFromDB = concertsDao.findByStatus(EventStatus.PUBLISHED, paging);
        } else if (user.getAuthorities().contains(new SimpleGrantedAuthority("ROLE_ADMIN"))) {
            concertsFromDB = concertsDao.findAll(paging);
        } else {
            concertsFromDB = concertsDao.findByStatusORAuthor(EventStatus.PUBLISHED, user.getUsername(), paging);
        }

        pagedResult = concertsFromDB.map(entity -> {
            ReadConcertDto dto = mapperService.readConcertDto(entity);
            return dto;
        });

        return pagedResult;
    }

    @Override
    @Transactional
    public ReadConcertDto getEventByUuid(UUID uuid) {
        UserDetails user = userHolder.getUser();
        Concert concert;

        if (user == null) {
            concert = concertsDao.findByUuidAndStatus(uuid, EventStatus.PUBLISHED)
                    .orElseThrow(() -> new EntityNotFoundException("error", "Not found!!"));

        } else if (user.getAuthorities().contains(new SimpleGrantedAuthority("ROLE_ADMIN"))) {
            concert = concertsDao.findById(uuid).orElseThrow(() -> new EntityNotFoundException("error", "Not found!!"));
        } else {
            concert = concertsDao.findByUuidAndStatusOrAuthor(uuid, EventStatus.PUBLISHED, user.getUsername())
                    .orElseThrow(() -> new EntityNotFoundException("error", "Not found!!"));
        }

        ReadConcertDto dto = mapperService.readConcertDto(concert);
        return dto;
    }


    @Override
    @Transactional
    public ReadConcertDto update(UUID uuid, LocalDateTime dtUpdate, CreateConcertDto dto) {
        UserDetails user = userHolder.getUser();

        if (uuid == null) {
            throw new IllegalArgumentException("The field cannot be null");
        }
        RestTemplate restTemplate = new RestTemplate();
        String concertResourceUrl
                = "http://classifier-service:81/classifier/concert/category";;
        ResponseEntity<String> response
                = restTemplate.getForEntity(concertResourceUrl + "/" + dto.getCategory(),
                String.class);
        if (response.getStatusCode() == HttpStatus.OK) {
            ReadConcertDto dtoFromDB = this.getEventByUuid(uuid);

            if (!user.getUsername().equals(dtoFromDB.getAuthor()) || user.getAuthorities().contains(new SimpleGrantedAuthority("ROLE_ADMIN"))) {
                throw new IllegalArgumentException("You can't edit this event!");
            }

            if (!dtoFromDB.getDtUpdate().equals(dtUpdate)) {
                throw new IllegalArgumentException("The Film was updated before you!!!");
            }
            Concert concert = mapperService.concertEntity(dtoFromDB);
            addFields(dto, concert);
            concert.setOwner(dtoFromDB.getAuthor());
            ReadConcertDto readConcertDto = mapperService.readConcertDto(concertsDao.save(concert));
            return readConcertDto;
        } else {
            throw new IllegalArgumentException("No such category in concert category!");
        }
    }

    private void addFields(CreateConcertDto dto, Concert concert) {
        concert.setTitle(dto.getTitle());
        concert.setDescription(dto.getDescription());
        concert.setStatus(dto.getStatus());
        concert.setType(dto.getType());
        concert.setDtEvent(dto.getDtEvent());
        concert.setDtEndOfSale(dto.getDtEndOfSale());
        concert.setCategory(dto.getCategory());
    }

}



