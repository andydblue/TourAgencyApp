package dao.implementationsMySQL;

import dao.connection.ConnectionSetUp;
import dao.interfaces.HotelDaoInterface;
import entities.*;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class HotelDaoImplMySQl extends ConnectionSetUp implements HotelDaoInterface {

    private Connection connection = getConnection();

    private final static String INSERT_NEW_HOTEL = "INSERT INTO hotels (id_ctry_for_htl, hotelName, starsRating, roomCoast) VALUES (?, ?, ?, ?)";
    private final static String SELECT_HOTEL_BY_ID = "SELECT * FROM hotels WHERE id_htl = ?";
    private final static String UPDATE_HOTEL_BY_ID = "UPDATE hotels SET id_ctry_for_htl = ?, hotelName = ?, starsRating = ?, roomCoast = ? WHERE id_htl = ? LIMIT 1";
    private final static String DELETE_HOTEL_BY_ID = "DELETE FROM hotels WHERE id_htl = ?";
    private final static String SELECT_HOTEL_BY_COUNTRY_ID = "SELECT id_ctry_for_htl, hotelName, starsRating, roomCoast FROM hotels WHERE id_ctry_for_htl = ?";
    private final static String SELECT_HOTEL_BY_STARS_AND_COUNTRY = "SELECT id_ctry_for_htl, hotelName, starsRating, roomCoast FROM hotels WHERE id_ctry_for_htl = ? AND starsRating = ?";

    @Override
    public Hotel create(Hotel hotel) {
        PreparedStatement statement = null;
        try {
            statement = connection.prepareStatement(INSERT_NEW_HOTEL);
            statement.setInt(1, hotel.getHotelCountryID());
            statement.setString(2, hotel.getHotelName());
            statement.setString(3, hotel.getStarsRating());
            statement.setInt(4, hotel.getRoomCoast());
            statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            try {
                if (statement != null) { statement.close(); }
            } catch (SQLException e) {}
        }
        return hotel;
    }

    @Override
    public Hotel read(Integer id) {
        Hotel hotel = null;
        PreparedStatement statement = null;
        try {
            statement = connection.prepareStatement(SELECT_HOTEL_BY_ID);
            statement.setInt(1, id);
            ResultSet resultSet = statement.executeQuery();
            if (resultSet.next()) {
                hotel = new Hotel();
                hotel.setHotelCountryID(resultSet.getInt("id_ctry_for_htl"));
                hotel.setHotelName(resultSet.getString("hotelName"));
                hotel.setStarsRating(StarsRating.getStarsByName(resultSet.getString("starsRating")));
                hotel.setRoomCoast(resultSet.getInt("roomCoast"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            try {
                if (statement != null) {statement.close();}
            } catch (SQLException e) {}
        }
        return hotel;
    }

    @Override
    public void update(Hotel hotel, Integer id) {
        PreparedStatement statement = null;
        try {
            statement = connection.prepareStatement(UPDATE_HOTEL_BY_ID);
            statement.setInt(1,hotel.getHotelCountryID());
            statement.setString(2, hotel.getHotelName());
            statement.setString(3, hotel.getStarsRating());
            statement.setInt(4, hotel.getRoomCoast());
            statement.setInt(5, id);
            statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            try {
                if (statement != null) { statement.close(); }
            } catch (SQLException e) {}
        }
    }

    @Override
    public void delete(Integer id) {
        PreparedStatement statement = null;
        try {
            statement = connection.prepareStatement(DELETE_HOTEL_BY_ID);
            statement.setInt(1,id);
            statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            try {
                if (statement != null) {statement.close();}
            } catch (SQLException e) {}
        }
    }

    @Override
    public List<Hotel> getHotelsByCountry(int id) {
        List<Hotel> hotelList = new ArrayList<>();
        Hotel hotel = null;
        PreparedStatement statement = null;
        try {
            statement = connection.prepareStatement(SELECT_HOTEL_BY_COUNTRY_ID);
            statement.setInt(1, id);
            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()) {
                hotel = new Hotel();
                hotel.setHotelCountryID(resultSet.getInt("id_ctry_for_htl"));
                hotel.setHotelName(resultSet.getString("hotelName"));
                hotel.setStarsRating(StarsRating.getStarsByName(resultSet.getString("starsRating")));
                hotel.setRoomCoast(resultSet.getInt("roomCoast"));
                hotelList.add(hotel);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            try {
                if (statement != null) {statement.close();}
            } catch (SQLException e) {}
        }
        return hotelList;
    }

    @Override
    public List<Hotel> getHotelsByStarsAndCountry(int id, StarsRating stars) {
        List<Hotel> hotelList = new ArrayList<>();
        Hotel hotel;
        PreparedStatement statement = null;
        try {
            statement = connection.prepareStatement(SELECT_HOTEL_BY_STARS_AND_COUNTRY);
            statement.setInt(1, id);
            statement.setString(2, stars.getStars());
            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()) {
                hotel = new Hotel();
                hotel.setHotelCountryID(resultSet.getInt("id_ctry_for_htl"));
                hotel.setHotelName(resultSet.getString("hotelName"));
                hotel.setStarsRating(StarsRating.getStarsByName(resultSet.getString("starsRating")));
                hotel.setRoomCoast(resultSet.getInt("roomCoast"));
                hotelList.add(hotel);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            try {
                if (statement != null) {statement.close();}
            } catch (SQLException e) {}
        }
        return hotelList;
    }
}
