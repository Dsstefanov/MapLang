package MapLang.runner;
import MapLang.CustomExceptions.SegmentOverlapException;
import MapLang.analysis.*;
import MapLang.classes.*;
import MapLang.classes.Map;
import MapLang.classes.Point;
import MapLang.classes.Rectangle;
import MapLang.classes.Shape;
import MapLang.classes.Template;
import MapLang.node.*;

import java.util.*;

public class SemanticProcessor extends DepthFirstAdapter {

    Helpers helper = new Helpers();
    public Hashtable symbolTable = new Hashtable();
    int internalCounter = 0;

    @Override
    public void outACreateMap(ACreateMap node) {
        super.outACreateMap(node);
        TId identifier = node.getId();
        String idUpperCase = identifier.getText().toUpperCase();
        symbolTable.put(idUpperCase, new MapLang.classes.Map(idUpperCase));
        ArrayList<String> maps = (ArrayList<String>)symbolTable.get("0m");
        if(maps == null) {
            maps = new ArrayList<String>();
        }
        maps.add(idUpperCase);
        symbolTable.remove("0m");
        symbolTable.put("0m", maps);
    }

    @Override
    public void outAAddToTemplateProgramStart(AAddToTemplateProgramStart node) {
        super.outAAddToTemplateProgramStart(node);
        TId identifier = node.getId();
        String idUpperCase = identifier.getText().toUpperCase();

        PTypeList identifiers = node.getTypeList();
        ArrayList<TId> idList = helper.typeListToTIdList(identifiers);
        ArrayList<AAstCoordinate> coordinateList = helper.typeListToTCoordinateList(identifiers);

        ArrayList<String> IdResult = new ArrayList<>();
        for (var id:idList) { IdResult.add(id.getText().toUpperCase()); }

        ArrayList<Tuple<Float,Float>> coordinateResult = new ArrayList<Tuple<Float,Float>>();
        for (var id:coordinateList) { coordinateResult.add(new Tuple<Float,Float>(Float.parseFloat(id.getX().getText()), Float.parseFloat(id.getY().getText()))); }

        Template templateObjCopy = (Template) symbolTable.get(idUpperCase);
        templateObjCopy.appendContentIds(IdResult);
        templateObjCopy.appendCoordinates(coordinateResult);
        symbolTable.remove(idUpperCase);

        symbolTable.put(idUpperCase, templateObjCopy);
    }

    @Override
    public void outAAddToRoomProgramStart(AAddToRoomProgramStart node) {
        super.outAAddToRoomProgramStart(node);
        TId identifier = node.getId();
        String idUpperCase = identifier.getText().toUpperCase();

        PRoomType identifiers = node.getRoomType();
        ArrayList<TId> idList = helper.roomTypeToTIdList(identifiers);
        ArrayList<AAstCoordinate> coordinateList = helper.roomTypeToTCoordinateList(identifiers);

        ArrayList<String> IdResult = new ArrayList<>();
        for (var id:idList) { IdResult.add(id.getText().toUpperCase()); }

        ArrayList<Tuple<Float,Float>> coordinateResult = new ArrayList<Tuple<Float,Float>>();
        for (var id:coordinateList) { coordinateResult.add(new Tuple<Float,Float>(Float.parseFloat(id.getX().getText()), Float.parseFloat(id.getY().getText()))); }

        Room roomObj = (Room) symbolTable.get(idUpperCase);
        roomObj.appendContentIds(IdResult);
        roomObj.appendCoordinates(coordinateResult);
        symbolTable.remove(idUpperCase);

        symbolTable.put(idUpperCase, roomObj);
    }

    @Override
    public void outACreateTemplate(ACreateTemplate node) {
        super.outACreateTemplate(node);
        TId identifier = node.getId();
        String idUpperCase = identifier.getText().toUpperCase();
        symbolTable.put(idUpperCase, new Template(idUpperCase));
    }

    @Override
    public void outACreatePointCreatePoint(ACreatePointCreatePoint node) {
        super.outACreatePointCreatePoint(node);
        TId identifier = node.getId();
        String idUpperCase = identifier.getText().toUpperCase();
        symbolTable.put(idUpperCase, new Point(idUpperCase));
    }

    @Override
    public void outACreateLineProgramStart(ACreateLineProgramStart node) {
        super.outACreateLineProgramStart(node);
        TId identifier = node.getId();
        String idUpperCase = identifier.getText().toUpperCase();
        AAstCoordinate coordinateLeft = (AAstCoordinate) node.getLeft();
        AAstCoordinate coordinateRight = (AAstCoordinate) node.getRight();
        Shape line = new Shape(idUpperCase);
        ArrayList<Tuple<Float, Float>> listOfCoordinates = new ArrayList<>();
        listOfCoordinates.add(new Tuple<Float, Float>(
                Float.parseFloat(coordinateLeft.getX().getText()),
                Float.parseFloat(coordinateLeft.getY().getText())));
        listOfCoordinates.add(new Tuple<Float, Float>(
                Float.parseFloat(coordinateRight.getX().getText()),
                Float.parseFloat(coordinateRight.getY().getText())));
        try {
            line.addCoordinatesToShape(listOfCoordinates);
            symbolTable.put(idUpperCase, line);
        }catch (SegmentOverlapException e2) {
            System.out.println("Segment overlap exception at line: "+ ((AAstCoordinate) node.getLeft()).getX().getLine());
            System.exit(1);
        }
    }

