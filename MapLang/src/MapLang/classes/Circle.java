package MapLang.classes;

import java.util.ArrayList;

public class Circle extends Shape{
    public float getRadius() {
        return radius;
    }

    float radius;

    public Circle(String id, float r, Relation relation) {
        super(id);
        this.coordinates = new ArrayList<>();
        this.radius = r;
        switch (relation.getType()) {
            case COORDINATE:
                Tuple<Float, Float> tuple = new Tuple<Float, Float>(relation.getX(), relation.getY());
                this.coordinates.add(tuple);
                break;
            case TWO_DIR_EXPLICIT:
                float x = relation.getX();
                float y =  relation.getY();
                String dir1 = relation.getDir1();
                float amount1 = relation.getAmount1();
                String dir2 = relation.getDir2();
                float amount2 = relation.getAmount2();
                Tuple<Float, Float> tuple1 = setRelatedXY(dir1, amount1, x, y);
                tuple1 = setRelatedXY(dir2, amount2, tuple1.x, tuple1.y);
                this.coordinates.add(tuple1);
                break;
            case SINGLE_DIR_EXPLICIT:
                Tuple<Float, Float> tuple3 = setRelatedXY(relation.getDir1(),
                        relation.getAmount1(), relation.getX(), relation.getY());
                this.coordinates.add(tuple3);
                break;
        }
    }

    public Circle(String id, float r, Tuple<Float, Float> coordinate) {
        super(id);
        this.coordinates = new ArrayList<>();
        this.radius = r;
        this.coordinates.add(coordinate);
    }

    private Tuple<Float, Float> setRelatedXY(String dir, float amount, float x, float y) {
        switch (dir) {
            case "left":
                x = x - amount;
                break;
            case "right":
                x = x + amount;
                break;
            case "down":
                y = y - amount;
                break;
            case "up":
                y = y+ amount;
                break;
        }
        return new Tuple<Float, Float>(x, y);
    }

}
