package tests;

import business.implementations.HotelImpl;
import dao.connection.ConnectionSetUp;
import entities.Hotel;
import entities.StarsRating;
import org.testng.Assert;
import org.testng.annotations.*;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class TestHotelImpl {

    private final HotelImpl hotelImpl = new HotelImpl();
    private Connection connection;
    private List<Hotel> tableData;

    @BeforeTest
    void setConnection() {
        ConnectionSetUp connectionSetUp = new ConnectionSetUp();
        connection = connectionSetUp.getConnection();
    }

    @BeforeClass
    private void addExpectedTableData() {
        tableData = new ArrayList<>();
        tableData.add(new Hotel(1, "hotel1", StarsRating.FIVE, 56000));
        tableData.add(new Hotel(2, "hotel2", StarsRating.THREE, 43000));
        tableData.add(new Hotel(3, "hotel3", StarsRating.THREE, 18000));
        tableData.add(new Hotel(4, "hotel4", StarsRating.FIVE, 85000));
        tableData.add(new Hotel(4, "hotel5", StarsRating.FIVE, 66000));
        tableData.add(new Hotel(4, "hotel6", StarsRating.TWO, 17000));
        tableData.add(new Hotel(5, "hotel7", StarsRating.FOUR, 83000));
        tableData.add(new Hotel(6, "hotel8", StarsRating.FOUR, 35000));
        tableData.add(new Hotel(6, "hotel9", StarsRating.TWO, 32000));
        tableData.add(new Hotel(7, "hotel10", StarsRating.FIVE, 114000));
        PreparedStatement statement = null;
        try {
            statement = connection.prepareStatement("DELETE FROM hotels");
            statement.executeUpdate();
            statement = connection.prepareStatement("INSERT INTO hotels SET id_ctry_for_htl = ?, hotelName = ?, starsRating = ?, roomCoast = ?");
            for (Hotel h : tableData) {
                statement.setInt(1, h.getHotelCountryID());
                statement.setString(2, h.getHotelName());
                statement.setString(3, h.getStarsRating());
                statement.setInt(4, h.getRoomCoast());
                statement.executeUpdate();
            }
        } catch (SQLException e) {e.printStackTrace();}
        finally {
            try {
                if (statement != null) {statement.close();}
            } catch (SQLException e) {e.printStackTrace();}
        }
    }

    @AfterClass
    private void rollbackHotelChanges() {
        PreparedStatement statement = null;
        try {
            statement = connection.prepareStatement("DELETE FROM hotels");
            statement.executeUpdate();
            statement = connection.prepareStatement("INSERT INTO hotels (id_htl, id_ctry_for_htl, hotelName, starsRating, roomCoast) VALUES (1, 2, 'Tiflis Palace', '4', 57000), (2, 2, 'Lopota Lake Resort & Spa', '5', 87000), (3, 2, 'Hotel Terrace Kutaisi', '3', 29000), (4, 1, 'Electra Metropolis', '5', 122000), (5, 1, 'Acropolis View Hotel', '3', 55000), (6, 1, 'Phidias Piraeus Hotel', '2', 35800), (7, 3, 'Steigenberger Aldau Beach Hotel', '5', 107000), (8, 3, 'Sofitel Cairo Nile El Gezirah', '5', 59000), (9, 3, 'Tolip Resort El Galala Majestic', '5', 97000), (10, 7, 'Celal Aga Konagi Metro Hotel', '5', 41000), (11, 7, 'Sura Design Hotel & Suites', '4', 73000), (12, 7, 'Sealife Family Resort Hotel', '5', 65000), (13, 5, 'Riu Plaza España', '4', 113000), (14, 5, 'Palm Oasis Maspalomas', '3', 65000), (15, 5, 'El Avenida Palace', '4', 76000), (16, 4, 'Hotel Giolli Nazionale', '3', 40000), (17, 4, 'Hotel Abbazia', '3', 62000), (18, 4, 'The Square Milano Duomo', '4', 150000), (19, 6, 'Hôtel des Etats-Unis', '2', 68000), (20, 6, 'Radisson Blu Hotel, Lyon', '4', 109000), (21, 6, 'citizenM Paris Gare de Lyon', '4', 62800)");
            statement.executeUpdate();
        } catch (SQLException e) {e.printStackTrace();}
        finally {
            try {
                if (statement != null) {statement.close();}
            } catch (SQLException e) {e.printStackTrace();}
        }
    }

    @AfterTest
    void closeConnection() {
        try {
            if (connection != null) {
                connection.close();
            }
        } catch (SQLException e) {e.printStackTrace();}
    }

    @DataProvider (name = "testById")
    public Object[][] testById() {
        return new Object[][] {
                {1, new ArrayList<>(tableData.subList(0, 1))},
                {2, new ArrayList<>(tableData.subList(1, 2))},
                {3, new ArrayList<>(tableData.subList(2, 3))},
                {4, new ArrayList<>(tableData.subList(3, 6))},
                {5, new ArrayList<>(tableData.subList(6, 7))},
                {6, new ArrayList<>(tableData.subList(7, 9))},
                {7, new ArrayList<>(tableData.subList(9, 10))}
        };
    }

    @Test (dataProvider = "testById")
    public void testGetHotelsByCountryID(int id, List<Hotel> expected) {
        List<Hotel> actualData = hotelImpl.getHotelsByCountryID(id);
        Assert.assertEquals(actualData, expected);
    }
}
