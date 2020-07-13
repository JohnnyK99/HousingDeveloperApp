package Things;

public class Boat extends Vehicle implements Swimming {
    private int displacement;

    public Boat(String name, double size, int power, int displacement){
        super(name, size, power);
        this.displacement = displacement;
    }

    @Override
    public void engineOn() {
        System.out.println("Turning on the boat engine.");
    }

    @Override
    public void engineOff() {
        System.out.println("Turning off the boat engine.");
    }

    @Override
    public String toString() {
        return "Boat " +  super.toString() + ", displacement: " + displacement + "kg";
    }

    @Override
    public int waterTemperature() {
        return (int)(Math.random()*30);
    }
}
