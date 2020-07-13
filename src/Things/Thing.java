package Things;

public class Thing extends GarageItem {

    public Thing(String name, double size){
        super(name, size);
    }

    public String getName(){
        return super.getName();
    }

    public double getSize(){
        return super.getSize();
    }

    public String toString(){
        return super.toString();
    }

}
