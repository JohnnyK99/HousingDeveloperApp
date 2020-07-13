package Things;

public class Motorcycle extends Vehicle implements Driving {
    private double acceleration;

    public Motorcycle(String name, double size, int power, double acceleration){
        super(name, size, power);
        this.acceleration = acceleration;
    }

    @Override
    public void engineOn() {
        System.out.println("Turning on the motorcycle engine.");
    }

    @Override
    public void engineOff() {
        System.out.println("Turning off the motorycle engine.");
    }

    @Override
    public void changeTires() {
        System.out.println("Changing tires in the motorocycle.");
    }

    @Override
    public String toString() {
        return "Motorcycle " + super.toString() + ", 0-100 km/h: " + acceleration + "s.";
    }
}
