package pokemon;
public class Chikorita extends Grass {
    private int specialMoveDamage;
    public Chikorita(String name, int health, int specialMoveDamage) {
        super(name, health);
        this.specialMoveDamage = specialMoveDamage;
    }
}