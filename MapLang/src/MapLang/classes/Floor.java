package MapLang.classes;

import java.util.ArrayList;

public class Floor {

    private int floorNo;
    private ArrayList<Room> rooms = new ArrayList<>();
    private ArrayList<Door> doors = new ArrayList<>();
    private ArrayList<Window> windows = new ArrayList<>();
    private ArrayList<Exit> exits = new ArrayList<>();

    public Floor(int floorNo) {
        this.floorNo = floorNo;
    }

    public int getFloorNo() {
        return floorNo;
    }

    public ArrayList<Room> getRooms() {
        return rooms;
    }

    public ArrayList<Door> getDoors() {
        return doors;
    }

    public ArrayList<Window> getWindows() {
        return windows;
    }

    public ArrayList<Exit> getExits() {
        return exits;
    }
}
