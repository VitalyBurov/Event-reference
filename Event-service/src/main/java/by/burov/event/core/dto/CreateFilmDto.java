package by.burov.event.core.dto;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

public class CreateFilmDto extends CreateEventDto {

    @NotBlank
    private String country;

    @NotNull
    @Min(1600)
    private int releaseYear;

    @NotNull
    private String releaseDate;

    @NotNull
    @Min(10)
    @Max(300)
    private int duration;

    public String getCountry() {
        return country;
    }

    public int getReleaseYear() {
        return releaseYear;
    }

    public String getReleaseDate() {
        return releaseDate;
    }

    public int getDuration() {
        return duration;
    }
}
