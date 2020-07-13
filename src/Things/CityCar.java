package Things;

public class CityCar extends Vehicle implements Driving {
    private double fuelConsumption;

    public CityCar(String name, double size, int power, double fuelConsumption){
        super(name, size, power);
        this.fuelConsumption = fuelConsumption;
    }

    @Override
    public void engineOn() {
        System.out.println("Turning on the city car engine.");
    }

    @Override
    public void engineOff() {
        System.out.println("Turning off the city car engine.");
    }

    @Override
    public void changeTires() {
        System.out.println("Changing tires in the city car.");
    }

    @Override
    public String toString() {
        return "City car " + super.toString() + ", fuel consumption: " + fuelConsumption + "l/100km";
    }
}