    @Override
    public void outACreateRectangleOneProgramStart(ACreateRectangleOneProgramStart node) {
        super.outACreateRectangleOneProgramStart(node);
        TId identifier = node.getId();
        String idUpperCase = identifier.getText().toUpperCase();
        PAstRelation relation = node.getAstRelation();
        float width = Float.parseFloat(node.getWidth().getText());
        float height = Float.parseFloat(node.getHeight().getText());
        //check if it is an explicit relation
        try{
            AAstRelationExplicit relationExplicit = (AAstRelationExplicit) ((ARelationExplicitAstRelation) relation).getAstRelationExplicit();
            String relIdCaps = relationExplicit.getId().getText().toUpperCase();
            AAstPreciseDir preciseDir = (AAstPreciseDir) ((ARelationExplicitAstRelation) relation).getAstPreciseDir();

            float firstDirAmount =  Float.parseFloat(preciseDir.getFloat().getText());
            Point relatedPoint = (Point) symbolTable.get(relIdCaps);
            PAstDir firstDir = preciseDir.getAstDir();
            String firstDirString = getDirString(firstDir);

            AAstSecondPreciseDir secondPreciseDir = (AAstSecondPreciseDir) preciseDir.getAstSecondPreciseDir();
            Rectangle rectangle = null;
            if(relatedPoint ==null){return;}
            if(secondPreciseDir == null ) {
                rectangle = new Rectangle(idUpperCase, relatedPoint.getX(), relatedPoint.getY(),
                        firstDirString, firstDirAmount, width, height);
            } else {
            float secondDirAmount = Float.parseFloat(secondPreciseDir.getFloat().getText());
            PAstDir secondDir = secondPreciseDir.getAstDir();
            String secondDirString = getDirString(secondDir);
            rectangle = new Rectangle(idUpperCase, relatedPoint.getX(), relatedPoint.getY(), firstDirString, firstDirAmount, secondDirString, secondDirAmount, width, height);
        }

            symbolTable.put(idUpperCase, rectangle);

        }catch (ClassCastException e) {
            AAstCoordinate coordinate = (AAstCoordinate) ((ARelationCoordinateAstRelation) relation).getAstCoordinate();
            float x = Float.parseFloat(coordinate.getX().getText());
            float y = Float.parseFloat(coordinate.getY().getText());
            Rectangle rectangle = new Rectangle(idUpperCase, x, y, width, height);
            symbolTable.put(idUpperCase, rectangle);

        }
    }

    //error with points
    @Override
    public void outACreateRectangleTwoProgramStart(ACreateRectangleTwoProgramStart node) {
        super.outACreateRectangleTwoProgramStart(node);
        TId identifier = node.getRight();
        TId left1 = node.getLeft1();
        TId left2 = node.getLeft2();
        String idUpperCase = identifier.getText().toUpperCase();
        String left1Upper = left1.getText().toUpperCase();
        String left2Upper = left2.getText().toUpperCase();
        Point pLeft1 = (Point) symbolTable.get(left1Upper);
        Point pLeft2 = (Point) symbolTable.get(left2Upper);
        symbolTable.put(idUpperCase, new Rectangle(idUpperCase, pLeft1, pLeft2));
    }

    @Override
    public void outACreatePointExtendedCreatePoint(ACreatePointExtendedCreatePoint node) {
        super.outACreatePointExtendedCreatePoint(node);
        PAstRelation relation = node.getAstRelation();
        String idStringCaps = node.getId().getText().toUpperCase();

        //check if it is an explicit relation
        //TODO: refactor this so it uses the process relation function
        try {
            AAstRelationExplicit relationExplicit = (AAstRelationExplicit) ((ARelationExplicitAstRelation) relation).
                    getAstRelationExplicit();
            String relIdCaps = relationExplicit.getId().getText()
                    .toUpperCase();
            AAstPreciseDir preciseDir = (AAstPreciseDir) ((ARelationExplicitAstRelation) relation).getAstPreciseDir();

            //first dir
            float firstDirAmount = Float.parseFloat(preciseDir.getFloat().getText());
            Point relatedPoint = (Point) symbolTable.get(relIdCaps);
            PAstDir firstDir = preciseDir.getAstDir();
            String firstDirString = getDirString(firstDir);

            AAstSecondPreciseDir secondPreciseDir = (AAstSecondPreciseDir) preciseDir.getAstSecondPreciseDir();

            Point point = null;
            if (relatedPoint == null) {
                return;
            }
            if (secondPreciseDir == null) {
                point = new Point(idStringCaps, relatedPoint.getX(), relatedPoint.getY(),
                        firstDirString, firstDirAmount);
            } else {
                float secondDirAmount = Float.parseFloat(secondPreciseDir.getFloat().getText());
                PAstDir secondDir = secondPreciseDir.getAstDir();
                String secondDirString = getDirString(secondDir);
                point = new Point(idStringCaps, relatedPoint.getX(), relatedPoint.getY(),
                        firstDirString, firstDirAmount, secondDirString, secondDirAmount);
            }
            symbolTable.put(idStringCaps, point);
        } catch (ClassCastException e) {
            AAstCoordinate coordinate = (AAstCoordinate) ((ARelationCoordinateAstRelation) relation).getAstCoordinate();
            float x = Float.parseFloat(coordinate.getX().getText());
            float y = Float.parseFloat(coordinate.getY().getText());
            Point point = new Point(idStringCaps, x, y);
            symbolTable.put(idStringCaps, point);

        }
    }

