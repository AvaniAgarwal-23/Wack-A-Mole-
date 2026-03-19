package game.data;

import java.io.Serializable;

public class PlayerRecord implements Serializable {

    private static final long serialVersionUID = 1L;

    private String name;
    private int points;

    public PlayerRecord(String name, int points) {
        this.name = name;
        this.points = points;
    }
    public String getName() {
        return name;
    }
    public int getPoints() {
        return points;
    }
    @Override
    public String toString() {
        return name + " - " + points;
    }
}