package Things;

public abstract class Vehicle extends GarageItem {
    private int power;

    public Vehicle(String name, double size, int power){
        super(name, size);
        this.power = power;
    }

    public abstract void engineOn();
    public abstract void engineOff();

    @Override
    public String toString() {
        return super.toString() + ", power: " + power + "PS";
    }
}
