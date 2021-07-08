package MapLang.classes;
import java.util.*;

public class Template {
    private String id;
    private ArrayList<String> contentIds;
    private ArrayList<Tuple<Float,Float>> coordinates;

    public Template(String id) {
        this.id = id;
        coordinates = new ArrayList<>();
    }

    public Template(String id, ArrayList<String> contentIds) {
        this.id = id;
        this.contentIds = contentIds;
        coordinates = new ArrayList<>();

    }

    public String getId() {
        return id;
    }
    public ArrayList<String> getContentIds(){
        if (this.contentIds == null) {
            this.contentIds = new ArrayList<>();
        }
        return this.contentIds;
    }
    public ArrayList<Tuple<Float,Float>> getCoordinates(){return this.coordinates;}

    private void setContentIds(ArrayList<String> ids) {
        this.contentIds = ids;
    }
    private void setCoordinates(ArrayList<Tuple<Float,Float>> coordinates) {
        this.coordinates = coordinates;
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



}
