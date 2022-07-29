package by.burov.event.core.dto;

import by.burov.event.controller.utills.json.LocalDateTimeDeserializer;
import by.burov.event.core.enums.EventStatus;
import by.burov.event.core.enums.EventType;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;

import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.validation.constraints.NotNull;
import java.time.LocalDateTime;

public abstract class CreateEventDto {

    @NotNull
    private String title;

    @NotNull
    private String description;

    @NotNull
    @JsonDeserialize(using = LocalDateTimeDeserializer.class)
    private LocalDateTime dtEvent;

    @NotNull
    @JsonDeserialize(using = LocalDateTimeDeserializer.class)
    private LocalDateTime dtEndOfSale;

    @NotNull
    @Enumerated(EnumType.STRING)
    private EventType type;

    @NotNull
    @Enumerated(EnumType.STRING)
    private EventStatus status;

    public String getTitle() {
        return title;
    }

    public String getDescription() {
        return description;
    }

    public LocalDateTime getDtEvent() {
        return dtEvent;
    }

    public LocalDateTime getDtEndOfSale() {
        return dtEndOfSale;
    }

    public EventType getType() {
        return type;
    }

    public EventStatus getStatus() {
        return status;
    }
}
