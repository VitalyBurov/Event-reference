package by.burov.event.core.dto;

public class ReadFilmDto extends ReadEventDto{

    private String country;
    private int releaseYear;
    private String releaseDate;
    private int duration;

    public void setCountry(String country) {
        this.country = country;
    }

    public void setReleaseYear(int releaseYear) {
        this.releaseYear = releaseYear;
    }

    public void setReleaseDate(String releaseDate) {
        this.releaseDate = releaseDate;
    }

    public void setDuration(int duration) {
        this.duration = duration;
    }

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

