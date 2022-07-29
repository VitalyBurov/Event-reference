package by.burov.classifiers.service;

import by.burov.classifiers.core.dto.CreateCountryDto;
import by.burov.classifiers.core.dto.ReadCountryDto;
import by.burov.classifiers.repository.CountryDao;
import by.burov.classifiers.repository.entity.Country;
import by.burov.classifiers.service.api.CountryService;
import by.burov.classifiers.service.api.MappingService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.UUID;

@Service
public class CountryServiceImpl implements CountryService {

    private CountryDao countryDao;
    private MappingService mappingService;

    public CountryServiceImpl(CountryDao countryDao, MappingService mappingService) {
        this.countryDao = countryDao;
        this.mappingService = mappingService;
    }

    @Override
    public ReadCountryDto save (CreateCountryDto dto) {
        Country country = mappingService.mapCreateCountry(dto);
        country.setDtCreate(LocalDateTime.now());
        country.setDtUpdate(LocalDateTime.now());
        ReadCountryDto readCountryDto = mappingService.mapReadCountry(this.countryDao.save(country));
        return readCountryDto;
    }

    @Override
    public Page<ReadCountryDto> readAll (int pageNo, int pageSize) {
        Pageable paging = PageRequest.of(pageNo, pageSize);

        Page<Country> countriesFromDB = countryDao.findAll(paging);

        Page<ReadCountryDto> pagedResult = countriesFromDB.map(entity -> {
            ReadCountryDto dto = mappingService.mapReadCountry(entity);
            return dto;
        });

        return pagedResult;
    }

    @Override
    public ReadCountryDto getByUuid(UUID uuid) {
        Country country = countryDao.findById(uuid).get();
        ReadCountryDto dto = mappingService.mapReadCountry(country);
        return dto;
    }
}
