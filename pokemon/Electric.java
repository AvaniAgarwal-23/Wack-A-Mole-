package pokemon;
public abstract class Electric extends Pokemon {
    public Electric(String name, int health) {
        super(name, health);
    }
    @Override
    public String getType() {
        return "Electric";
    }
}