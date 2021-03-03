package entities;

public class Order {
    private long orderNumber;
    private String customer;
    private Country country;
    private Excursion excursion;
    private Hotel hotel;
    private long totalCoast;

    public Order() {}

    public Order(String customer, Country country, Excursion excursion, Hotel hotel) {
        this.customer = customer;
        this.country = country;
        this.excursion = excursion;
        this.hotel = hotel;
        orderNumber = Math.abs(customer.hashCode()+country.hashCode());
        totalCoast = country.getFlightCoast()+hotel.getRoomCoast()+excursion.getExcursionCoast();
    }

    public long getOrderNumber() {
        return orderNumber;
    }

    public void setOrderNumber(long orderNumber) {
        this.orderNumber = orderNumber;
    }

    public String getCustomer() {
        return customer;
    }

    public void setCustomer(String customer) {
        this.customer = customer;
    }

    public Country getCountry() {
        return country;
    }

    public void setCountry(Country country) {
        this.country = country;
    }

    public Excursion getExcursion() {
        return excursion;
    }

    public void setExcursion(Excursion excursion) {
        this.excursion = excursion;
    }

    public Hotel getHotel() {
        return hotel;
    }

    public void setHotel(Hotel hotel) {
        this.hotel = hotel;
    }

    public long getTotalCoast() {
        return totalCoast;
    }

    public void setTotalCoast(long totalCoast) {
        this.totalCoast = totalCoast;
    }
}
