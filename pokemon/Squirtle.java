package pokemon;

public class Squirtle extends Water {
    private int specialMoveDamage;
    public Squirtle(String name, int health, int specialMoveDamage) {
        super(name, health);
        this.specialMoveDamage = specialMoveDamage;
    }
}