    @Override
    public void outACopyPoints(ACopyPoints node) {
        super.outACopyPoints(node);
        PAstMultiId pAstMultiIdLeft = node.getLeft();
        PAstNullableRelation pAstNullableRelation = node.getAstNullableRelation();
        Relation relation = processNullableRelation(pAstNullableRelation);
        PAstMultiId pAstMultiIdRight = node.getRight();
        ArrayList<String> stringOfRightIds = convertMultiIdToStringListId(pAstMultiIdRight);
        ArrayList<Point> pointsOfLeftIds = getPointsFromMultiId(pAstMultiIdLeft);

        ArrayList<Point> newPoints = copyPoints(pointsOfLeftIds, stringOfRightIds, relation);
        newPoints.forEach(p -> {
            symbolTable.put(p.getId(), p);
        });
    }

    @Override
    public void outACopyShapes(ACopyShapes node) {
        super.outACopyShapes(node);
        PAstMultiId pAstMultiIdLeft = node.getLeft();
        PAstNullableRelation pAstNullableRelation = node.getAstNullableRelation();
        Relation relation = processNullableRelation(pAstNullableRelation);
        PAstMultiId pAstMultiIdRight = node.getRight();
        ArrayList<String> stringOfRightIds = convertMultiIdToStringListId(pAstMultiIdRight);
        ArrayList<Shape> shapesOfLeftIds = getShapesFromMultiId(pAstMultiIdLeft);

        ArrayList<Shape> newShapes = copyShapes(shapesOfLeftIds, stringOfRightIds, relation);
        newShapes.forEach(s -> {
            symbolTable.put(s.getId(), s);
        });
    }

    @Override
    public void outAAddDoorwayPositionProgramStart(AAddDoorwayPositionProgramStart node) {
        super.outAAddDoorwayPositionProgramStart(node);
        PAstPosition position = node.getAstPosition();
        String mapId = node.getId().getText().toUpperCase();
        Map map = (Map) symbolTable.get(mapId);
        int floorNumber = Integer.parseInt(node.getInt().getText());
        Floor floor = map.getFloors().get(floorNumber);
        if (floor == null) {
            floor = new Floor(floorNumber);
            map.getFloors().put(floorNumber, floor);
        }
        try {
            AAstCoordinate aAstCoordinate = (AAstCoordinate) ((ACoordinateAstPosition) position).getAstCoordinate();
            if(aAstCoordinate != null) {
                float x = Float.parseFloat(aAstCoordinate.getX().getText());
                float y = Float.parseFloat(aAstCoordinate.getY().getText());
                floor.getDoors().add(new Door(new Tuple<>(x, y)));
            }
        } catch (ClassCastException e) {
            ADistanceAstPosition aDistanceAstPosition = ((ADistanceAstPosition) position);
            PAstDis pAstDis = aDistanceAstPosition.getAstDis();
            String pointId1 = aDistanceAstPosition.getL().getText().toUpperCase();
            String pointId2 = aDistanceAstPosition.getR().getText().toUpperCase();
            Point p1 = (Point) symbolTable.get(pointId1);
            Point p2 = (Point) symbolTable.get(pointId2);

            String astPos = convertAstPositionToString(pAstDis);
            floor.getDoors().add(new Door(p1, p2, astPos));
        }

    }
    @Override
    public void outAAddWindowPositionProgramStart(AAddWindowPositionProgramStart node) {
        super.outAAddWindowPositionProgramStart(node);
        PAstPosition position = node.getAstPosition();
        String mapId = node.getId().getText().toUpperCase();
        Map map = (Map) symbolTable.get(mapId);
        int floorNumber = Integer.parseInt(node.getInt().getText());
        Floor floor = map.getFloors().get(floorNumber);
        if (floor == null) {
            floor = new Floor(floorNumber);
            map.getFloors().put(floorNumber, floor);
        }
        try {
            AAstCoordinate aAstCoordinate = (AAstCoordinate) ((ACoordinateAstPosition) position).getAstCoordinate();
            if(aAstCoordinate != null) {
                float x = Float.parseFloat(aAstCoordinate.getX().getText());
                float y = Float.parseFloat(aAstCoordinate.getY().getText());
                floor.getWindows().add(new Window(new Tuple<>(x, y)));
            }
        } catch (ClassCastException e) {
            ADistanceAstPosition aDistanceAstPosition = ((ADistanceAstPosition) position);
            PAstDis pAstDis = aDistanceAstPosition.getAstDis();
            String pointId1 = aDistanceAstPosition.getL().getText().toUpperCase();
            String pointId2 = aDistanceAstPosition.getR().getText().toUpperCase();
            Point p1 = (Point) symbolTable.get(pointId1);
            Point p2 = (Point) symbolTable.get(pointId2);

            String astPos = convertAstPositionToString(pAstDis);
            floor.getWindows().add(new Window(p1, p2, astPos));
        }

    }
    @Override
    public void outAAddExitPositionProgramStart(AAddExitPositionProgramStart node) {
        super.outAAddExitPositionProgramStart(node);
        PAstPosition position = node.getAstPosition();
        String mapId = node.getId().getText().toUpperCase();
        Map map = (Map) symbolTable.get(mapId);
        int floorNumber = Integer.parseInt(node.getInt().getText());
        Floor floor = map.getFloors().get(floorNumber);
        if (floor == null) {
            floor = new Floor(floorNumber);
            map.getFloors().put(floorNumber, floor);
        }
        try {
            AAstCoordinate aAstCoordinate = (AAstCoordinate) ((ACoordinateAstPosition) position).getAstCoordinate();
            if(aAstCoordinate != null) {
                float x = Float.parseFloat(aAstCoordinate.getX().getText());
                float y = Float.parseFloat(aAstCoordinate.getY().getText());
                floor.getExits().add(new Exit(new Tuple<>(x, y)));
            }
        } catch (ClassCastException e) {
            ADistanceAstPosition aDistanceAstPosition = ((ADistanceAstPosition) position);
            PAstDis pAstDis = aDistanceAstPosition.getAstDis();
            String pointId1 = aDistanceAstPosition.getL().getText().toUpperCase();
            String pointId2 = aDistanceAstPosition.getR().getText().toUpperCase();
            Point p1 = (Point) symbolTable.get(pointId1);
            Point p2 = (Point) symbolTable.get(pointId2);

            String astPos = convertAstPositionToString(pAstDis);
            floor.getExits().add(new Exit(p1, p2, astPos));
        }

    }

