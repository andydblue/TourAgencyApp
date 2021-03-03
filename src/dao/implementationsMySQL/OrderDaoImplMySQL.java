package dao.implementationsMySQL;

import com.sun.org.apache.xpath.internal.operations.Or;
import dao.connection.ConnectionSetUp;
import dao.interfaces.OrderDaoInterface;
import entities.*;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class OrderDaoImplMySQL extends ConnectionSetUp implements OrderDaoInterface {

    private Connection connection = getConnection();

    private final static String INSERT_NEW_ORDER = "INSERT INTO orders (orderNumber, customer, country, hotel, excursion, totalCoast) VALUES (?, ?, ?, ?, ?, ?)";
    private final static String SELECT_ORDER_BY_ID = "SELECT * FROM orders WHERE id_order = ?";
    private final static String UPDATE_ORDER_BY_ID = "UPDATE orders SET orderNumber = ?, customer = ?, country = ?, hotel = ?, excursion = ?, totalCoast = ? WHERE id_order = ? LIMIT 1";
    private final static String DELETE_ORDER_BY_ID = "DELETE FROM orders WHERE id_order = ?";
    private final static String SELECT_ORDER_BY_ORDER_NUMBER = "SELECT orderNumber, customer, country, hotel, excursion, totalCoast FROM orders WHERE orderNumber = ?";
    private final static String DELETE_ORDER_BY_ORDER_NUMBER = "DELETE FROM orders WHERE orderNumber = ?";
    private final static String SELECT_COUNTRY_BY_NAME = "SELECT countryName, season, flightCoast FROM countries WHERE countryName = ?";
    private final static String SELECT_HOTEL_BY_NAME = "SELECT id_ctry_for_htl, hotelName, starsRating, roomCoast FROM hotels WHERE hotelName = ?";
    private final static String SELECT_EXCURSION_BY_NAME = "SELECT id_ctry_for_exc, excursionName, excursionCoast FROM excursions WHERE excursionName = ?";

    @Override
    public Order create(Order order) {
        PreparedStatement statement = null;
        try {
            statement = connection.prepareStatement(INSERT_NEW_ORDER);
            statement.setLong(1, order.getOrderNumber());
            statement.setString(2, order.getCustomer());
            statement.setString(3, order.getCountry().getCountryName());
            statement.setString(4, order.getHotel().getHotelName());
            statement.setString(5, order.getExcursion().getExcursionName());
            statement.setLong(6, order.getTotalCoast());
            statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            try {
                if (statement != null) { statement.close(); }
            } catch (SQLException e) {}
        }
        return order;
    }

    @Override
    public Country getCountryByName(String name) {
        Country country = null;
        PreparedStatement statement = null;
        try {
            statement = connection.prepareStatement(SELECT_COUNTRY_BY_NAME);
            statement.setString(1, name);
            ResultSet resultSet = statement.executeQuery();
            if (resultSet.next()) {
                country = new Country();
                country.setCountryName(resultSet.getString("countryName"));
                country.setCountrySeason(Season.getSeasonByName(resultSet.getString("season")));
                country.setFlightCoast(resultSet.getInt("flightCoast"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            try {
                if (statement != null) {statement.close();}
            } catch (SQLException e) {}
        }
        return country;
    }

    @Override
    public Hotel getHotelByName (String name) {
        Hotel hotel = null;
        PreparedStatement statement = null;
        try {
            statement = connection.prepareStatement(SELECT_HOTEL_BY_NAME);
            statement.setString(1, name);
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
    public Excursion getExcursionByName(String name) {
        Excursion excursion = null;
        PreparedStatement statement = null;
        try {
            statement = connection.prepareStatement(SELECT_EXCURSION_BY_NAME);
            statement.setString(1, name);
            ResultSet resultSet = statement.executeQuery();
            if (resultSet.next()) {
                excursion = new Excursion();
                excursion.setExcursionCountryID(resultSet.getInt("id_ctry_for_exc"));
                excursion.setExcursionName(resultSet.getString("excursionName"));
                excursion.setExcursionCoast(resultSet.getInt("excursionCoast"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            try {
                if (statement != null) {statement.close();}
            } catch (SQLException e) {}
        }
        return excursion;
    }

    @Override
    public Order read(Integer id) {
        Order order = null;
        PreparedStatement statement = null;
        try {
            statement = connection.prepareStatement(SELECT_ORDER_BY_ID);
            statement.setInt(1, id);
            ResultSet resultSet = statement.executeQuery();
            if (resultSet.next()) {
                order = new Order();
                order.setOrderNumber(resultSet.getLong("orderNumber"));
                order.setCustomer(resultSet.getString("customer"));
                order.setCountry(getCountryByName(resultSet.getString("country")));
                order.setHotel(getHotelByName(resultSet.getString("hotel")));
                order.setExcursion(getExcursionByName(resultSet.getString("excursion")));
                order.setTotalCoast(resultSet.getLong("totalCoast"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            try {
                if (statement != null) {statement.close();}
            } catch (SQLException e) {}
        }
        return order;
    }

    @Override
    public void update(Order order, Integer id) {
        PreparedStatement statement = null;
        try {
            statement = connection.prepareStatement(UPDATE_ORDER_BY_ID);
            statement.setLong(1,order.getOrderNumber());
            statement.setString(2, order.getCustomer());
            statement.setString(3, order.getCountry().getCountryName());
            statement.setString(4, order.getHotel().getHotelName());
            statement.setString(5, order.getExcursion().getExcursionName());
            statement.setLong(6, order.getTotalCoast());
            statement.setInt(7, id);
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
            statement = connection.prepareStatement(DELETE_ORDER_BY_ID);
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
    public Order getOrderByOrderNumber(long number) {
        Order order = null;
        PreparedStatement statement = null;
        try {
            statement = connection.prepareStatement(SELECT_ORDER_BY_ORDER_NUMBER);
            statement.setLong(1, number);
            ResultSet resultSet = statement.executeQuery();
            if (resultSet.next()) {
                order = new Order();
                order.setOrderNumber(resultSet.getLong("orderNumber"));
                order.setCustomer(resultSet.getString("customer"));
                order.setCountry(getCountryByName(resultSet.getString("country")));
                order.setHotel(getHotelByName(resultSet.getString("hotel")));
                order.setExcursion(getExcursionByName(resultSet.getString("excursion")));
                order.setTotalCoast(resultSet.getLong("totalCoast"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            try {
                if (statement != null) {statement.close();}
            } catch (SQLException e) {}
        }
        return order;
    }

    @Override
    public boolean removeOrderByOrderNumber(long number) {
        PreparedStatement statement = null;
        int flag = 0;
        try {
            statement = connection.prepareStatement(DELETE_ORDER_BY_ORDER_NUMBER);
            statement.setLong(1, number);
            flag = statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            try {
                if (statement != null) {statement.close();}
            } catch (SQLException e) {}
        }
        return flag > 0;
    }
}
