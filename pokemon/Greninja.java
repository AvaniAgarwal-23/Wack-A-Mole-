package pokemon;
public class Greninja extends Water {
    private int specialMoveDamage;
    public Greninja(String name, int health, int specialMoveDamage) {
        super(name, health);
        this.specialMoveDamage = specialMoveDamage;
    }
}