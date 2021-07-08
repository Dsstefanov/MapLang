package MapLang.runner;


import MapLang.classes.*;

import java.io.IOException;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.Hashtable;
import java.io.File;
import java.util.Iterator;

public class CodeGen {
    private final Hashtable symbolTable;

    public CodeGen(Hashtable symbolTable) {
        this.symbolTable = symbolTable;
        exec();
    }

    private void exec() {
        ArrayList<String> mapIds = (ArrayList) symbolTable.get("0m");
        if (mapIds == null) {
            System.out.println("Map was not declared");
            System.exit(1);
        }
        clearEnvironment();
        createFilesDir();


        mapIds.forEach(mapId -> {
            createDirectoryForMap(mapId);
            ArrayList<Point> boundaries = getMapBoundaries(mapId);
            drawMap((Map) symbolTable.get(mapId), boundaries);
        });
    }

    private void createFilesDir() {
        Path currentPath = Path.of("");
        String files = currentPath.toAbsolutePath().toString() + File.separator + "files";
        File file = new File(files);
        boolean created = file.mkdir();
        if (!created) {
            System.out.println("Failed to create directory files");
            System.exit(1);
        }
    }

    private void clearEnvironment() {
        Path currentPath = Path.of("");
        String files = currentPath.toAbsolutePath().toString() + File.separator + "files";
        File file = new File(files);
        if (file.exists()) {
            boolean result = deleteDirectory(file);
            if (!result) {
                System.out.println("Failed to delete directory files");
                System.exit(1);
            }
        }
    }

    private boolean deleteDirectory(File directoryToDelete) {
        File[] contents = directoryToDelete.listFiles();
        if (contents != null) {
            for (File file : contents) {
                deleteDirectory(file);
            }
        }
        return directoryToDelete.delete();
    }

    private void createDirectoryForMap(String mapId) {
        Path currentPath = Path.of("");
        String filesPath = currentPath.toAbsolutePath().toString() + File.separator + "files" + File.separator;
        File file = new File(filesPath + mapId);
        if (file != null) {
            boolean created = file.mkdir();
            if (!created) {
                System.out.println("Error create directory for map: " + mapId);
                System.exit(1);
            }
        }
    }

