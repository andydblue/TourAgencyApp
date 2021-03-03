package entities;

public class Hotel {
    private String hotelName;
    private StarsRating starsRating;
    private int roomCoast;
    private int hotelCountryID;

    public Hotel() {}

    public Hotel(String hotelName, StarsRating starsRating, int roomCoast) {
        this.hotelName = hotelName;
        this.starsRating = starsRating;
        this.roomCoast = roomCoast;
    }

    public int getRoomCoast() {
        return roomCoast;
    }

    public int getHotelCountryID() { return hotelCountryID; }

    public String getStarsRating() {
        return starsRating.getStars();
    }

    public String getHotelName() {
        return hotelName;
    }

    public void setHotelCountryID(int hotelCountryID) { this.hotelCountryID = hotelCountryID; }

    public void setHotelName(String hotelName) {
        this.hotelName = hotelName;
    }

    public void setRoomCoast(int roomCoast) {
        this.roomCoast = roomCoast;
    }

    public void setStarsRating(StarsRating starsRating) {
        this.starsRating = starsRating;
    }
}
