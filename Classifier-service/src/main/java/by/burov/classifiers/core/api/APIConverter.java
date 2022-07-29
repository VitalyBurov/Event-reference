package by.burov.classifiers.core.api;

import org.springframework.core.convert.converter.Converter;
import org.springframework.data.domain.Page;

public class APIConverter<T> implements Converter<Page<T>,APIResponse<T> > {

    @Override
    public APIResponse<T> convert(Page<T> source) {
        return new APIResponse<T>(
                source.getNumber(),
                source.getSize(),
                source.getTotalPages(),
                source.getTotalElements(),
                source.isFirst(),
                source.getNumberOfElements(),
                source.isLast(),
                source.getContent());
    }
}
