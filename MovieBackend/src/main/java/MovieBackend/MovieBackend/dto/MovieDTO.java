package MovieBackend.MovieBackend.dto;
public class MovieDTO {
    private long id;
    private String title;
    private String description;
    private int releasedYear;
    private double rating;

    public MovieDTO(long id, String title, String description, int releasedYear, double rating) {
        this.id = id;
        this.title = title;
        this.description = description;
        this.releasedYear = releasedYear;
        this.rating = rating;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

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

    public int getReleasedYear() {
        return releasedYear;
    }

    public void setReleasedYear(int releasedYear) {
        this.releasedYear = releasedYear;
    }

    public double getRating() {
        return rating;
    }

    public void setRating(double rating) {
        this.rating = rating;
    }
}
