package MapLang.classes;

public class Window {
    public Tuple<Float, Float> getCoordinate() {
        return coordinate;
    }

    public Window(Tuple<Float, Float> coordinate) {
        this.coordinate = coordinate;
    }
    public Window(Point p1, Point p2, String position) {
        float p1X = p1.getX();
        float p1Y = p1.getY();
        float p2X = p2.getX();
        float p2Y = p2.getY();
        float distanceX;
        float distanceY;

        switch (position){
            case "start":
                coordinate = new Tuple<>(p1X, p1Y);
                break;
            case "nearstart":
                distanceX = (p2X-p1X)*0.25f;
                distanceY = (p2Y-p1Y)*0.25f;
                coordinate = new Tuple<>(p1X+distanceX, p1Y + distanceY);
                break;
            case "halfway":
                distanceX = (p2X-p1X)*0.5f;
                distanceY = (p2Y-p1Y)*0.5f;
                coordinate = new Tuple<>(p1X+distanceX, p1Y + distanceY);
                break;
            case "nearend":
                distanceX = (p2X-p1X)*0.75f;
                distanceY = (p2Y-p1Y)*0.75f;
                coordinate = new Tuple<>(p1X+distanceX, p1Y + distanceY);
                break;
            case "end":
                coordinate = new Tuple<>(p2X, p2Y);
                break;

        }
    }

    private Tuple<Float, Float> coordinate;

}
