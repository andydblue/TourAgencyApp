package swing;

import business.implementations.CountryImpl;
import business.implementations.ExcursionImpl;
import business.implementations.HotelImpl;
import business.implementations.OrderImpl;
import entities.*;

import javax.swing.*;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

public class TourApplication {

    private Object[][] countryArray;
    private final Object[] countryHeaders = {"id","Страна","Туристический сезон","Стоимость перелета"};
    private Object[][] hotelArray;
    private final Object[] hotelHeaders = {"Отель","Количество звезд","Стоимость проживания"};
    private Object[][] excursionArray;
    private final Object[] excursionHeaders = {"Экскурсионный тур","Стоимость тура"};
    private Object[][] orderArray;
    private final Object[] orderHeaders = {"Номер заказа", "Имя заказчика", "Страна", "Отель", "Экскурсионный тур", "Стоимость"};
    private JTable countryTable;
    private JTable hotelTable;
    private JTable excursionTable;
    private JTable orderTable;
    private JComboBox<String> seasonFilter;
    private JComboBox<String> starsFilter;
    private JTextField nameField;
    private JTextField lastNameField;
    private JLabel message;
    private JTextField numberField;
    private JLabel orderMessage;
    CountryImpl countryImpl = new CountryImpl();
    HotelImpl hotelImpl = new HotelImpl();
    ExcursionImpl excursionImpl = new ExcursionImpl();
    OrderImpl orderImpl = new OrderImpl();


    public static void main(String[] args) {
        TourApplication tourApplication = new TourApplication();
        tourApplication.buildGUI();
    }

    public void buildGUI() {
        JFrame frame = new JFrame("В бесконечность и далее!");                       // создали главную панель, на которую потом навешаем объектов
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);                            // при закрытии приложения программа остановится
        JTabbedPane jTabbedPane = new JTabbedPane();                                     // создаем панель для вкладок
        jTabbedPane.setPreferredSize(new Dimension(700,400));               // размер панели вкладок
        JPanel countrySet = new JPanel(new BorderLayout());                             // создаем сами панели-вкладки
        JPanel hotelSet = new JPanel(new BorderLayout());
        JPanel excursionSet = new JPanel(new BorderLayout());
        JPanel orderSet = new JPanel(new BorderLayout());

        countryArray = getCountryArray(countryImpl.getAllCountries());
        countryTable = new JTable();
        countryTable.getSelectionModel().setSelectionMode(ListSelectionModel.SINGLE_INTERVAL_SELECTION);
        countryTable.setModel(new MyTableModel(countryArray, countryHeaders));
        countryTable.getSelectionModel().addListSelectionListener(new CountrySelectListener());                        // установили прослушку за странами
        JScrollPane countryPane = new JScrollPane();
        countryPane.setViewportView(countryTable);
        countrySet.add(countryPane, BorderLayout.CENTER);
        JLabel filter = new JLabel("Фильтровать по туристическому сезону");
        seasonFilter = new JComboBox<>();
        seasonFilter.setModel(new DefaultComboBoxModel<>(new String[] {"нет","зима", "весна", "лето", "осень"}));
        seasonFilter.addActionListener(new CountryCheckBoxListener());                                                  // слушаем чекбокс-фильтр стран
        JPanel countryFilter = new JPanel();
        countryFilter.add(filter);
        countryFilter.add(seasonFilter);
        countrySet.add(countryFilter, BorderLayout.NORTH);

        hotelArray = new Object[0][0];
        hotelTable = new JTable();
        hotelTable.getSelectionModel().setSelectionMode(ListSelectionModel.SINGLE_INTERVAL_SELECTION);
        hotelTable.setModel(new MyTableModel(hotelArray, hotelHeaders));
        JScrollPane hotelPane = new JScrollPane();
        hotelPane.setViewportView(hotelTable);
        hotelSet.add(hotelPane,BorderLayout.CENTER);
        JLabel filterStars = new JLabel("Фильтровать по количеству звёзд");
        starsFilter = new JComboBox<>();
        starsFilter.setModel(new DefaultComboBoxModel<>(new String[] {"нет", "1", "2", "3", "4", "5"}));
        starsFilter.addActionListener(new HotelCheckBoxListener());                                                     // слушаем чек-бокс фильтр отелей
        JPanel hotelFilter = new JPanel();
        hotelFilter.add(filterStars);
        hotelFilter.add(starsFilter);
        hotelSet.add(hotelFilter, BorderLayout.NORTH);

        excursionArray = new Object[0][0];
        excursionTable = new JTable();
        excursionTable.getSelectionModel().setSelectionMode(ListSelectionModel.SINGLE_INTERVAL_SELECTION);
        excursionTable.setModel(new MyTableModel(excursionArray, excursionHeaders));
        JScrollPane excPane = new JScrollPane();
        excPane.setViewportView(excursionTable);
        excursionSet.add(excPane, BorderLayout.CENTER);

