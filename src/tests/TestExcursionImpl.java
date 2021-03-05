package tests;

import business.implementations.ExcursionImpl;
import dao.connection.ConnectionSetUp;
import entities.Excursion;
import org.testng.Assert;
import org.testng.annotations.*;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class TestExcursionImpl {

    private final ExcursionImpl excursionImpl = new ExcursionImpl();
    private Connection connection;
    private List<Excursion> tableData;

    @BeforeTest
    void setConnection() {
        ConnectionSetUp connectionSetUp = new ConnectionSetUp();
        connection = connectionSetUp.getConnection();
    }

    @BeforeClass
    private void addExpectedTableData() {
        tableData = new ArrayList<>();
        tableData.add(new Excursion(1, "exc1", 8800));
        tableData.add(new Excursion(1, "exc2", 6900));
        tableData.add(new Excursion(1, "exc3", 8960));
        tableData.add(new Excursion(2, "exc4", 4500));
        tableData.add(new Excursion(2, "exc5", 3200));
        tableData.add(new Excursion(3, "exc6", 2500));
        tableData.add(new Excursion(3, "exc7", 1230));
        tableData.add(new Excursion(3, "exc8", 5692));
        tableData.add(new Excursion(4, "exc9", 6857));
        tableData.add(new Excursion(4, "exc10", 5000));
        tableData.add(new Excursion(5, "exc11", 10000));
        tableData.add(new Excursion(5, "exc12", 15200));
        tableData.add(new Excursion(6, "exc13", 20320));
        tableData.add(new Excursion(6, "exc14", 15569));
        tableData.add(new Excursion(7, "exc15", 14016));
        PreparedStatement statement = null;
        try {
            statement = connection.prepareStatement("DELETE FROM excursions");
            statement.executeUpdate();
            statement = connection.prepareStatement("INSERT INTO excursions SET id_ctry_for_exc = ?, excursionName = ?, excursionCoast = ?");
            for (Excursion e : tableData) {
                statement.setInt(1, e.getExcursionCountryID());
                statement.setString(2, e.getExcursionName());
                statement.setInt(3, e.getExcursionCoast());
                statement.executeUpdate();
            }
        } catch (SQLException e) {e.printStackTrace();}
        finally {
            try {
                if (statement != null) {
                    statement.close();
                }
            } catch (SQLException e) {e.printStackTrace();}
        }
    }

    @AfterClass
    private void rollbackExcursionChanges() {
        PreparedStatement statement = null;
        try {
            statement = connection.prepareStatement("DELETE FROM excursions");
            statement.executeUpdate();
            statement = connection.prepareStatement("INSERT INTO excursions (id_exc, id_ctry_for_exc, excursionName, excursionCoast) VALUES (1, 1, 'кушаем лепешечки, валяемся у моря, пляшем сиртаки', 0), (2, 1, 'покупаем шубы и репродукции фреск', 12000), (3, 1, 'катаемся по островам, фоткаемся на фоне развалин, дышим античностью', 35000), (4, 2, 'ай, зачем тебе эти экскурсии? Дядя Гиви сам тебе все покажет', 0), (5, 2, 'горнолыжный тур break a leg', 30000), (6, 2, 'гастротур непросыхайка', 60000), (7, 3, 'песок на зубах: пирамиды, гробницы, храмы', 45000), (8, 3, 'шоп-тур: Наташа мы сделаем скидку', 8000), (9, 3, 'греем жопки у моря, ловим рыбок панамками', 0), (10, 4, 'шмоток много не бывает', 58000), (11, 4, 'гуляем пешочком, ищем нормальную пиццу как у мамы', 0), (12, 4, 'слушаем оперу (удиви своих знакомых)', 25000), (13, 5, 'футбол, коррида, парк аттракционов', 36000), (14, 5, 'верни мне мой 2007: тур по готической архитектуре', 27000), (15, 5, 'боремся с ветряными мельницами', 0), (16, 6, 'огни Парижа', 70000), (17, 6, 'тур по винам', 12000), (18, 6, 'берем палатку и идем в Лувр', 20000), (19, 7, 'у меня ол инклюзив и трое детей', 0), (20, 7, 'а ты купи слона - тур по рынкам', 26000), (21, 7, 'там нет моря и придется ходить, оно вам надо?', 45000)");
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

    @DataProvider (name = "testByIdData")
    public Object[][] testByIdData() {
        return new Object[][] {
                {1, new ArrayList<>(tableData.subList(0,3))},
                {2, new ArrayList<>(tableData.subList(3,5))},
                {3, new ArrayList<>(tableData.subList(5,8))},
                {4, new ArrayList<>(tableData.subList(8, 10))},
                {5, new ArrayList<>(tableData.subList(10, 12))},
                {6, new ArrayList<>(tableData.subList(12, 14))},
                {7, new ArrayList<>(tableData.subList(14, 15))},
                {0, new ArrayList<>()},
                {-15, new ArrayList<>()},
                {2000000003, new ArrayList<>()}};
    }

    @Test (dataProvider = "testByIdData")
    public void testGetExcursionsByCountryID(int id, List<Excursion> expected) {
        List<Excursion> actualData = excursionImpl.getExcursionsByCountryID(id);
        Assert.assertEquals(actualData, expected);
    }
}
