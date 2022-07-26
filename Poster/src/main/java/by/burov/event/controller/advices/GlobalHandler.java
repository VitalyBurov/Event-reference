package by.burov.event.controller.advices;

import by.burov.event.core.dto.FieldValidationErrorDto;
import by.burov.event.core.dto.FieldValidationResultDto;
import by.burov.event.core.errors.FieldValidationError;
import by.burov.event.core.errors.SingleErrorDto;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import javax.validation.ConstraintViolation;
import javax.validation.ConstraintViolationException;
import javax.validation.Path;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@RestControllerAdvice
public class GlobalHandler {

    @ExceptionHandler
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public FieldValidationResultDto handle(ConstraintViolationException e) {
        List<FieldValidationError> errors = new ArrayList<>();
        String field = null;
        for (ConstraintViolation<?> constraintViolation : e.getConstraintViolations()) {
            for (Path.Node node : constraintViolation.getPropertyPath()) {
                field = node.getName();
            }
            errors.add(new FieldValidationError(constraintViolation.getMessage(), field));
        }
        FieldValidationResultDto data = new FieldValidationResultDto("structured_errors",
                errors.stream()
                        .map(error -> new FieldValidationErrorDto(error.getField(), error.getMessage()))
                        .collect(Collectors.toList()));
        return data;
    }

    @ExceptionHandler
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    public List<SingleErrorDto> handle(RuntimeException e) {
        List<SingleErrorDto> data = new ArrayList<>();
        SingleErrorDto errorDto = new SingleErrorDto("error", e.getMessage());
        data.add(errorDto);
        return data;
    }

    @ExceptionHandler
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    public List<SingleErrorDto> handle(IllegalArgumentException e) {
        List<SingleErrorDto> data = new ArrayList<>();
        SingleErrorDto errorDto = new SingleErrorDto("error", e.getMessage());
        data.add(errorDto);
        return data;
    }

    @ExceptionHandler
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    public List<SingleErrorDto> handle(NullPointerException e) {
        List<SingleErrorDto> data = new ArrayList<>();
        SingleErrorDto errorDto = new SingleErrorDto("error", e.getMessage());
        data.add(errorDto);
        return data;
    }

    @ExceptionHandler
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    public List<SingleErrorDto> handle(UsernameNotFoundException e) {
        List<SingleErrorDto> data = new ArrayList<>();
        SingleErrorDto errorDto = new SingleErrorDto("error", e.getMessage());
        data.add(errorDto);
        return data;
    }


}

