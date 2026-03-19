package pokemon;
public class Pikachu extends Electric {
    private int specialMoveDamage;
    public Pikachu(String name, int health, int specialMoveDamage) {
        super(name, health);
        this.specialMoveDamage = specialMoveDamage;
    }
}