package entities;

public class Excursion {
    private String excursionName;
    private int excursionCoast;
    private int excursionCountryID;

    public Excursion() {}

    public Excursion(String excursionName, int excursionCoast) {
        this.excursionName = excursionName;
        this.excursionCoast = excursionCoast;
    }

    public int getExcursionCoast() {
        return excursionCoast;
    }

    public String getExcursionName() {
        return excursionName;
    }

    public int getExcursionCountryID() {
        return excursionCountryID;
    }

    public void setExcursionCountryID(int excursionCountryID) {
        this.excursionCountryID = excursionCountryID;
    }

    public void setExcursionCoast(int excursionCoast) {
        this.excursionCoast = excursionCoast;
    }

    public void setExcursionName(String excursionName) {
        this.excursionName = excursionName;
    }
}
