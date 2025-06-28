package dto;

import java.time.LocalDateTime;

public class Show {
    private int showId;
    private int movieId;
    private int screenId;
    private LocalDateTime showTime;
    private double price;
    private int availableSeats;

    // ✅ New fields for JOIN display
    private String movieTitle;
    private String theaterName;

    // Full constructor
    public Show(int showId, int movieId, int screenId, LocalDateTime showTime, double price, int availableSeats) {
        this.showId = showId;
        this.movieId = movieId;
        this.screenId = screenId;
        this.showTime = showTime;
        this.price = price;
        this.availableSeats = availableSeats;
    }

    // Backward-compatible constructor
    public Show(int showId, int movieId, int screenId, LocalDateTime showTime, double price) {
        this(showId, movieId, screenId, showTime, price, 0);
    }

    // Getters
    public int getShowId() { return showId; }
    public int getMovieId() { return movieId; }
    public int getScreenId() { return screenId; }
    public LocalDateTime getShowTime() { return showTime; }
    public double getPrice() { return price; }
    public int getAvailableSeats() { return availableSeats; }

    public String getMovieTitle() {
        return movieTitle;
    }
    public String getTheaterName() {
        return theaterName;
    }
    // Setter
    public void setAvailableSeats(int availableSeats) {
        this.availableSeats = availableSeats;
    }
    public void setMovieTitle(String movieTitle) {
        this.movieTitle = movieTitle;
    }
    public void setTheaterName(String theaterName) {
        this.theaterName = theaterName;
    }
}
