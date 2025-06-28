package dto;

import java.time.LocalDateTime;

public class Booking{
    private int bookingId;
    private int userId;
    private int showId;
    private int seatsBooked;
    private String movieName;
    private LocalDateTime showTime;
    private LocalDateTime bookingTime;

    // Constructor for inserting booking
    public Booking(int userId, int showId, int seatsBooked) {
        this.userId = userId;
        this.showId = showId;
        this.seatsBooked = seatsBooked;
    }
    public Booking(int bookingId, int userId, int showId, int seatsBooked, LocalDateTime bookingTime) {
        this.bookingId = bookingId;
        this.userId = userId;
        this.showId = showId;
        this.seatsBooked = seatsBooked;
        this.bookingTime = bookingTime;
    }

    // Constructor for viewing history
    public Booking(int bookingId, String movieName, LocalDateTime showTime, int seatsBooked, LocalDateTime bookingTime) {
        this.bookingId = bookingId;
        this.movieName = movieName;
        this.showTime = showTime;
        this.seatsBooked = seatsBooked;
        this.bookingTime = bookingTime;
    }

    public int getBookingId() { return bookingId; }
    public String getMovieName() { return movieName; }
    public LocalDateTime getShowTime() { return showTime; }
    public int getSeatsBooked() { return seatsBooked; }
    public LocalDateTime getBookingTime() { return bookingTime; }

    public int getUserId() { return userId; }
    public int getShowId() { return showId; }
    public int getSeatsBookedToInsert() { return seatsBooked; }
    
   
}
