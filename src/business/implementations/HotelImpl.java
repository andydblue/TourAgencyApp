package business.implementations;

import business.interfaces.HotelInterface;
import dao.implementationsMySQL.HotelDaoImplMySQl;
import entities.Hotel;
import entities.StarsRating;

import java.util.List;

public class HotelImpl implements HotelInterface {

    HotelDaoImplMySQl hotelDaoImplMySQl = new HotelDaoImplMySQl();

    @Override
    public List<Hotel> getHotelsByCountryID(int id) {
        return hotelDaoImplMySQl.getHotelsByCountry(id);
    }

    @Override
    public List<Hotel> getHotelsByCountryAndStars(int id, StarsRating stars) {
        return hotelDaoImplMySQl.getHotelsByStarsAndCountry(id, stars);
    }
}
