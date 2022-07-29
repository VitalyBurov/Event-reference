package by.burov.user.core.errors;

import java.util.List;

public class FieldValidationResultDto{

    private String logref;
    private List<FieldValidationErrorDto> errors;

    public FieldValidationResultDto(String logref, List<FieldValidationErrorDto> errors) {
        this.logref = logref;
        this.errors = errors;
    }

    public String getLogref() {
        return logref;
    }

    public List<FieldValidationErrorDto> getErrors() {
        return errors;
    }
}
