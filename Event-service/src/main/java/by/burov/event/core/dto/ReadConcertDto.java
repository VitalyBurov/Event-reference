package by.burov.event.core.dto;

public class ReadConcertDto extends ReadEventDto{

    private String category;

    public void setCategory(String category) {
        this.category = category;
    }

    public String getCategory() {
        return category;
    }
}

