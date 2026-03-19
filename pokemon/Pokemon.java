package pokemon;

public abstract class Pokemon {
    private String name;
    private int health;

    public Pokemon(String name, int health) {
        this.name = name;
        this.health = health;
    }
    public abstract String getType();
    public String getName() {
        return name;
    }
    public int getHealth() {
        return health;
    }
    public void setHealth(int health) {
        this.health = health;
    }
    @Override
    public String toString() {
        return name + " (" + getType() + ", HP:" + health + ")";
    }
}