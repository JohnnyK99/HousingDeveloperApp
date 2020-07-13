package Others;

import Areas.*;
import Things.Vehicle;

public class Lease implements Comparable<Lease> {
    private Person renter;
    private Room room;
    private int start;
    private int end;

    public Lease(Person renter, Room room, int start){
        this.renter = renter;
        this.room = room;
        this.start = start;
        this.end = start + 30;
        room.setRenter(renter);
    }

    public void leaseExpiration(int day){
        renter.addFile(room,day);
    }

    public boolean clearing(int day){
        if(room instanceof ParkingSpace){
            int i = 0;

            while(i<((ParkingSpace) room).getContents().size()){
                if(((ParkingSpace) room).getContents().get(i) instanceof Vehicle){
                    ((ParkingSpace) room).getContents().remove(i);
                    renter.removeFile(room);
                    end = day + 60;
                    return false;
                }
                else
                    i++;
            }
        }
        room.clear();
        return true;
    }

    @Override
    public int compareTo(Lease lease) {
        if(this.getRoom().getSize() < lease.getRoom().getSize())
            return -1;
        else
            return 1;
    }

    public Room getRoom(){
        return this.room;
    }

    public int getEnd() { return end; }
    public void setEnd(int end) { this.end = end; }

    @Override
    public String toString() {
        return room + " is leased from day " + start + " to day " + end + ".";
    }
}
