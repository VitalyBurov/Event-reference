package by.burov.classifiers.core.api;

import org.springframework.core.convert.converter.Converter;
import org.springframework.data.domain.Page;

public class PageConverter<T> implements Converter<Page<T>, PageResponse<T>> {

    @Override
    public PageResponse<T> convert(Page<T> source) {
        return new PageResponse<T>(
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
