package entities;

public enum StarsRating {
    ONE("1"),
    TWO("2"),
    THREE("3"),
    FOUR("4"),
    FIVE("5");

    private String stars;

    public static StarsRating getStarsByName(String s) {
        for (StarsRating rate : StarsRating.values()) {
            if (rate.stars.equals(s)) {
                return rate;
            }
        }
        return null;
    }

    StarsRating (String stars) {
        this.stars = stars;
    }

    public String getStars() {
        return stars;
    }
}
