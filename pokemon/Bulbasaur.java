package pokemon;
public class Bulbasaur extends Grass {
    private int specialMoveDamage;
    public Bulbasaur(String name, int health, int specialMoveDamage) {
        super(name, health);
        this.specialMoveDamage = specialMoveDamage;
    }
}