package dao.implementationsMySQL;

import dao.connection.ConnectionSetUp;
import dao.interfaces.ExcursionDaoInterface;
import entities.Country;
import entities.Excursion;
import entities.Season;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class ExcursionDaoImplMySQL extends ConnectionSetUp implements ExcursionDaoInterface {

    private Connection connection = getConnection();

    private final static String INSERT_NEW_EXCURSION = "INSERT INTO excursions (id_ctry_for_exc, excursionName, excursionCoast) VALUES (?, ?, ?)";
    private final static String SELECT_EXCURSION_BY_ID = "SELECT * FROM excursions WHERE id_exc = ?";
    private final static String UPDATE_EXCURSION_BY_ID = "UPDATE excursions SET id_ctry_for_exc = ?, excursionName = ?, excursionCoast = ? WHERE id_exc = ? LIMIT 1";
    private final static String DELETE_EXCURSION_BY_ID = "DELETE FROM excursions WHERE id_exc = ?";
    private final static String SELECT_EXCURSION_BY_COUNTRY_ID = "SELECT id_ctry_for_exc, excursionName, excursionCoast FROM excursions WHERE id_ctry_for_exc = ?";

    @Override
    public Excursion create(Excursion excursion) {
        PreparedStatement statement = null;
        try {
            statement = connection.prepareStatement(INSERT_NEW_EXCURSION);
            statement.setInt(1, excursion.getExcursionCountryID());
            statement.setString(2, excursion.getExcursionName());
            statement.setInt(3, excursion.getExcursionCoast());
            statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            try {
                if (statement != null) { statement.close(); }
            } catch (SQLException e) {}
        }
        return excursion;
    }

    @Override
    public Excursion read(Integer id) {
        Excursion excursion = null;
        PreparedStatement statement = null;
        try {
            statement = connection.prepareStatement(SELECT_EXCURSION_BY_ID);
            statement.setInt(1, id);
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
    public void update(Excursion excursion, Integer id) {
        PreparedStatement statement = null;
        try {
            statement = connection.prepareStatement(UPDATE_EXCURSION_BY_ID);
            statement.setInt(1, excursion.getExcursionCountryID());
            statement.setString(2, excursion.getExcursionName());
            statement.setInt(3, excursion.getExcursionCoast());
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
            statement = connection.prepareStatement(DELETE_EXCURSION_BY_ID);
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
    public List<Excursion> getExcursionsByCountry(int id) {
        List<Excursion> excursionList = new ArrayList<>();
        Excursion excursion = null;
        PreparedStatement statement = null;
        try {
            statement = connection.prepareStatement(SELECT_EXCURSION_BY_COUNTRY_ID);
            statement.setInt(1, id);
            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()) {
                excursion = new Excursion();
                excursion.setExcursionCountryID(resultSet.getInt("id_ctry_for_exc"));
                excursion.setExcursionName(resultSet.getString("excursionName"));
                excursion.setExcursionCoast(resultSet.getInt("excursionCoast"));
                excursionList.add(excursion);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            try {
                if (statement != null) {statement.close();}
            } catch (SQLException e) {}
        }
        return excursionList;
    }
}
