package tests;

import business.implementations.CountryImpl;
import dao.connection.ConnectionSetUp;
import entities.Country;
import entities.Season;
import org.testng.Assert;
import org.testng.annotations.*;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class TestCountryImpl {

    private final CountryImpl testCountry = new CountryImpl();
    private Connection connection;
    private List<Country> tableData;

    @BeforeTest
    void setConnection() {
        ConnectionSetUp connectionSetUp = new ConnectionSetUp();
        connection = connectionSetUp.getConnection();
    }

    @BeforeClass
    private void addExpectedTableData() {
        tableData = new ArrayList<>();
        tableData.add(new Country(1,"Абхазия", 30000, Season.SUMMER));
        tableData.add(new Country(2,"Вьетнам", 40200, Season.WINTER));
        tableData.add(new Country(3,"Тайланд", 45000, Season.WINTER));
        tableData.add(new Country(4,"Черногория", 25000, Season.SUMMER));
        tableData.add(new Country(5,"Швеция", 28000, Season.SPRING));
        tableData.add(new Country(6,"Финляндия", 15000, Season.AUTUMN));
        tableData.add(new Country(7,"Германия", 32000, Season.AUTUMN));
        PreparedStatement statement = null;
        try {
            statement = connection.prepareStatement("DELETE FROM countries");
            statement.executeUpdate();
            for (Country c : tableData) {
                statement = connection.prepareStatement("INSERT INTO countries SET id_ctry = ?, countryName = ?, season = ?, flightCoast = ?");
                statement.setInt(1, c.getId());
                statement.setString(2, c.getCountryName());
                statement.setString(3, c.getSeason());
                statement.setInt(4, c.getFlightCoast());
                statement.executeUpdate();
            }
        } catch (SQLException e) {e.printStackTrace();}
        finally {
            try {
                if (statement != null) { statement.close(); }
            } catch (SQLException e) {e.printStackTrace();}
        }
    }

   @AfterClass
    private void rollbackCountryChanges() {
       PreparedStatement statement = null;
       try {
           statement = connection.prepareStatement("DELETE FROM countries");
           statement.executeUpdate();
           statement = connection.prepareStatement("INSERT INTO countries (id_ctry, countryName, season, flightCoast) VALUES (1, 'Греция', 'лето', 25000), (2, 'Грузия', 'зима', 35200), (3, 'Египет', 'зима', 40000), (4, 'Италия', 'лето', 37000), (5, 'Испания', 'весна', 50000), (6, 'Франция', 'осень', 62000), (7, 'Турция', 'осень', 42000)");
           statement.executeUpdate();
       } catch (SQLException e) {e.printStackTrace();}
       finally {
           try {
               if (statement != null) { statement.close(); }
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

    @DataProvider (name = "testBySeasonData")
    public Object[][] testBySeasonData() {
        List<Country> autumn = new ArrayList<>();
        Collections.addAll(autumn, tableData.get(5), tableData.get(6));
        List<Country> winter = new ArrayList<>();
        Collections.addAll(winter, tableData.get(1), tableData.get(2));
        List<Country> spring = new ArrayList<>();
        spring.add(tableData.get(4));
        List<Country> summer = new ArrayList<>();
        Collections.addAll(summer, tableData.get(0), tableData.get(3));
        return new Object[][] {{Season.AUTUMN, autumn}, {Season.SPRING, spring}, {Season.SUMMER, summer}, {Season.WINTER, winter}};
    }

    @Test
    public void testGetAllCountries() {
        List<Country> actualData = testCountry.getAllCountries();
        Assert.assertNotNull(actualData);
        Assert.assertEquals(actualData, tableData);
    }

    @Test (dataProvider = "testBySeasonData")
    public void testGetCountriesBySeason(Season season, List<Season> expected) {
        List<Country> actualData = testCountry.getCountriesBySeason(season);
        Assert.assertEquals(actualData, expected);
    }

}
