package Areas;

import Things.GarageItem;

import java.util.ArrayList;
import java.util.Collections;

public class ParkingSpace extends Room {
    private ArrayList<GarageItem> contents;

    public ParkingSpace(double size){
        super(size);
        contents = new ArrayList<>();
    }

    public double freeSpace(){
        double sum = 0;
        for(GarageItem gItem : contents)
            sum += gItem.getSize();

        return super.getSize() - sum;
    }

    public void clear(){
        contents.clear();
        setRenter(null);
    }

    public void addThing(GarageItem gItem) {
        contents.add(gItem);
    }

    public void removeThing(GarageItem gItem) {
        contents.remove(gItem);
    }

    public void showContents(){
        int i = 0;
        for(GarageItem gItem : contents) {
            System.out.print(i++ + " ");
            System.out.println(gItem);
        }
    }

    public ArrayList<GarageItem> getContents(){
        return contents;
    }

    public void showInfo(){
        System.out.println(this);
        System.out.println(sortedContentstoString(""));
    }

    public String sortedContentstoString(String tabulation){
        ArrayList<GarageItem> tmp = contents;
        Collections.sort(tmp);

        StringBuilder sb = new StringBuilder();
        sb.append(tabulation).append("Contents: \n");
        if(contents.isEmpty())
            sb.append(tabulation).append("\tnone\n");
        else
            for(GarageItem gItem : tmp)
            sb.append(tabulation).append("\t").append(gItem).append("\n");

        return sb.toString();
    }

    public String toString(){
        return "Housing development " + getHousingDev() + ", building " + getBuilding() + ", parking space " + super.toString();
    }
}
