package MapLang.runner;

import MapLang.classes.Circle;
import MapLang.classes.OpeningType;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.nio.file.Path;
import java.util.LinkedList;

import javax.imageio.ImageIO;


public class DrawMap  {

    private String imagePath;
    private LinkedList<Line> stack;
    private LinkedList<Circle> circles;
    public DrawMap(String mapId, int floor)  {
        Path currentPath = Path.of("");
        String filepath = currentPath.toAbsolutePath().toString() + File.separator +
                "files" + File.separator + mapId.trim() + File.separator + "floor" + String.valueOf(floor);
        this.imagePath = filepath;
        stack = new LinkedList<>();
        circles = new LinkedList<>();

    }

    public void createMap(int height, int width) throws IOException {
        File file = new File(imagePath + ".jpg");
        BufferedImage bufferedImage = new BufferedImage(width+11, height+11, BufferedImage.TYPE_INT_RGB);
        for (Line line : stack) {
            Graphics g = bufferedImage.getGraphics();
            if(line.color != null){
                g.setColor(line.color);

            } else {
                g.setColor(Color.WHITE);
            }
            g.drawLine(line.x1, line.y1, line.x2, line.y2);

        }

        for (Circle circle : circles) {
            Graphics g = bufferedImage.getGraphics();
            g.drawOval(
                    Math.round(circle.getCoordinates().get(0).x - circle.getRadius()),
                    Math.round( height - (circle.getCoordinates().get(0).y + circle.getRadius())),
                    Math.round(2 * circle.getRadius()),
                    Math.round(2 * circle.getRadius()));
        }

        ImageIO.write(bufferedImage, "jpg", file);
    }

    public void addLine(int x1, int y1, int x2, int y2) {
        stack.push(new Line(x1,y1,x2,y2));
    }
    public void addSquare(int x, int y, OpeningType openingType) {
        Color color;
        switch (openingType){
            case DOOR:
                color = Color.GRAY;
                break;
            case WINDOW:
                color = Color.CYAN;
                break;
            case EXIT:
                color = Color.YELLOW;
                break;
            default:
                color = Color.WHITE;
        }
        stack.push(new Line(x-5,y+5,x-5,y-5, color));
        stack.push(new Line(x-5,y-5,x+5,y-5, color));
        stack.push(new Line(x+5,y-5,x+5,y+5, color));
        stack.push(new Line(x+5,y+5,x-5,y+5, color));
    }


    public void addCircle(Circle circle) {
        circles.push(circle);
    }

    public class Line {
        private int x1;
        private int y1;
        private int x2;
        private int y2;
        private Color color;

        public Line(int x1, int y1, int x2, int y2) {
            this.x1 = x1;
            this.y1 = y1;
            this.x2 = x2;
            this.y2 = y2;
        }

        public Line(int x1, int y1, int x2, int y2,Color color) {
            this.x1 = x1;
            this.y1 = y1;
            this.x2 = x2;
            this.y2 = y2;
            this.color = color;
        }

    }
}