package by.burov.classifiers.controllers;

import by.burov.classifiers.core.api.PageConverter;
import by.burov.classifiers.core.api.PageResponse;
import by.burov.classifiers.core.dto.CreateConcertCategoryDto;
import by.burov.classifiers.core.dto.CreateCountryDto;
import by.burov.classifiers.core.dto.ReadConcertCategoryDto;
import by.burov.classifiers.core.dto.ReadCountryDto;
import by.burov.classifiers.service.api.ConcertService;
import by.burov.classifiers.service.api.CountryService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@RequestMapping("/classifier")
public class ClassifierController {

    private final CountryService countryService;
    private final ConcertService concertCategoryService;


    public ClassifierController(CountryService countryService, ConcertService concertCategoryService) {
        this.countryService = countryService;
        this.concertCategoryService = concertCategoryService;
    }


    @PostMapping("/country")
    public ResponseEntity<ReadCountryDto> createCountry(@RequestBody CreateCountryDto dto) {
        return new ResponseEntity<>(countryService.save(dto), HttpStatus.CREATED);

    }


    @GetMapping("/country")
    public ResponseEntity<PageResponse<ReadCountryDto>> getAllCountries(@RequestParam(defaultValue = "1") Integer pageNo,
                                                                        @RequestParam(defaultValue = "10") Integer pageSize) {
        PageResponse<ReadCountryDto> response = new PageConverter<ReadCountryDto>().convert(countryService.readAll(pageNo-1, pageSize));
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @GetMapping("/country/{uuid}")
    public ResponseEntity<ReadCountryDto> getCountryByUuid(@PathVariable UUID uuid) {
        return new ResponseEntity<>(countryService.getByUuid(uuid), HttpStatus.OK);
    }


    @PostMapping("/concert/category")
    public ResponseEntity<ReadConcertCategoryDto> createConcertCategory(@RequestBody CreateConcertCategoryDto dto) {
        return new ResponseEntity<>(concertCategoryService.save(dto), HttpStatus.CREATED);

    }

    @GetMapping("/concert/category")
    public ResponseEntity<PageResponse<ReadConcertCategoryDto>> getAllCategories(@RequestParam(defaultValue = "1") Integer pageNo,
                                                                                 @RequestParam(defaultValue = "10") Integer pageSize) {
        PageResponse<ReadConcertCategoryDto> response = new PageConverter<ReadConcertCategoryDto>().convert(concertCategoryService.readAll(pageNo-1, pageSize));
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @GetMapping("/concert/category/{uuid}")
    public ResponseEntity<ReadConcertCategoryDto> getCategoriesByUuid(@PathVariable UUID uuid) {
        return new ResponseEntity<>(concertCategoryService.getByUuid(uuid), HttpStatus.OK);
    }


}