        orderArray = new Object[0][0];
        orderTable = new JTable(new MyTableModel(orderArray, orderHeaders));
        JScrollPane orderPane = new JScrollPane();
        orderPane.setViewportView(orderTable);
        orderSet.add(orderPane, BorderLayout.CENTER);
        JPanel configOrder = new JPanel();
        JLabel findOrder = new JLabel("Поиск заказа по номеру");
        numberField = new JTextField(20);
        JButton startFindOrder = new JButton("Искать заказ");
        startFindOrder.addActionListener(new FindOrderButtonListener());                                                                             // слушатель кнопки
        JButton removeOrder = new JButton("Удалить заказ");
        removeOrder.addActionListener(new RemoveOrderButtonListener());                                                                                // слушатель кнопки
        configOrder.add(findOrder);
        configOrder.add(numberField);
        configOrder.add(startFindOrder);
        configOrder.add(removeOrder);
        orderSet.add(configOrder, BorderLayout.NORTH);
        orderMessage = new JLabel(" ");
        orderSet.add(orderMessage, BorderLayout.SOUTH);

        jTabbedPane.addTab("Страны",countrySet);
        jTabbedPane.addTab("Отели", hotelSet);
        jTabbedPane.addTab("Экскурсии", excursionSet);
        jTabbedPane.addTab("Поиск заказа", orderSet);

        JPanel orderConfig = new JPanel();
        JLabel forName = new JLabel("Введите Ваше имя");
        JLabel forLastName = new JLabel("Введите Вашу фамилию");
        nameField = new JTextField(20);
        lastNameField = new JTextField(20);
        JButton setOrder = new JButton("Разместить заказ");
        setOrder.addActionListener(new SetOrderButtonListener());
        orderConfig.add(forName);
        orderConfig.add(nameField);
        orderConfig.add(forLastName);
        orderConfig.add(lastNameField);
        orderConfig.add(setOrder);

        JPanel forMessage = new JPanel();
        message = new JLabel("Сконфигурируйте свой тур, введите имя и разместите заказ");
        forMessage.add(message);

