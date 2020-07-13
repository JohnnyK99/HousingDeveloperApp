package Things;

public class GarageItem implements Comparable<GarageItem>{
    private String name;
    private double size;

    public GarageItem(String name, double size){
        this.name = name;
        this.size = size;
    }

    @Override
    public int compareTo(GarageItem item) {
        if(this.size < item.getSize())
            return 1;
        else if (this.size > item.getSize())
            return -1;
        else
            return this.name.compareTo(item.getName());
    }

    public double getSize(){
        return size;
    }
    public String getName(){
        return name;
    }

    @Override
    public String toString() {
        return name + ", size " + size + " m3";
    }


}