    private ArrayList<Point> getMapBoundaries(String mapId) {
        Point topLeft = new Point("", 0, 0);
        Point bottomLeft = new Point("", 0, 0);
        Point bottomRight = new Point("", 0, 0);
        Point topRight = new Point("", 0, 0);
        Map map = (Map) symbolTable.get(mapId);
        if (map != null) {
            Iterator it = map.getFloors().values().iterator();
            while (it.hasNext()) {
                Floor floor = (Floor) it.next();
                for (Room room : floor.getRooms()) {
                    ArrayList<String> contents = room.getContentIds();
                    Template template = new Template("", contents);
                    ArrayList<String> spreadContent = spreadTemplates(template);
                    for (String id : spreadContent) {
                        Object object = symbolTable.get(id);
                        switch (object.getClass().getSimpleName()) {
                            case "Shape":
                            case "Rectangle":
                                Shape shape = (Shape) object;
                                for (Tuple<Float, Float> coordinate : shape.getCoordinates()) {
                                    Point dummyPoint = new Point("", coordinate.x, coordinate.y);
                                    topLeft = new Point("",
                                            Math.min(topLeft.getX(), dummyPoint.getX()),
                                            Math.max(topLeft.getY(), dummyPoint.getY()));
                                    bottomLeft = new Point("",
                                            Math.min(bottomLeft.getX(), dummyPoint.getX()),
                                            Math.min(bottomLeft.getY(), dummyPoint.getY()));
                                    bottomRight = new Point("",
                                            Math.max(bottomRight.getX(), dummyPoint.getX()),
                                            Math.min(bottomRight.getY(), dummyPoint.getY()));
                                    topRight = new Point("",
                                            Math.max(topRight.getX(), dummyPoint.getX()),
                                            Math.max(topRight.getY(), dummyPoint.getY()));
                                }
                                break;
                            case "Circle":
                                Circle circle = (Circle) object;
                                Point circleTopLeft = new Point("",
                                        circle.getCoordinates().get(0).x - circle.getRadius(),
                                        circle.getCoordinates().get(0).y + circle.getRadius());
                                Point circleBottomLeft = new Point("",
                                        circle.getCoordinates().get(0).x - circle.getRadius(),
                                        circle.getCoordinates().get(0).y - circle.getRadius());
                                Point circleBottomRight = new Point("",
                                        circle.getCoordinates().get(0).x + circle.getRadius(),
                                        circle.getCoordinates().get(0).y - circle.getRadius());
                                Point circleTopRight = new Point("",
                                        circle.getCoordinates().get(0).x + circle.getRadius(),
                                        circle.getCoordinates().get(0).y + circle.getRadius());

                                topLeft = new Point("",
                                        Math.min(topLeft.getX(), circleTopLeft.getX()),
                                        Math.max(topLeft.getY(), circleTopLeft.getY()));
                                bottomLeft = new Point("",
                                        Math.min(bottomLeft.getX(), circleBottomLeft.getX()),
                                        Math.min(bottomLeft.getY(), circleBottomLeft.getY()));
                                bottomRight = new Point("",
                                        Math.max(bottomRight.getX(), circleBottomRight.getX()),
                                        Math.min(bottomRight.getY(), circleBottomRight.getY()));
                                topRight = new Point("",
                                        Math.max(topRight.getX(), circleTopRight.getX()),
                                        Math.max(topRight.getY(), circleTopRight.getY()));
                                break;

                        }
                    }
                }
                for (Door door : floor.getDoors()) {
                    Point point = new Point("", door.getCoordinate().x, door.getCoordinate().y);
                    topLeft = new Point("",
                            Math.min(topLeft.getX(), point.getX()),
                            Math.max(topLeft.getY(), point.getY()));
                    bottomLeft = new Point("",
                            Math.min(bottomLeft.getX(), point.getX()),
                            Math.min(bottomLeft.getY(), point.getY()));
                    bottomRight = new Point("",
                            Math.max(bottomRight.getX(), point.getX()),
                            Math.min(bottomRight.getY(), point.getY()));
                    topRight = new Point("",
                            Math.max(topRight.getX(), point.getX()),
                            Math.max(topRight.getY(), point.getY()));
                }
                for (Exit exit : floor.getExits()) {
                    Point point = new Point("", exit.getCoordinate().x, exit.getCoordinate().y);
                    topLeft = new Point("",
                            Math.min(topLeft.getX(), point.getX()),
                            Math.max(topLeft.getY(), point.getY()));
                    bottomLeft = new Point("",
                            Math.min(bottomLeft.getX(), point.getX()),
                            Math.min(bottomLeft.getY(), point.getY()));
                    bottomRight = new Point("",
                            Math.max(bottomRight.getX(), point.getX()),
                            Math.min(bottomRight.getY(), point.getY()));
                    topRight = new Point("",
                            Math.max(topRight.getX(), point.getX()),
                            Math.max(topRight.getY(), point.getY()));
                }
                for (Window window : floor.getWindows()) {
                    Point point = new Point("", window.getCoordinate().x, window.getCoordinate().y);
                    topLeft = new Point("",
                            Math.min(topLeft.getX(), point.getX()),
                            Math.max(topLeft.getY(), point.getY()));
                    bottomLeft = new Point("",
                            Math.min(bottomLeft.getX(), point.getX()),
                            Math.min(bottomLeft.getY(), point.getY()));
                    bottomRight = new Point("",
                            Math.max(bottomRight.getX(), point.getX()),
                            Math.min(bottomRight.getY(), point.getY()));
                    topRight = new Point("",
                            Math.max(topRight.getX(), point.getX()),
                            Math.max(topRight.getY(), point.getY()));
                }
            }
        }

        ArrayList<Point> points = new ArrayList<>();
        points.add(topLeft);
        points.add(bottomLeft);
        points.add(bottomRight);
        points.add(topRight);
        return points;
    }

