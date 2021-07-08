package MapLang.classes;

import MapLang.CustomExceptions.SegmentOverlapException;

import java.util.ArrayList;

public class Shape {
    public void setId(String id) {
        this.id = id;
    }

    protected String id;

    public ArrayList<Tuple<Float, Float>> getCoordinates() {
        return coordinates;
    }

    protected ArrayList<Tuple<Float, Float>> coordinates;

    public Shape(String id) {
        this.id = id;
        this.coordinates = new ArrayList<>();
    }

    public Shape(String id, ArrayList<Tuple<Float, Float>> coordinates) {
        this.id = id;
        this.coordinates = coordinates;
    }

    public String getId() {
        return id;
    }

    public void addToShape(ArrayList<Point> points) throws SegmentOverlapException {
        points.forEach(p -> {
            float x = p.getX();
            float y = p.getY();
            Tuple<Float, Float> coordinate = new Tuple<Float, Float>(x, y);
            coordinates.add(coordinate);
        });
        validateAddToShape();


    }


    public void addCoordinatesToShape(ArrayList<Tuple<Float, Float>> coordinates) throws SegmentOverlapException {
        this.coordinates.addAll(coordinates);
        validateAddToShape();
    }

    private void validateAddToShape () throws SegmentOverlapException{
        int coordinatesSize = coordinates.size();
        if (coordinatesSize == 2) {
            Tuple<Float, Float> t1 = coordinates.get(0);
            Tuple<Float, Float> t2 = coordinates.get(1);
            if (t1.x.equals(t2.x) && t1.y.equals(t2.y)) {
                throw new SegmentOverlapException("Points/Coordinates overlap in a shape");
            }

        } else if (coordinatesSize == 3) {
            Tuple<Float, Float> t1 = coordinates.get(0);
            Tuple<Float, Float> t2 = coordinates.get(1);
            Tuple<Float, Float> t3 = coordinates.get(2);
            if ((t1.x.equals(t2.x) && t1.y.equals(t2.y)) || (orientation(t1, t2, t3) == 0 && (beforeLastPointOnSegment(t1, t3, t2)))) {
                throw new SegmentOverlapException("Sides of a shape intersect");
            }
        } else if (coordinatesSize > 3) {
            Tuple<Float, Float> t1;
            Tuple<Float, Float> t2;
            Tuple<Float, Float> t3;
            Tuple<Float, Float> t4;

            //check if every two sequential segments have overlap
            for (int i = 0; i < coordinatesSize - 1; i++) {
                t1 = coordinates.get(i);
                t2 = coordinates.get(i + 1);
                try {
                    t3 = coordinates.get(i + 2);
                } catch (IndexOutOfBoundsException e) {
                    t3 = coordinates.get(0);
                }
                if ((t1.x == t2.x && t1.y == t2.y)){
                    throw new SegmentOverlapException("Sides of a shape intersect");

                }
                if(orientation(t1, t2, t3)==0 && beforeLastPointOnSegment(t1, t3, t2)) {
                    throw new SegmentOverlapException("Sides of a shape intersect");
                }
            }


            //check if every segment intersects with non-neighbours
            for (int i = 0; i < coordinatesSize - 2; i++) {
                t1 = coordinates.get(i);
                t2 = coordinates.get(i + 1);

                for (int j = i+2; j < coordinatesSize; j++) {
                    t3 = coordinates.get(j);

                    try {
                        t4 = coordinates.get(j+1);
                        if (doIntersect(t1, t2, t3, t4)) {
                            throw new SegmentOverlapException("Sides of a shape intersect");
                        }
                    } catch (IndexOutOfBoundsException e) {
                        t4 = coordinates.get(0);
                        if (i!=0 && doIntersect(t1, t2, t3, t4)) {
                            throw new SegmentOverlapException("Sides of a shape intersect");
                        }
                    }

                }
            }

        }
    }
    // Given three colinear points p,q, r the function checks if
// point q lies on line segment 'pr'
    private boolean onSegment(Tuple<Float, Float> p, Tuple<Float, Float> q, Tuple<Float, Float> r) {
        if (q.x <= Math.max(p.x, r.x) && q.x >= Math.min(p.x, r.x) &&
                q.y <= Math.max(p.y, r.y) && q.y >= Math.min(p.y, r.y))
            return true;

        return false;
    }

    // Given three colinear points p,q, r the function checks if
// point q lies before r
    private boolean beforeLastPointOnSegment(Tuple<Float, Float> p, Tuple<Float, Float> q, Tuple<Float, Float> r) {

        if (r.x <= p.x && r.y >= p.y) { //if r is up and/or left from p, then return true if q is up and/or left from r
            return (q.x >= r.x && q.y <= r.y);
        } else if (r.x <= p.x && r.y <= p.y) { //if r is down and/or left from p, then return true if q is down and/or left from r
            return (q.x >= r.x && q.y >= r.y);
        } else if (r.x >= p.x && r.y <= p.y) { //if r is down and/or right from p, then return true if q is down and/or right from r
            return (q.x <= r.x && q.y >= r.y);
        } else if (r.x >= p.x && r.y >= p.y) { //if r is up and/or right from p, then return true if q is up and/or right from r
            return (q.x <= r.x && q.y <= r.y);
        }
        return true;
    }


    // To find orientation of ordered triplet (p, q, r).
// The function returns following values
// 0 --> p, q and r are colinear
// 1 --> Clockwise
// 2 --> Counterclockwise
    private int orientation(Tuple<Float, Float> p, Tuple<Float, Float> q, Tuple<Float, Float> r) {
        // See https://www.geeksforgeeks.org/orientation-3-ordered-points/
        // for details of below formula.
        float val = (q.y - p.y) * (r.x - q.x) -
                (q.x - p.x) * (r.y - q.y);

        if (val == 0) return 0; // colinear

        return (val > 0) ? 1 : 2; // clock or counterclock wise
    }

    // The main function that returns true if line segment 'p1q1'
// and 'p2q2' intersect.
    private boolean doIntersect(Tuple<Float, Float> p1, Tuple<Float, Float> q1, Tuple<Float, Float> p2, Tuple<Float, Float> q2) {
        // Find the four orientations needed for general and
        // special cases
        int o1 = orientation(p1, q1, p2);
        int o2 = orientation(p1, q1, q2);
        int o3 = orientation(p2, q2, p1);
        int o4 = orientation(p2, q2, q1);

        // General case
        if (o1 != o2 && o3 != o4)
            return true;

        // Special Cases
        // p1, q1 and p2 are colinear and p2 lies on segment p1q1
        if (o1 == 0 && onSegment(p1, p2, q1)) return true;

        // p1, q1 and q2 are colinear and q2 lies on segment p1q1
        if (o2 == 0 && onSegment(p1, q2, q1)) return true;

        // p2, q2 and p1 are colinear and p1 lies on segment p2q2
        if (o3 == 0 && onSegment(p2, p1, q2)) return true;

        // p2, q2 and q1 are colinear and q1 lies on segment p2q2
        if (o4 == 0 && onSegment(p2, q1, q2)) return true;

        return false; // Doesn't fall in any of the above cases
    }

//some of this code was inspired and/or taken from https://www.geeksforgeeks.org/check-if-two-given-line-segments-intersect/ (Last access: 18th of May 2021)
}
