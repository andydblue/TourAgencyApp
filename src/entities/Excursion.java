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

    public Excursion(int excursionCountryID, String excursionName, int excursionCoast) {
        this.excursionName = excursionName;
        this.excursionCoast = excursionCoast;
        this.excursionCountryID = excursionCountryID;
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

    @Override
    public boolean equals(Object obj) {
        if (obj == null || obj.getClass() != this.getClass()) {
            return false;
        } else {
            Excursion e = (Excursion) obj;
            return e.excursionCountryID == this.excursionCountryID && e.excursionName.equals(this.excursionName) && e.excursionCoast == this.excursionCoast;
        }
    }

    @Override
    public int hashCode() {
        return excursionName.hashCode();
    }
}
