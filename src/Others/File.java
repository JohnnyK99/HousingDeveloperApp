package Others;

import Areas.Room;

public class File {
    private Room room;
    private int issueDate;

    public File(Room room, int day){
        this.room = room;
        this.issueDate = day;
    }

    public Room getRoom(){
        return room;
    }

    @Override
    public String toString() {
        return room + ", day of issue: " + issueDate + ".";
    }
}
