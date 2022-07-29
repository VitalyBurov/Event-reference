package by.burov.classifiers.service.api;

import org.springframework.data.domain.Page;
import org.springframework.validation.annotation.Validated;

import javax.validation.Valid;
import java.util.UUID;

@Validated
public interface ClassifierService<C, R> {

    R save(@Valid C dto);

    Page<R> readAll(int pageNo, int pageSize);

    R getByUuid(UUID uuid);


}
