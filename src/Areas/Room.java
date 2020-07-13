package Areas;

import Others.Person;

public abstract class Room {
    private int id;
    private int building;
    private int housingDev;
    private double size;
    private Person renter;

    private static int idGenerator = 1;

    public Room(double size){
        this.size = size;
        this.id = idGenerator;
        idGenerator++;
        this.renter = null;
    }

    public abstract void clear();
    public abstract void showInfo();
    public abstract String sortedContentstoString(String tabulation);

    public Person getRenter(){ return renter; }
    public void setRenter(Person person) { this.renter = person;}

    public int getId() { return id; }

    public int getHousingDev(){
        return housingDev;
    }
    public void setHousingDev(int housingDev){
        this.housingDev = housingDev;
    }

    public double getSize(){
        return size;
    }

    public int getBuilding(){
        return building;
    }
    public void setBuilding(int id){
        this.building = id;
    }

    @Override
    public String toString() {
        return "No. " + id + ", size " + size + "m3";
    }
}