    private ArrayList<String> spreadTemplates(Template t) {
        ArrayList<String> ids = new ArrayList<>();
        t.getContentIds().forEach(id -> {
            Object object = symbolTable.get(id);
            if (object.getClass().getSimpleName().equals("Template")) {
                ids.addAll(spreadTemplates((Template) object));
            } else {
                ids.add(id);
            }
        });
        return ids;
    }

    private void drawMap(Map map, ArrayList<Point> boundaries) {
        Iterator<Floor> it = map.getFloors().values().iterator();

        float minX = Math.min(boundaries.get(0).getX(), boundaries.get(1).getX());
        float minY = Math.min(boundaries.get(1).getY(), boundaries.get(2).getY());
        float maxX = Math.max(boundaries.get(2).getX(), boundaries.get(3).getX());
        float maxY = Math.max(boundaries.get(0).getY(), boundaries.get(3).getY());

        float offSetX = 0 - minX+5;
        float offSetY = 0 - minY-5;
        float height = maxY - minY;

        while (it.hasNext()) {
            Floor floor = it.next();
            DrawMap drawMap = new DrawMap(map.getId(), floor.getFloorNo());
            for (Room room : floor.getRooms()) {
                ArrayList<String> contents = room.getContentIds();
                Template template = new Template("", contents);
                ArrayList<String> spreadContent = spreadTemplates(template);
                for (String id : spreadContent) {
                    Object object = symbolTable.get(id);
                    switch (object.getClass().getSimpleName()) {
                        case "Shape":
                        case "Rectangle":
                            Shape shape = (Shape) object;
                            if (shape.getCoordinates().size() > 2) {
                                for (int i = 0; i < shape.getCoordinates().size(); i++) {
                                    Tuple<Float, Float> connector = shape.getCoordinates().get(i);
                                    Tuple<Float, Float> connectee;
                                    try {
                                        connectee = shape.getCoordinates().get(i + 1);
                                    } catch (IndexOutOfBoundsException e) {
                                        connectee = shape.getCoordinates().get(0);
                                    }
                                    drawMap.addLine(
                                            Math.round(connector.x + offSetX),
                                            Math.round(height - (connector.y + offSetY)),
                                            Math.round(connectee.x + offSetX),
                                            Math.round(height - (connectee.y + offSetY)));
                                }
                            }
                            break;
                        case "Circle":
                            Circle circle = (Circle) object;
                            drawMap.addCircle(new Circle(
                                    "",
                                    circle.getRadius(),
                                    new Tuple<>( circle.getCoordinates().get(0).x + offSetX,
                                            (circle.getCoordinates().get(0).y + offSetY))
                            ));
                            break;

                    }
                }
            }
            for (Door door : floor.getDoors()) {
                Point point = new Point("", door.getCoordinate().x+offSetX, door.getCoordinate().y+offSetY);
                drawMap.addSquare(Math.round(point.getX()), Math.round(height - point.getY()), OpeningType.DOOR);

            }
            for (Exit exit : floor.getExits()) {
                Point point = new Point("", exit.getCoordinate().x+offSetX, exit.getCoordinate().y+offSetY);
                drawMap.addSquare(Math.round(point.getX()), Math.round(height - point.getY()), OpeningType.EXIT);


            }
            for (Window window : floor.getWindows()) {
                Point point = new Point("", window.getCoordinate().x+offSetX, window.getCoordinate().y+offSetY);
                drawMap.addSquare(Math.round(point.getX()), Math.round(height - point.getY()), OpeningType.WINDOW);

            }
            try {
                drawMap.createMap(
                        Math.round(maxY - minY),
                        Math.round(maxX - minX));
            } catch (IOException e) {
                System.out.println("Failed to draw floor: " + floor.getFloorNo());
                System.exit(1);
            }
        }
    }
}
