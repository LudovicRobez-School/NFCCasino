package fr.Chips.Ressources;

/**
 * Created by rl613611 on 17/01/2017.
 */
public enum Chip {
    BLACK(100,""),
    ORANGE(50,""),
    GREEN(25,""),
    BLUE(10,""),
    RED(5,""),
    WHITE(1,"");

    private final int value;
    private final String picture;

    Chip(int value, String picture) {
        this.value = value;
        this.picture = picture;
    }

    public int getValue() {
        return value;
    }

    public String getPicture() {
        return picture;
    }
}
