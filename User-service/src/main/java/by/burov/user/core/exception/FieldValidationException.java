package by.burov.user.core.exception;

import by.burov.user.core.api.FieldValidationError;

import java.util.List;

public class FieldValidationException extends IllegalArgumentException {

    private String logref;
    private List<FieldValidationError> errors;

    public FieldValidationException(String logref, List<FieldValidationError> errors) {
        this.logref = logref;
        this.errors = errors;
    }

    public String getLogref() {
        return logref;
    }

    public List<FieldValidationError> getErrors() {
        return errors;
    }
}
