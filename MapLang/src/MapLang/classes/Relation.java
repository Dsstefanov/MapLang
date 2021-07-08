package MapLang.classes;

public class Relation {
    String dir1;
    String dir2;
    float amount1;
    float amount2;
    float x;
    float y;
    RelationType type;

    public Relation(String dir1, String dir2, float amount1, float amount2, float x, float y) {
        this.dir1 = dir1;
        this.dir2 = dir2;
        this.amount1 = amount1;
        this.amount2 = amount2;
        this.x = x;
        this.y = y;
        type = RelationType.TWO_DIR_EXPLICIT;
    }

    public String getDir1() {
        return dir1;
    }

    public String getDir2() {
        return dir2;
    }

    public float getAmount1() {
        return amount1;
    }

    public float getAmount2() {
        return amount2;
    }

    public float getX() {
        return x;
    }

    public float getY() {
        return y;
    }

    public Relation(String dir1, float amount1, float x, float y) {
        this.dir1 = dir1;
        this.amount1 = amount1;
        this.x = x;
        this.y = y;
        type = RelationType.SINGLE_DIR_EXPLICIT;
    }

    public Relation(float x, float y) {
        this.x = x;
        this.y = y;
        type = RelationType.COORDINATE;
    }

    public Relation(String dir1, float amount1) {
        this.dir1 = dir1;
        this.amount1 = amount1;
        type = RelationType.SINGLE_DIR;
    }
    public Relation(String dir1, String dir2, float amount1, float amount2) {
        this.dir1 = dir1;
        this.dir2 = dir2;
        this.amount1 = amount1;
        this.amount2 = amount2;
        type = RelationType.TWO_DIR;
    }

    public RelationType getType() {
        return type;
    }
}
