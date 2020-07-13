package Areas;

import java.util.HashMap;

public class Building {
    private int id;
    private int housingDev;

   private HashMap<Integer, Room> rooms;

   public Building(int apartmentsCount){
       rooms = new HashMap<>();

       for(int i = 0; i<apartmentsCount; i++){
           Room room = new Apartment(Math.round(Math.random()*100)+50);
           rooms.put(room.getId(), room);
       }

       for(int i = 0; i<apartmentsCount; i++){
           Room room = new ParkingSpace(Math.round(Math.random()*10)+5);
           rooms.put(room.getId(),room);
       }
   }

   public void setHousingDev(int housingDevId){
       this.housingDev = housingDevId;
       for(int room : rooms.keySet()){
           rooms.get(room).setHousingDev(housingDev);
       }
   }

   public void setId(int id){
       this.id = id;
       for(int room : rooms.keySet()){
           rooms.get(room).setBuilding(id);
       }
   }

    public HashMap<Integer, Room> getRooms() {
        return rooms;
    }

    public Room getRoom(int index){
       return rooms.get(index);
    }

    @Override
    public String toString() {
        return "Building " + id;
    }
}
