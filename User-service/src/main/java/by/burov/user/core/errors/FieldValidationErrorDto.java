package by.burov.user.core.errors;

public class FieldValidationErrorDto {

    private String field;
    private String message;

    public FieldValidationErrorDto(String field, String message){
        this.field = field;
        this.message = message;
    }

    public String getField() {
        return field;
    }

    public String getMessage() {
        return message;
    }
}
