package by.burov.event.core.exception;

import java.util.List;

public class EntityNotFoundException extends IllegalStateException {

    private String logref;
    private String message;

    public EntityNotFoundException(String logref, String message) {
        this.message = message;
        this.logref = logref;
    }

    public String getLogref() {
        return logref;
    }

    public void setLogref(String logref) {
        this.logref = logref;
    }

    @Override
    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}