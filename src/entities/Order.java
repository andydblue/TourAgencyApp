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

    public Order(long orderNumber, String customer, Country country, Hotel hotel, Excursion excursion, long totalCoast) {
        this.orderNumber = orderNumber;
        this.customer = customer;
        this.country = country;
        this.hotel = hotel;
        this.excursion = excursion;
        this.totalCoast = totalCoast;
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

    @Override
    public boolean equals(Object obj) {
        if (obj == null || obj.getClass() != this.getClass()) {
            return false;
        } else {
            Order o = (Order) obj;
            return o.customer.equals(this.customer)&&o.country.getCountryName().equals(this.country.getCountryName())&&o.hotel.getHotelName().equals(this.hotel.getHotelName())&&o.excursion.getExcursionName().equals(this.excursion.getExcursionName())&&o.totalCoast==this.totalCoast&&o.orderNumber==this.orderNumber;
        }
    }

    @Override
    public int hashCode() {
        return customer.hashCode()+country.hashCode()+hotel.hashCode()+excursion.hashCode();
    }
}
