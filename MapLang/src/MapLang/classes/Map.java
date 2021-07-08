package MapLang.classes;

import java.util.Hashtable;

public class Map {
    private String id;
    private Hashtable<Integer, Floor> floors = new Hashtable<>();


    public Map(String id) {
        this.id = id;
    }

    public String getId() {
        return id;
    }

    public Hashtable<Integer, Floor> getFloors() {
        return floors;
    }
}
