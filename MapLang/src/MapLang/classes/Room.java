package MapLang.classes;

import java.util.ArrayList;

public class Room {
    private String id;
    private ArrayList<String> contentIds;
    private ArrayList<Tuple<Float,Float>> coordinates;
    public Room (String id) {
        this.id = id;
    }

    public String getId() {
        return id;
    }

    public void setContentIds(ArrayList<String> contentIds) {
        this.contentIds = contentIds;
    }

    public ArrayList<String> getContentIds() {
        return contentIds;
    }

    public void appendContentIds(ArrayList<String> ids)
    {
        if(this.contentIds == null || this.contentIds.isEmpty()) {
            setContentIds(ids);
        }
        else {
            this.contentIds.addAll(ids);
        }
    }

    public void appendCoordinates(ArrayList<Tuple<Float,Float>> coordinates)
    {
        if(this.coordinates == null || this.coordinates.isEmpty()) {
            setCoordinates(coordinates);
        }
        else {
            this.coordinates.addAll(coordinates);
        }
    }

    public void setCoordinates(ArrayList<Tuple<Float, Float>> coordinates) {
        this.coordinates = coordinates;
    }
}
