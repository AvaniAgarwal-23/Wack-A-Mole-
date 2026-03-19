package pokemon;
public class Charmander extends Fire {
    private int specialMoveDamage;
    public Charmander(String name, int health, int specialMoveDamage) {
        super(name, health);
        this.specialMoveDamage = specialMoveDamage;
    }
}