    @Override
    public void outAProgramStart(AProgramStart node) {
        super.outAProgramStart(node);
        new CodeGen(this.symbolTable);
    }


    @Override
    public void outACreateCircleProgramStart(ACreateCircleProgramStart node) {
        super.outACreateCircleProgramStart(node);
        TId tId = node.getId();
        String idUpperCase = tId.getText().toUpperCase();
        float radius = Float.parseFloat(node.getFloat().getText());
        PAstRelation  pAstRelation = node.getAstRelation();
        Relation relation = processRelation(pAstRelation);
        Circle circle = new Circle(idUpperCase,radius, relation);
        symbolTable.put(idUpperCase, circle);
    }

    @Override
    public void outACreateRoomProgramStart(ACreateRoomProgramStart node) {
        super.outACreateRoomProgramStart(node);
        TId tId = node.getRight();
        String idUpperCase = tId.getText().toUpperCase();

        Room room = new Room(idUpperCase);
        symbolTable.put(idUpperCase, room);
        Map map = (Map)symbolTable.get(node.getLeft().getText().toUpperCase());
        int floorNumber = Integer.parseInt(node.getInt().getText());
        Floor floor = map.getFloors().get(floorNumber);
        if (floor == null) {
            floor = new Floor(floorNumber);
            map.getFloors().put(floorNumber, floor);
        }
        floor.getRooms().add(room);
    }

    @Override
    public void outAAddPointsToShapeTypeList(AAddPointsToShapeTypeList node) {
        super.outAAddPointsToShapeTypeList(node);
        try {
            AAddToShapeProgramStart parent = (AAddToShapeProgramStart) node.parent();
            String parentId = parent.getId().getText().toUpperCase();

            ArrayList<Point> points = getPointsFromMultiId(node.getAstMultiId());
            Shape shape = (Shape) symbolTable.get(parentId);
            if (shape != null) {
                if (shape.getClass().getSimpleName().equals("Circle")) {
                    System.out.println("Not allowed to add to Circle shape");
                    System.exit(1);
                }
                shape.addToShape(points);
            }

        } catch (ClassCastException e) {
        } catch (SegmentOverlapException e2) {
            System.out.println("Segment overlap exception at line: "+ ((AAstMultiId)node.getAstMultiId()).getId().getLine());
            System.exit(1);
        }
    }

    @Override
    public void outAAddCoordinateToShapeTypeList(AAddCoordinateToShapeTypeList node) {
        super.outAAddCoordinateToShapeTypeList(node);
        try {
            AAddToShapeProgramStart parent = (AAddToShapeProgramStart) node.parent();
            String parentId = parent.getId().getText().toUpperCase();
            PAstMultiCoordinate coordinates = node.getAstMultiCoordinate();
            ArrayList<AAstCoordinate> coordinates1 = helper.multiCoordinateToTCoordinateList(coordinates);
            Shape shape = (Shape) symbolTable.get(parentId);
            ArrayList<Tuple<Float, Float>>coordinatesToAdd = new ArrayList<>();
            coordinates1.forEach(c -> {
                coordinatesToAdd.add(new Tuple<>(
                        Float.parseFloat(c.getX().getText()),
                        Float.parseFloat(c.getY().getText())));
            });
            if (shape != null) {
                if (shape.getClass().getSimpleName().equals("Circle")) {
                    System.out.println("Not allowed to add to Circle shape");
                    System.exit(1);
                }
                shape.addCoordinatesToShape(coordinatesToAdd);
            }

        } catch (ClassCastException e) {
        }catch (SegmentOverlapException e2) {
            System.out.println("Segment overlap exception at line: "+ ((AAstCoordinate)((AAstMultiCoordinate)node
                    .getAstMultiCoordinate()).getAstCoordinate()).getX().getLine());
            System.exit(1);
        }
    }

