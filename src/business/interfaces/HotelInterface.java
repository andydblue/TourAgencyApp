package business.interfaces;

import entities.Country;
import entities.Hotel;
import entities.StarsRating;

import java.util.List;

public interface HotelInterface {
    List<Hotel> getHotelsByCountryID(int id);
    List<Hotel> getHotelsByCountryAndStars(int id, StarsRating stars);
}
