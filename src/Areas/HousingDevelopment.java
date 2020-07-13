package Areas;

import java.util.ArrayList;
import java.util.Arrays;

public class HousingDevelopment {
    private ArrayList<Building> buildings;
    private int id;
    private int buildingId;

    private static int idGenerator = 0;

    public HousingDevelopment(){
        buildings = new ArrayList<>();
        this.id = idGenerator++;
        this.buildingId = 0;
    }

    public void addBuilding(Building b){
        b.setHousingDev(id);
        b.setId(buildingId++);
        buildings.add(b);
    }

    public void showFreeRooms(){
        for(Building b : buildings){
            Object[] keys = b.getRooms().keySet().toArray();
            Arrays.sort(keys);

            //I have to sort rooms from the hashmap
            for(Object key : keys){
                if(b.getRooms().get(key).getRenter() == null)
                    System.out.println(b.getRooms().get(key));
            }
            System.out.println("\n");
        }
    }

    public Building getBuilding(int index){
        return buildings.get(index);
    }

    @Override
    public String toString() {
        return "Housing development " + id;
    }
}
