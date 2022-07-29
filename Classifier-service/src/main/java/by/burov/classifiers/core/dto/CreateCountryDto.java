package by.burov.classifiers.core.dto;

import javax.validation.constraints.NotNull;

public class CreateCountryDto {
    @NotNull
    private String title;

    @NotNull
    private String description;

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

}
