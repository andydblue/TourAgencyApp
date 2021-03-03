package dao.interfaces;

import entities.Hotel;
import entities.StarsRating;

import java.util.List;

public interface HotelDaoInterface extends DaoCrud<Hotel, Integer> {
    List<Hotel> getHotelsByCountry(int id);
    List<Hotel> getHotelsByStarsAndCountry(int id, StarsRating stars);
}
