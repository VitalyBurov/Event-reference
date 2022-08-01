package by.burov.event.controller;

import by.burov.event.core.api.PageConverter;
import by.burov.event.core.api.PageResponse;
import by.burov.event.core.dto.CreateConcertDto;
import by.burov.event.core.dto.ReadConcertDto;
import by.burov.event.service.api.ConcertService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.UUID;

@RestController
@RequestMapping("/afisha/event/concerts")
public class ConcertController {

    private ConcertService concertsService;


    public ConcertController(ConcertService concertsService) {
        this.concertsService = concertsService;

    }

    @PostMapping
    public ResponseEntity<ReadConcertDto> createConcert(@RequestBody CreateConcertDto concert) {
        return new ResponseEntity<>(concertsService.save(concert), HttpStatus.CREATED);

    }

    @GetMapping
    public ResponseEntity<PageResponse<ReadConcertDto>> getAllConcerts(@RequestParam(defaultValue = "1") int pageNo,
                                                                       @RequestParam(defaultValue = "10") int pageSize) {
        PageResponse<ReadConcertDto> response = new PageConverter<ReadConcertDto>().convert(concertsService.readAll(pageNo - 1, pageSize));
        return new ResponseEntity<>(
                response, HttpStatus.OK);
    }

    @GetMapping("/{uuid}")
    public ResponseEntity<ReadConcertDto> getEventByUuid(@PathVariable UUID uuid) {
        return new ResponseEntity<>(concertsService.getEventByUuid(uuid), HttpStatus.OK);
    }

    @PutMapping("/{uuid}/dt_update/{dt_update}")
    public ResponseEntity<ReadConcertDto> updateConcert(@PathVariable("uuid") UUID uuid,
                                                        @PathVariable("dt_update") Long dt,
                                                        @RequestBody CreateConcertDto concert) {
        LocalDateTime dtUpdate = LocalDateTime.ofInstant(Instant.ofEpochMilli(dt), ZoneId.systemDefault());

        return new ResponseEntity<>(concertsService.update(uuid, dtUpdate, concert), HttpStatus.OK);
    }
}
