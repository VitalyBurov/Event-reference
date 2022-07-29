package by.burov.classifiers.service;

import by.burov.classifiers.core.dto.CreateConcertCategoryDto;
import by.burov.classifiers.core.dto.ReadConcertCategoryDto;
import by.burov.classifiers.repository.ConcertDao;
import by.burov.classifiers.repository.entity.ConcertCategory;
import by.burov.classifiers.service.api.ConcertService;
import by.burov.classifiers.service.api.MappingService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.UUID;

@Service
public class ConcertServiceImpl implements ConcertService {

    private ConcertDao categoryDao;
    private MappingService mappingService;

    public ConcertServiceImpl(ConcertDao categoryDao, MappingService mappingService) {
        this.categoryDao = categoryDao;
        this.mappingService = mappingService;
    }


    @Override
    public ReadConcertCategoryDto save(CreateConcertCategoryDto dto) {
        ConcertCategory category = mappingService.mapCreateConcert(dto);
        category.setDtCreate(LocalDateTime.now());
        category.setDtUpdate(LocalDateTime.now());
        ReadConcertCategoryDto readConcertCategoryDto = mappingService.mapReadConcert(this.categoryDao.save(category));
        return readConcertCategoryDto ;
    }


    @Override
    public Page<ReadConcertCategoryDto> readAll (int pageNo, int pageSize) {
        Pageable paging = PageRequest.of(pageNo, pageSize);

        Page<ConcertCategory> concertCategoriesFromDB = categoryDao.findAll(paging);

        Page<ReadConcertCategoryDto> pagedResult = concertCategoriesFromDB.map(entity -> {
            ReadConcertCategoryDto dto = mappingService.mapReadConcert(entity);
            return dto;
        });

        return pagedResult;
    }

    @Override
    public ReadConcertCategoryDto getByUuid(UUID uuid) {
        ConcertCategory concertCategory = categoryDao.findById(uuid).get();
        ReadConcertCategoryDto dto = mappingService.mapReadConcert(concertCategory);
        return dto;
    }
}
