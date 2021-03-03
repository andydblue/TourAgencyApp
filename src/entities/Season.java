package entities;

public enum Season {
    WINTER("зима"),
    SPRING("весна"),
    SUMMER("лето"),
    AUTUMN("осень");

    private String name;

    Season (String name) {
        this.name = name;
    }

    public static Season getSeasonByName(String s) {
        for (Season season : Season.values()) {
            if (season.name.equals(s)) {
                return season;
            }
        }
        return null;
    }

    public String getName() {
        return name;
    }
}
