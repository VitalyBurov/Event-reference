package by.burov.event.core.dto;

import javax.validation.constraints.NotNull;

public class CreateConcertDto extends CreateEventDto {

    @NotNull
    private String category;

    public String getCategory() {
        return category;
    }
}
