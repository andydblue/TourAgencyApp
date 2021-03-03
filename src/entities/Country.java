package entities;

public class Country {
    private int id;
    private String countryName;
    private int flightCoast;
    private Season countrySeason;

    public Country(){}

    public Country(String countryName, int flightCoast, Season countrySeason) {
        this.countryName = countryName;
        this.flightCoast = flightCoast;
        this.countrySeason = countrySeason;
    }

    public String getCountryName() {
        return countryName;
    }

    public int getFlightCoast() {
        return flightCoast;
    }

    public String getSeason() {
        return countrySeason.getName();
    }

    public void setCountryName(String countryName) {
        this.countryName = countryName;
    }

    public void setFlightCoast(int flightCoast) {
        this.flightCoast = flightCoast;
    }

    public void setCountrySeason(Season countrySeason) {
        this.countrySeason = countrySeason;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
}
