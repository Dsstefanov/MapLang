package MapLang.classes;

public class Point {
    private String id;
    private float x;
    private float y;

    public Point(String id) {
        this.id = id;
        x = 0;
        y = 0;
    }
    public Point(String id, float x, float y) {
        this.id = id;
        this.x = x;
        this.y = y;
    }
    public Point(String id, float relatedX, float relatedY, String dir, float amount) {
        this.id = id;
        this.x = relatedX;
        this.y = relatedY;
        setRelatedXY(dir, amount);
    }
    public Point(String id, float relatedX, float relatedY, String dir, float amount, String secondDir, float secondAmount) {
        this.id = id;
        this.x = relatedX;
        this.y = relatedY;
        setRelatedXY(dir, amount);
        setRelatedXY(secondDir, secondAmount);
    }


    public String getId() {
        return id;
    }

    public float getX() {
        return x;
    }

    public float getY() {
        return y;
    }

    public void setX(float x) {
        this.x = x;
    }

    public void setY(float y) {
        this.y = y;
    }

    private void setRelatedXY(String dir, float amount) {
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
                this.y = y+ amount;
                break;

        }
    }

}
