package by.burov.event.core.errors;

public class SingleErrorDto {

    private String logref;
    private String message;

    public SingleErrorDto(String logref, String message){
        this.logref = logref;
        this.message = message;
    }

    public String getLogref() {
        return logref;
    }

    public String getMessage() {
        return message;
    }
}
