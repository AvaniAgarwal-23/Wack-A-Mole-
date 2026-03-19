package pokemon;
public abstract class Grass extends Pokemon {
    public Grass(String name, int health) {
        super(name, health);
    }
    @Override
    public String getType() {
        return "Grass";
    }
}