package Things;

public class OffRoadCar extends Vehicle implements Driving {
    boolean winch;

    public OffRoadCar(String name, double size, int power, boolean winch){
        super(name, size, power);
        this.winch = winch;
    }

    @Override
    public void engineOn() {
        System.out.println("Turning on the off-road car engine.");
    }

    @Override
    public void engineOff() {
        System.out.println("Turning off the off-road car engine.");
    }

    @Override
    public void changeTires() {
        System.out.println("Changing tires in the off-road car.");
    }

    @Override
    public String toString() {
        return "Off-road car " + super.toString() + (winch ? ", has a winch.":", doesn't have a winch.");
    }

}