        frame.getContentPane().add(jTabbedPane, BorderLayout.SOUTH);
        frame.getContentPane().add(orderConfig, BorderLayout.CENTER);
        frame.getContentPane().add(forMessage, BorderLayout.NORTH);
        frame.setBounds(50,50, 700, 800);
        frame.pack();
        frame.setVisible(true);
    }

    public class RemoveOrderButtonListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            if (numberField.getText().isEmpty()) {
                orderMessage.setText("Введите номер заказа");
            } else {
                long num = Long.parseLong(numberField.getText());
                boolean flag = orderImpl.removeOrderByNumber(num);
                if (flag) {
                    orderMessage.setText("Заказ удален");
                } else {
                    orderMessage.setText("Заказ либо не существует, либо слишком дорог для нас");
                }
            }
        }
    }

    public class FindOrderButtonListener implements ActionListener {
        Order order;
        @Override
        public void actionPerformed(ActionEvent e) {
            if (numberField.getText().isEmpty()) {
                orderMessage.setText("Введите номер заказа");
            } else {
                long num = Long.parseLong(numberField.getText());
                order = orderImpl.getOrderByNumber(num);
                if (order == null) {
                    orderMessage.setText("Заказ не найден");
                } else {
                    orderArray = getOrderArray(order);
                    orderTable.setModel(new MyTableModel(orderArray, orderHeaders));
                }
            }
        }
    }

    public class SetOrderButtonListener implements ActionListener {
        String customerName;
        Country country;
        Hotel hotel;
        Excursion excursion;
        @Override
        public void actionPerformed(ActionEvent e) {
            if (countryTable.getSelectionModel().isSelectionEmpty() || hotelTable.getSelectionModel().isSelectionEmpty() || excursionTable.getSelectionModel().isSelectionEmpty() || nameField.getText().isEmpty() || lastNameField.getText().isEmpty()) {
                message.setText("Имя, фамилия, страна, отель и экскурсионный тур - обязательные параметры");
            } else {
                int selectedCountryRow = countryTable.getSelectedRow();
                String countryName = countryTable.getValueAt(selectedCountryRow, 1).toString();
                Season season = Season.getSeasonByName(countryTable.getValueAt(selectedCountryRow, 2).toString());
                int flightCoast = (int) countryTable.getValueAt(selectedCountryRow, 3);
                country = new Country(countryName, flightCoast, season);
                int selectedHotelRow = hotelTable.getSelectedRow();
                String hotelName = hotelTable.getValueAt(selectedHotelRow, 0).toString();
                StarsRating starsRating = StarsRating.getStarsByName(hotelTable.getValueAt(selectedHotelRow, 1).toString());
                int roomCoast = (int) hotelTable.getValueAt(selectedHotelRow, 2);
                hotel = new Hotel(hotelName, starsRating, roomCoast);
                int selectedExcRow = excursionTable.getSelectedRow();
                String excName = excursionTable.getValueAt(selectedExcRow, 0).toString();
                int excCoast = (int) excursionTable.getValueAt(selectedExcRow, 1);
                excursion = new Excursion(excName, excCoast);
                customerName = nameField.getText() + " " + lastNameField.getText();
                Order order = new Order(customerName, country, excursion, hotel);
                orderImpl.addNewOrder(order);
                message.setText("Ваш заказ размещен! Номер заказа: "+ order.getOrderNumber()+". Стоимость тура: "+order.getTotalCoast());
            }
        }
    }

    public class HotelCheckBoxListener implements ActionListener {
        int selectedCountryId;
        int searchingCountryId;
        String selectedStar;
        @Override
        public void actionPerformed(ActionEvent e) {
            try {
                selectedCountryId = countryTable.getSelectedRow();
                searchingCountryId = (int) countryTable.getValueAt(selectedCountryId, 0);
                selectedStar = starsFilter.getSelectedItem().toString();
                if (selectedStar.equals("нет")) {
                    hotelArray = getHotelArray(hotelImpl.getHotelsByCountryID(searchingCountryId));
                } else {
                    hotelArray = getHotelArray(hotelImpl.getHotelsByCountryAndStars(searchingCountryId, StarsRating.getStarsByName(selectedStar)));
                }
                hotelTable.setModel(new MyTableModel(hotelArray, hotelHeaders));
            } catch (IndexOutOfBoundsException i) {
                hotelTable.setModel(new MyTableModel(new Object[0][0], hotelHeaders));

            }
        }
    }

    public class CountryCheckBoxListener implements ActionListener {
        String selectedItem;
        @Override
        public void actionPerformed(ActionEvent e) {
            selectedItem = seasonFilter.getSelectedItem().toString();
            if (selectedItem.equals("нет")) {
                countryArray = getCountryArray(countryImpl.getAllCountries());
            } else {
                countryArray = getCountryArray(countryImpl.getCountriesBySeason(Season.getSeasonByName(selectedItem)));
            }
            countryTable.setModel(new MyTableModel(countryArray, countryHeaders));
        }
    }

    public class CountrySelectListener implements ListSelectionListener {
        int selectedID;
        int searchingID;
        @Override
        public void valueChanged(ListSelectionEvent e) {
            try {
                selectedID = countryTable.getSelectedRow();
                searchingID = (int) countryTable.getValueAt(selectedID, 0);
                hotelArray = getHotelArray(hotelImpl.getHotelsByCountryID(searchingID));
                excursionArray = getExcursionArray(excursionImpl.getExcursionsByCountryID(searchingID));
                hotelTable.setModel(new MyTableModel(hotelArray, hotelHeaders));
                excursionTable.setModel(new MyTableModel(excursionArray, excursionHeaders));
            } catch (IndexOutOfBoundsException i) {
                hotelTable.setModel(new MyTableModel(new Object[0][0],hotelHeaders));
                excursionTable.setModel(new MyTableModel(new Object[0][0], excursionHeaders));
            }
        }
    }

    public <T extends Country> Object[][] getCountryArray(List<T> country) {
        Object[][] countryArray = new Object[country.size()][4];
        for (int i = 0; i < country.size(); i++) {
            countryArray[i][0] = country.get(i).getId();
            countryArray[i][1] = country.get(i).getCountryName();
            countryArray[i][2] = country.get(i).getSeason();
            countryArray[i][3] = country.get(i).getFlightCoast();
        }
        return countryArray;
    }

    public <T extends Hotel> Object[][] getHotelArray(List<T> hotel) {
        Object[][] hotelArray = new Object[hotel.size()][3];
        for (int i = 0; i < hotel.size(); i++) {
            hotelArray[i][0] = hotel.get(i).getHotelName();
            hotelArray[i][1] = hotel.get(i).getStarsRating();
            hotelArray[i][2] = hotel.get(i).getRoomCoast();
        }
        return hotelArray;
    }

    public <T extends Excursion> Object[][] getExcursionArray(List<T> excursion) {
        Object[][] excursionArray = new Object[excursion.size()][2];
        for (int i = 0; i < excursion.size(); i++) {
            excursionArray[i][0] = excursion.get(i).getExcursionName();
            excursionArray[i][1] = excursion.get(i).getExcursionCoast();
        }
        return excursionArray;
    }

    public <T extends Order> Object[][] getOrderArray(Order order) {
        Object[][] orderArray = new Object[1][6];
        orderArray[0][0] = order.getOrderNumber();
        orderArray[0][1] = order.getCustomer();
        orderArray[0][2] = order.getCountry().getCountryName();
        orderArray[0][3] = order.getHotel().getHotelName();
        orderArray[0][4] = order.getExcursion().getExcursionName();
        orderArray[0][5] = order.getTotalCoast();
        return orderArray;
    }
}