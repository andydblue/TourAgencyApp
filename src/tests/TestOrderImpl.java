package tests;

import business.implementations.OrderImpl;
import dao.connection.ConnectionSetUp;
import entities.*;
import org.testng.Assert;
import org.testng.annotations.*;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class TestOrderImpl {

    private final OrderImpl orderImpl = new OrderImpl();
    private Connection connection;

    @BeforeTest
    void setConnection() {
        ConnectionSetUp connectionSetUp = new ConnectionSetUp();
        connection = connectionSetUp.getConnection();
    }

    @BeforeClass
    private void addExpectedTableData() {
        PreparedStatement statement = null;
        try {
            statement = connection.prepareStatement("DELETE FROM orders");
            statement.executeUpdate();
        } catch (SQLException e) {e.printStackTrace();}
        finally {
            try {
                if (statement != null) {statement.close();}
            } catch (SQLException e) {e.printStackTrace();}
        }
    }

    @DataProvider (name = "testData")
    public Object[][] testData() {
        return new Object[][] {
                {45687995, new Order(45687995, "customer1", new Country("Турция",42000,Season.AUTUMN), new Hotel("Sura Design Hotel & Suites", StarsRating.FOUR, 73000), new Excursion("у меня ол инклюзив и трое детей", 0), 256000)},
                {35689416, new Order(35689416, "customer2", new Country("Италия",37000,Season.SUMMER), new Hotel("Hotel Abbazia", StarsRating.THREE,62000), new Excursion("шмоток много не бывает", 58000), 13400)},
                {5235841, new Order(5235841, "customer3", new Country("Франция",62000,Season.AUTUMN), new Hotel("Hôtel des Etats-Unis", StarsRating.TWO,68000), new Excursion("тур по винам", 12000), 130000)},
                {23568946, new Order(23568946, "customer4", new Country("Греция",25000,Season.SUMMER), new Hotel("Electra Metropolis", StarsRating.FIVE,122000), new Excursion("покупаем шубы и репродукции фреск", 12000), 97500)},
                {8653246, new Order(8653246, "customer5", new Country("Грузия",35200,Season.WINTER), new Hotel("Tiflis Palace", StarsRating.FOUR,57000), new Excursion("горнолыжный тур break a leg", 30000), 83500)},
                {2569444, null}
        };
    }

    @AfterClass
    private void rollbackOrderChanges() {
        PreparedStatement statement = null;
        try {
            statement = connection.prepareStatement("DELETE FROM orders");
            statement.executeUpdate();
            statement = connection.prepareStatement("INSERT INTO orders (id_order, orderNumber, customer, country, hotel, excursion, totalCoast) VALUES (2, 423739905, 'fse4 gf5', 'Италия', 'Hotel Giolli Nazionale', 'слушаем оперу (удиви своих знакомых)', 102000)");
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

    @Test (dataProvider = "testData")
    public void testAddGetRemoveByNumber(long orderNumber, Order order) {
        orderImpl.addNewOrder(order);
        Order read = orderImpl.getOrderByNumber(orderNumber);
        Assert.assertEquals(read, order);
        if (read != null) {
            boolean isRemoved = orderImpl.removeOrderByNumber(orderNumber);
            Assert.assertTrue(isRemoved);
            Order removed = orderImpl.getOrderByNumber(orderNumber);
            Assert.assertNull(removed);
        }
    }
}
