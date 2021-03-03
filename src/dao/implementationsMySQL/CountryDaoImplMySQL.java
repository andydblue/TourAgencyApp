package dao.implementationsMySQL;

import dao.connection.ConnectionSetUp;
import dao.interfaces.CountryDaoInterface;
import entities.Country;
import entities.Season;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class CountryDaoImplMySQL extends ConnectionSetUp implements CountryDaoInterface {

    private Connection connection = getConnection();

    private final static String INSERT_NEW_COUNTRY = "INSERT INTO countries (countryName, season, flightCoast) VALUES (?, ?, ?)";
    private final static String SELECT_COUNTRY_BY_ID = "SELECT * FROM countries WHERE id_ctry = ?";
    private final static String UPDATE_COUNTRY_BY_ID = "UPDATE countries SET countryName = ?, season = ?, flightCoast = ? WHERE id_ctry = ? LIMIT 1";
    private final static String DELETE_COUNTRY_BY_ID = "DELETE FROM countries WHERE id_ctry = ?";
    private final static String SELECT_ALL_COUNTRIES = "SELECT * FROM countries";
    private final static String SELECT_COUNTRIES_BY_SEASON = "SELECT * FROM countries WHERE season = ?";

    @Override
    public Country create(Country country) {
        PreparedStatement statement = null;
        try {
            statement = connection.prepareStatement(INSERT_NEW_COUNTRY);
            statement.setString(1,country.getCountryName());
            statement.setString(2, country.getSeason());
            statement.setInt(3, country.getFlightCoast());
            statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            try {
                if (statement != null) { statement.close(); }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return country;
    }

    @Override
    public Country read(Integer id) {
        Country country = null;
        PreparedStatement statement = null;
        try {
            statement = connection.prepareStatement(SELECT_COUNTRY_BY_ID);
            statement.setInt(1, id);
            ResultSet resultSet = statement.executeQuery();
            if (resultSet.next()) {
                country = new Country();
                country.setId(resultSet.getInt("id_ctry"));
                country.setCountryName(resultSet.getString("countryName"));
                country.setCountrySeason(Season.getSeasonByName(resultSet.getString("season")));
                country.setFlightCoast(resultSet.getInt("flightCoast"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            try {
                if (statement != null) { statement.close(); }
            } catch (SQLException e) {}
        }
        return country;
    }

    @Override
    public void update(Country country, Integer id) {
        PreparedStatement statement = null;
        try {
            statement = connection.prepareStatement(UPDATE_COUNTRY_BY_ID);
            statement.setString(1,country.getCountryName());
            statement.setString(2, country.getSeason());
            statement.setInt(3, country.getFlightCoast());
            statement.setInt(4, id);
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
            statement = connection.prepareStatement(DELETE_COUNTRY_BY_ID);
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
    public List<Country> getAllCountries() {
        List<Country> countryList = new ArrayList<Country>();
        Country country = null;
        PreparedStatement statement = null;
        try {
            statement = connection.prepareStatement(SELECT_ALL_COUNTRIES);
            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()) {
                country = new Country();
                country.setId(resultSet.getInt("id_ctry"));
                country.setCountryName(resultSet.getString("countryName"));
                country.setCountrySeason(Season.getSeasonByName(resultSet.getString("season")));
                country.setFlightCoast(resultSet.getInt("flightCoast"));
                countryList.add(country);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            try {
                if (statement != null) {statement.close();}
            } catch (SQLException e) {}
        }
        return countryList;
    }

    @Override
    public List<Country> getCountriesBySeason(Season season) {
        List<Country> listCountry = new ArrayList<>();
        Country country;
        PreparedStatement statement = null;
        try {
            statement = connection.prepareStatement(SELECT_COUNTRIES_BY_SEASON);
            statement.setString(1, season.getName());
            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()) {
                country = new Country();
                country.setId(resultSet.getInt("id_ctry"));
                country.setCountryName(resultSet.getString("countryName"));
                country.setCountrySeason(Season.getSeasonByName(resultSet.getString("season")));
                country.setFlightCoast(resultSet.getInt("flightCoast"));
                listCountry.add(country);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            try {
                if (statement != null) {statement.close();}
            } catch (SQLException e) {}
        }
        return listCountry;
    }
}
