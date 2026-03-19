package pokemon;

public class Vulpix extends Fire {
    private int specialMoveDamage;
    public Vulpix(String name, int health, int specialMoveDamage) {
        super(name, health);
        this.specialMoveDamage = specialMoveDamage;
    }
}
