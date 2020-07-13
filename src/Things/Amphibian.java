package Things;

public class Amphibian extends Vehicle implements Swimming, Driving {
    private int seatCount;

    public Amphibian(String name, double size, int power, int seatCount){
        super(name, size, power);
        this.seatCount = seatCount;
    }

    @Override
    public void engineOn() {
        System.out.println("Turning on the amphibian engine.");
    }

    @Override
    public void engineOff() {
        System.out.println("Turning off the amphibian engine.");
    }

    @Override
    public String toString() {
        return "Amphibian " + super.toString() + ", number of seats: " + seatCount;
    }

    @Override
    public void changeTires() {
        System.out.println("Changing tires in the amphibian.");
    }

    @Override
    public int waterTemperature() {
        return (int)(Math.random()*30);
    }
}
