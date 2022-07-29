package by.burov.classifiers.service.api;

import by.burov.classifiers.core.dto.CreateConcertCategoryDto;
import by.burov.classifiers.core.dto.CreateCountryDto;
import by.burov.classifiers.core.dto.ReadConcertCategoryDto;
import by.burov.classifiers.repository.entity.ConcertCategory;
import by.burov.classifiers.repository.entity.Country;
import by.burov.classifiers.core.dto.ReadCountryDto;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface MappingService {

    Country mapCreateCountry(CreateCountryDto dto);

    ConcertCategory mapCreateConcert(CreateConcertCategoryDto dto);

    ReadConcertCategoryDto mapReadConcert(ConcertCategory concertCategory);

    ReadCountryDto mapReadCountry(Country country);

}