    @Override
    public void outAAddTemplateToShapeTypeList(AAddTemplateToShapeTypeList node) {
        super.outAAddTemplateToShapeTypeList(node);
        try {
            AAddToShapeProgramStart parent = (AAddToShapeProgramStart) node.parent();
            String parentId = parent.getId().getText().toUpperCase();
            Shape shape = (Shape) symbolTable.get(parentId);
            PAstMultiId pAstMultiId = node.getAstMultiId();
            ArrayList<TId> multiIdTokens = helper.multiIdToTIdList(pAstMultiId);
            ArrayList<Template> templatesToAdd = new ArrayList<>();
            ArrayList<Tuple<Float, Float>> coordinatesToAdd = new ArrayList<>();
            if (shape.getClass().getSimpleName().equals("Circle")) {
                System.out.println("Not allowed to add to Circle shape");
                System.exit(1);
            }
            multiIdTokens.forEach(t -> {
                Template template = (Template) symbolTable.get(t.getText().toUpperCase());
                templatesToAdd.add(template);
            });
            templatesToAdd.forEach(t -> {
                coordinatesToAdd.addAll(getCoordinatesFromTemplate(t));
            });
            shape.addCoordinatesToShape(coordinatesToAdd);
        } catch (ClassCastException e) {
        }catch (SegmentOverlapException e2) {
            System.out.println("Segment overlap exception at line: "+ ((AAstMultiId)node.getAstMultiId()).getId().getLine());
            System.exit(1);
        }
    }

    @Override
    public void outAAddShapesToShapeTypeList(AAddShapesToShapeTypeList node) {
        super.outAAddShapesToShapeTypeList(node);
        try {
            AAddToShapeProgramStart parent = (AAddToShapeProgramStart) node.parent();
            String parentId = parent.getId().getText().toUpperCase();
            Shape shape = (Shape) symbolTable.get(parentId);
            if (shape.getClass().getSimpleName().equals("Circle")) {
                System.out.println("Not allowed to add to Circle shape");
                System.exit(1);
            }
            PAstMultiId pAstMultiId = node.getAstMultiId();
            ArrayList<TId> multiIdTokens = helper.multiIdToTIdList(pAstMultiId);
            ArrayList<Tuple<Float, Float>> coordinatesToAdd = new ArrayList<>();
            multiIdTokens.forEach(t -> {
                Shape shape1 = (Shape) symbolTable.get(t.getText().toUpperCase());
                if (shape1.getClass().getSimpleName().equals("Circle")) {
                    System.out.println("Not allowed to add Circle to a shape");
                    System.exit(1);
                }
                coordinatesToAdd.addAll(shape1.getCoordinates());
            });
            shape.addCoordinatesToShape(coordinatesToAdd);
        } catch (ClassCastException e) {
        }catch (SegmentOverlapException e2) {
            System.out.println("Segment overlap exception at line: "+ ((AAstMultiId)node.getAstMultiId()).getId().getLine());
            System.exit(1);
        }
    }

    @Override
    public void outACopyTemplate(ACopyTemplate node) {
        super.outACopyTemplate(node);
        String leftId = node.getLeft().getText().toUpperCase();
        Template template = (Template) symbolTable.get(leftId);
        if(template != null) {
            ArrayList<String> contentIds = spreadTemplates(template);
            ArrayList<Shape> shapes = convertIdsToShape(contentIds);
            ArrayList<Tuple<Float, Float>> templateCoordinates = template.getCoordinates();
            if (templateCoordinates != null && templateCoordinates.size() != 0) {
                templateCoordinates.forEach(coordinate -> {
                    Shape newShape = new Shape("");
                    newShape.getCoordinates().add(coordinate);
                    shapes.add(newShape);
                });
            }
            Relation relation = processNullableRelation(node.getAstNullableRelation());
            ArrayList<Shape> newShapes = copyShapes(shapes, relation);
            Template newTemplate = new Template(node.getRight().getText().toUpperCase());
            newShapes.forEach(newShape -> {
                newShape.setId(internalCounter + "S");
                internalCounter++;
                newTemplate.getContentIds().add(newShape.getId());
                symbolTable.put(newShape.getId(), newShape);
            });
            symbolTable.put(newTemplate.getId(), newTemplate);
        }
    }

