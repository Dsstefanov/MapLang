package MapLang.classes;

import static java.lang.Math.*;

public class Rectangle extends Shape {
    private float x, y, width, height;

    //RectangleOne at x,y
    public Rectangle(String id, float x, float y, float float1, float float2) {
        super(id);
        this.x = x;
        this.y = y;
        this.width = float1;
        this.height = float2;
        setRectangleCoordinates();
    }

    //RectangleOne 1 dir
    public Rectangle(String id, float relatedX, float relatedY, String dir, float amount, float float1, float float2) {
        super(id);
        this.x = relatedX;
        this.y = relatedY;
        setRelatedXY(dir, amount);
        this.width = float1;
        this.height = float2;
        setRectangleCoordinates();

    }

    //RectangleOne 2 dirs
    public Rectangle(String id, float relatedX, float relatedY, String dir, float amount, String secondDir, float secondAmount, float float1, float float2) {
        super(id);
        this.x = relatedX;
        this.y = relatedY;
        setRelatedXY(dir, amount);
        setRelatedXY(secondDir, secondAmount);

        this.width = float1;
        this.height = float2;
        setRectangleCoordinates();

    }

    //RectangleTwo From Points
    public Rectangle(String id, Point left1, Point left2) {
        super(id);
        this.x = min(left2.getX(), left1.getX());
        this.y = max(left2.getY(), left1.getY());

        this.width = abs(left2.getX() - left1.getX());
        this.height = abs(left2.getY() - left1.getY());
        setRectangleCoordinates();

    }

    public void setId(String id) {
        this.id = id;
    }

    public float getWidth() {
        return width;
    }

    public void setWidth(float width) {
        this.width = width;
    }

    public float getHeight() {
        return height;
    }

    public void setHeight(float height) {
        this.height = height;
    }

    public void setRelatedXY(String dir, float amount) {
        switch (dir) {
            case "left":
                this.x = x - amount;
                break;
            case "right":
                this.x = x + amount;
                break;
            case "down":
                this.y = y - amount;
                break;
            case "up":
                this.y = y + amount;
                break;
        }
    }

    private void setRectangleCoordinates() {
        Tuple<Float, Float> topLeft = new Tuple<>(x, y);
        Tuple<Float, Float> topRight = new Tuple<>(x + width, y);
        Tuple<Float, Float> bottomLeft = new Tuple<>(x, y - height);
        Tuple<Float, Float> bottomRight = new Tuple<>(x + width, y - height);
        coordinates.add(topLeft);
        coordinates.add(bottomLeft);
        coordinates.add(bottomRight);
        coordinates.add(topRight);

    }
}
