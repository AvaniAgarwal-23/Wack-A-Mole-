package pokemon;
public abstract class Water extends Pokemon {
    public Water(String name, int health) {
        super(name, health);
    }
    @Override
    public String getType() {
        return "Water";
    }
}