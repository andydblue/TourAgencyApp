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

    public Country(int id,String countryName, int flightCoast, Season countrySeason ) {
        this.id = id;
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

    @Override
    public boolean equals(Object obj) {
        if (obj == null || obj.getClass() != this.getClass()) {
            return false;
        } else {
            Country c = (Country) obj;
            return this.getCountryName().equals(c.getCountryName()) && this.getId() == c.getId() && this.getFlightCoast() == c.getFlightCoast();
        }
    }

    @Override
    public int hashCode() {
        return countryName.hashCode()+countrySeason.hashCode()+flightCoast+id;
    }
}