    private String convertAstPositionToString (PAstDis pAstDis) {
        String astPos;
        try {
            astPos = ((AStartAstDis) pAstDis).getStart().getText();
        } catch (ClassCastException e) {
            try {
                astPos = ((ANearStartAstDis) pAstDis).getNearstart().getText();
            } catch (ClassCastException e2) {
                try {
                    astPos = ((AHalfwayAstDis) pAstDis).getHalfway().getText();
                } catch (ClassCastException e3) {
                    try {
                        astPos = ((ANearEndAstDis) pAstDis).getNearend().getText();
                    } catch (ClassCastException e4) {
                        astPos = ((AEndAstDis) pAstDis).getEnd().getText();
                    }
                }
            }
        }
        return astPos.trim();
    }
    private ArrayList<Tuple<Float, Float>> getCoordinatesFromTemplate(Template t) {
        ArrayList<Tuple<Float, Float>> coordinatesToAdd = new ArrayList<>();
        t.getContentIds().forEach(id -> {
            Object object = symbolTable.get(id);
            switch (object.getClass().getSimpleName()) {
                case "Point":
                    Point point = (Point) object;
                    float x = point.getX();
                    float y = point.getY();
                    coordinatesToAdd.add(new Tuple<>(x, y));
                    break;
                case "Template":
                    Template template = (Template) object;
                    getCoordinatesFromTemplate(template);
                    break;
                case "Shape":
                case "Rectangle":
                    Shape shape = (Shape) object;
                    coordinatesToAdd.addAll(shape.getCoordinates());
                    break;
                case "Circle":
                    System.out.println("Circle cannot be added to a shape");
                    System.exit(1);
                default:
                    System.out.println("Error at getCoordinatesFromTemplate");
                    System.exit(1);
            }
        });
        coordinatesToAdd.addAll(t.getCoordinates());
        return coordinatesToAdd;
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

    private ArrayList<Shape> convertIdsToShape(ArrayList<String> ids) {
        ArrayList<Shape> shapesToReturn = new ArrayList<>();
        ids.forEach(id -> {
            Object object = symbolTable.get(id);
            Shape shape = new Shape("");
            switch (object.getClass().getSimpleName()) {
                case "Point":
                    Point point = (Point) object;
                    shape.getCoordinates().add(new Tuple<>(point.getX(), point.getY()));
                    shapesToReturn.add(shape);
                    break;
                case "Template":
                    System.out.println("Template is supposed to be unfolded upfront");
                    System.exit(1);
                    break;
                case "Shape":
                case "Rectangle":
                case "Circle":
                    shape = (Shape) object;
                    shapesToReturn.add(shape);
                    break;
                default:
                    System.out.println("Error at convertIdsToShape");
                    System.exit(1);
            }
        });
        return shapesToReturn;
    }

    private String getDirString(PAstDir dir) {
        try {
            return ((ALeftDirAstDir) dir).getLeft().getText();
        } catch (ClassCastException e) {
        }
        try {
            return ((ARightDirAstDir) dir).getRight().getText();
        } catch (ClassCastException e) {
        }
        try {
            return ((ADownDirAstDir) dir).getDown().getText();
        } catch (ClassCastException e) {
        }
        try {
            return ((AUpDirAstDir) dir).getUp().getText();
        } catch (ClassCastException e) {
            return null;
        }
    }



    private ArrayList<Point> getPointsFromMultiId(PAstMultiId pAstMultiId) {
        ArrayList<Point> points = new ArrayList<>();
        AAstMultiId aAstMultiId = (AAstMultiId) pAstMultiId;

        if (aAstMultiId != null) {
            String headId = aAstMultiId.getId().getText().toUpperCase();
            Point point = (Point) symbolTable.get(headId);
            points.add(point);
            points.addAll(getPointsFromMultiId(aAstMultiId.getAstMultiId()));
        }

        return points;
    }

    private ArrayList<Shape> getShapesFromMultiId(PAstMultiId pAstMultiId) {
        ArrayList<Shape> shapes = new ArrayList<>();
        AAstMultiId aAstMultiId = (AAstMultiId) pAstMultiId;

        if (aAstMultiId != null) {
            String headId = aAstMultiId.getId().getText().toUpperCase();
            Shape shape = (Shape) symbolTable.get(headId);
            shapes.add(shape);
            shapes.addAll(getShapesFromMultiId(aAstMultiId.getAstMultiId()));
        }

        return shapes;
    }

    private ArrayList<Point> copyPoints(ArrayList<Point> points, ArrayList<String> newIds, Relation relation) {
        ListIterator<Point> it = points.listIterator();
        ArrayList<Point> newPoints = new ArrayList<>();
        Point initialPoint;
        Point originalInitialPoint = points.get(0);
        float xDif = 0;
        float yDif = 0;
        while (it.hasNext()) {
            int index = it.nextIndex();
            String newPointId = newIds.get(index);
            Point point = it.next();
            if (index == 0) {
                switch (relation.getType()) {
                    case SINGLE_DIR:
                        Point newPoint = new Point(newPointId, point.getX(), point.getY(),
                                relation.getDir1(), relation.getAmount1());
                        initialPoint = newPoint;
                        newPoints.add(newPoint);
                        break;
                    case TWO_DIR:
                        Point newPoint2 = new Point(newPointId, point.getX(), point.getY(),
                                relation.getDir1(), relation.getAmount1(), relation.getDir2(), relation.getAmount2());
                        initialPoint = newPoint2;
                        newPoints.add(newPoint2);
                        break;
                    case COORDINATE:
                        Point newPoint3 = new Point(newPointId, relation.getX(), relation.getY());
                        initialPoint = newPoint3;
                        newPoints.add(newPoint3);
                        break;
                    case TWO_DIR_EXPLICIT:
                        Point newPoint4 = new Point(newPointId, relation.getX(), relation.getY(),
                                relation.getDir1(), relation.getAmount1(), relation.getDir2(), relation.getAmount2());
                        initialPoint = newPoint4;
                        newPoints.add(newPoint4);
                        break;
                    case SINGLE_DIR_EXPLICIT:
                        Point newPoint5 = new Point(newPointId, relation.getX(), relation.getY(),
                                relation.getDir1(), relation.getAmount1());
                        initialPoint = newPoint5;
                        newPoints.add(newPoint5);
                        break;
                    default:
                        initialPoint = new Point(newPointId);
                }
                xDif = initialPoint.getX() - originalInitialPoint.getX();
                yDif = initialPoint.getY() - originalInitialPoint.getY();
            } else {
                newPoints.add(new Point(newPointId, point.getX() + xDif, point.getY() + yDif));
            }
        }
        return newPoints;
    }
    private ArrayList<Shape> copyShapes(ArrayList<Shape> shapes, ArrayList<String> newIds, Relation relation) {
        ListIterator<Shape> it = shapes.listIterator();
        ArrayList<Shape> newShapes = new ArrayList<>();
        Shape originalInitialShape = shapes.get(0);
        float originalInitialShapeX = originalInitialShape.getCoordinates().get(0).x;
        float originalInitialShapeY = originalInitialShape.getCoordinates().get(0).y;
        float xDif = 0;
        float yDif = 0;
        Point newPoint;
        while (it.hasNext()) {
            int index = it.nextIndex();
            String newShapeId = newIds.get(index);
            Shape shape = it.next();
            if (index == 0) {
                switch (relation.getType()) {
                    case SINGLE_DIR:

                        newPoint = new Point("", originalInitialShapeX, originalInitialShapeY,
                                relation.getDir1(), relation.getAmount1());
                        break;
                    case TWO_DIR:
                        newPoint = new Point("", originalInitialShapeX, originalInitialShapeY,
                                relation.getDir1(), relation.getAmount1(), relation.getDir2(), relation.getAmount2());
                        break;
                    case COORDINATE:
                        newPoint = new Point("", relation.getX(), relation.getY());
                        break;
                    case TWO_DIR_EXPLICIT:
                        newPoint = new Point("", relation.getX(), relation.getY(),
                                relation.getDir1(), relation.getAmount1(), relation.getDir2(), relation.getAmount2());
                        break;
                    case SINGLE_DIR_EXPLICIT:
                        newPoint = new Point("", relation.getX(), relation.getY(),
                                relation.getDir1(), relation.getAmount1());
                        break;
                    default:
                        newPoint = new Point("",0,0);
                }
                xDif = newPoint.getX() - originalInitialShapeX;
                yDif = newPoint.getY() - originalInitialShapeY;
            }
            ArrayList<Tuple<Float, Float>> newCoordinates = new ArrayList<Tuple<Float, Float>>();
            for (Tuple<Float, Float> c : shape.getCoordinates()) {
                Tuple<Float, Float> tuple = new Tuple<>(c.x + xDif, c.y + yDif);
                newCoordinates.add(tuple);
            }
            if (shape.getClass().getSimpleName().equals("Circle")) {
                newShapes.add(new Circle(newShapeId, ((Circle) shape).getRadius(), newCoordinates.get(0)));
            } else {
                newShapes.add(new Shape(newShapeId, newCoordinates));
            }
        }
        return newShapes;
    }

    private ArrayList<Shape> copyShapes(ArrayList<Shape> shapes, Relation relation) {
        ListIterator<Shape> it = shapes.listIterator();
        ArrayList<Shape> newShapes = new ArrayList<>();
        Shape originalInitialShape = shapes.get(0);
        float originalInitialShapeX = originalInitialShape.getCoordinates().get(0).x;
        float originalInitialShapeY = originalInitialShape.getCoordinates().get(0).y;
        float xDif = 0;
        float yDif = 0;
        Point newPoint;
        while (it.hasNext()) {
            int index = it.nextIndex();
            Shape shape = it.next();
            if (index == 0) {
                switch (relation.getType()) {
                    case SINGLE_DIR:

                        newPoint = new Point("", originalInitialShapeX, originalInitialShapeY,
                                relation.getDir1(), relation.getAmount1());
                        break;
                    case TWO_DIR:
                        newPoint = new Point("", originalInitialShapeX, originalInitialShapeY,
                                relation.getDir1(), relation.getAmount1(), relation.getDir2(), relation.getAmount2());
                        break;
                    case COORDINATE:
                        newPoint = new Point("", relation.getX(), relation.getY());
                        break;
                    case TWO_DIR_EXPLICIT:
                        newPoint = new Point("", relation.getX(), relation.getY(),
                                relation.getDir1(), relation.getAmount1(), relation.getDir2(), relation.getAmount2());
                        break;
                    case SINGLE_DIR_EXPLICIT:
                        newPoint = new Point("", relation.getX(), relation.getY(),
                                relation.getDir1(), relation.getAmount1());
                        break;
                    default:
                        newPoint = new Point("",0,0);
                }
                xDif = newPoint.getX() - originalInitialShapeX;
                yDif = newPoint.getY() - originalInitialShapeY;
            }
            ArrayList<Tuple<Float, Float>> newCoordinates = new ArrayList<Tuple<Float, Float>>();
            for (Tuple<Float, Float> c : shape.getCoordinates()) {
                Tuple<Float, Float> tuple = new Tuple<>(c.x + xDif, c.y + yDif);
                newCoordinates.add(tuple);
            }
            if (shape.getClass().getSimpleName().equals("Circle")) {
                newShapes.add(new Circle("", ((Circle) shape).getRadius(), newCoordinates.get(0)));
            } else {
                newShapes.add(new Shape("", newCoordinates));
            }
        }
        return newShapes;
    }

    private Relation processNullableRelation(PAstNullableRelation rel) {
        try {
            AERelationAstNullableRelation relation = (AERelationAstNullableRelation) rel;
            AAstPreciseDir preciseDir = (AAstPreciseDir) relation.getAstPreciseDir();
            PAstDir pAstDir = preciseDir.getAstDir();
            String dir = getDirString(pAstDir);
            float dirAmount = Float.parseFloat(preciseDir.getFloat().getText());
            AAstSecondPreciseDir secondPreciseDir = (AAstSecondPreciseDir) preciseDir.getAstSecondPreciseDir();
            if (secondPreciseDir != null) {
                PAstDir pAstDir2 = secondPreciseDir.getAstDir();
                String dir2 = getDirString(pAstDir2);
                float dirAmount2 = Float.parseFloat(secondPreciseDir.getFloat().getText());
                return new Relation(dir, dir2, dirAmount, dirAmount2);
            }
            return new Relation(dir, dirAmount);
        } catch (ClassCastException e) {
            try {
                AERelationExplicitAstNullableRelation relation = (AERelationExplicitAstNullableRelation) rel;
                AAstRelationExplicit relationExplicit = (AAstRelationExplicit) relation.getAstRelationExplicit();
                String relatedPointString = relationExplicit.getId().getText().toUpperCase();
                Point relatedPoint = (Point) symbolTable.get(relatedPointString);
                if (relatedPoint == null) {
                    return null;
                }
                AAstPreciseDir preciseDir = (AAstPreciseDir) relation.getAstPreciseDir();
                PAstDir pAstDir = preciseDir.getAstDir();
                String dir = getDirString(pAstDir);
                float dirAmount = Float.parseFloat(preciseDir.getFloat().getText());
                AAstSecondPreciseDir secondPreciseDir = (AAstSecondPreciseDir) preciseDir.getAstSecondPreciseDir();
                if (secondPreciseDir != null) {
                    PAstDir pAstDir2 = secondPreciseDir.getAstDir();
                    String dir2 = getDirString(pAstDir2);
                    float dirAmount2 = Float.parseFloat(secondPreciseDir.getFloat().getText());
                    return new Relation(dir, dir2, dirAmount, dirAmount2, relatedPoint.getX(), relatedPoint.getY());
                }
                return new Relation(dir, dirAmount, relatedPoint.getX(), relatedPoint.getY());
            } catch (ClassCastException exception) {
                AERelationCoordinateAstNullableRelation relation = (AERelationCoordinateAstNullableRelation) rel;
                AAstCoordinate coordinate = (AAstCoordinate) relation.getAstCoordinate();
                return new Relation(
                        Float.parseFloat(coordinate.getX().getText()),
                        Float.parseFloat(coordinate.getY().getText())
                );
            }
        }
    }

    private Relation processRelation(PAstRelation rel) {
            try {
                ARelationExplicitAstRelation relation = (ARelationExplicitAstRelation) rel;
                AAstRelationExplicit relationExplicit = (AAstRelationExplicit) relation.getAstRelationExplicit();
                String relatedPointString = relationExplicit.getId().getText().toUpperCase();
                Point relatedPoint = (Point) symbolTable.get(relatedPointString);
                if (relatedPoint == null) {
                    return null;
                }
                AAstPreciseDir preciseDir = (AAstPreciseDir) relation.getAstPreciseDir();
                PAstDir pAstDir = preciseDir.getAstDir();
                String dir = getDirString(pAstDir);
                float dirAmount = Float.parseFloat(preciseDir.getFloat().getText());
                AAstSecondPreciseDir secondPreciseDir = (AAstSecondPreciseDir) preciseDir.getAstSecondPreciseDir();
                if (secondPreciseDir != null) {
                    PAstDir pAstDir2 = secondPreciseDir.getAstDir();
                    String dir2 = getDirString(pAstDir2);
                    float dirAmount2 = Float.parseFloat(secondPreciseDir.getFloat().getText());
                    return new Relation(dir, dir2, dirAmount, dirAmount2, relatedPoint.getX(), relatedPoint.getY());
                }
                return new Relation(dir, dirAmount, relatedPoint.getX(), relatedPoint.getY());
            } catch (ClassCastException exception) {
                ARelationCoordinateAstRelation relation = (ARelationCoordinateAstRelation) rel;
                AAstCoordinate coordinate = (AAstCoordinate) relation.getAstCoordinate();
                return new Relation(
                        Float.parseFloat(coordinate.getX().getText()),
                        Float.parseFloat(coordinate.getY().getText())
                );
            }

    }

    private ArrayList<String> convertMultiIdToStringListId(PAstMultiId pAstMultiId) {
        AAstMultiId aAstMultiId = (AAstMultiId) pAstMultiId;
        ArrayList<String> points = new ArrayList<>();

        if (aAstMultiId != null) {
            String headId = aAstMultiId.getId().getText().toUpperCase();
            points.add(headId);
            points.addAll(convertMultiIdToStringListId(aAstMultiId.getAstMultiId()));
        }
        return points;
    }
}
