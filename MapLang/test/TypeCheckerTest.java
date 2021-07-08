import MapLang.node.*;
import MapLang.runner.TypeChecker;
import org.junit.Assert;
import org.junit.Test;

public class TypeCheckerTest {
    @Test
    public void testValidOutACreateMapProgramStart() {
        TypeChecker typeChecker = new TypeChecker();
        ACreateMapProgramStart assertObj = new ACreateMapProgramStart();
        ACreateMap map = new ACreateMap();
        map.setId(new TId("testMap"));
        assertObj.setCreateMap(map);
        typeChecker.outACreateMapProgramStart(assertObj);

        String key = map.getId().getText();
        String upperCaseKey = key.toUpperCase();

        Object symboltableLookup = typeChecker.symbolTable.get(upperCaseKey);

        Assert.assertEquals(symboltableLookup.toString(),"Map");
    }

    @Test
    public void testOutACreatePointProgramStart() {
        TypeChecker typeChecker = new TypeChecker();
        var assertObj = new ACreatePointProgramStart();

        ACreatePointCreatePoint point = new ACreatePointCreatePoint();
        point.setId(new TId("testPoint"));

        assertObj.setCreatePoint(point);

        typeChecker.outACreatePointProgramStart(assertObj);

        String key = point.getId().getText();
        String upperCaseKey = key.toUpperCase();

        Object symboltableLookup = typeChecker.symbolTable.get(upperCaseKey);

        Assert.assertEquals(symboltableLookup.toString(),"Point");

    }

    @Test
    public void testOutACreateTemplateProgramStart() {
        TypeChecker typeChecker = new TypeChecker();
        var assertObj = new ACreateTemplateProgramStart();

        ACreateTemplate template = new ACreateTemplate();
        template.setId(new TId("testTemplate"));

        assertObj.setCreateTemplate(template);

        typeChecker.outACreateTemplateProgramStart(assertObj);

        String key = template.getId().getText();
        String upperCaseKey = key.toUpperCase();

        Object symboltableLookup = typeChecker.symbolTable.get(upperCaseKey);

        Assert.assertEquals(symboltableLookup.toString(),"Template");
    }

    @Test
    public void testOutACreateRoomProgramStart() {
        TypeChecker typeChecker = new TypeChecker();
        var assertObj = new ACreateRoomProgramStart();

        typeChecker.symbolTable.put("MAP1","Map");
        assertObj.setLeft(new TId("map1"));
        assertObj.setRight(new TId("p2"));

        typeChecker.outACreateRoomProgramStart(assertObj);

        String key = assertObj.getRight().getText();
        String upperCaseKey = key.toUpperCase();

        Object symboltableLookup = typeChecker.symbolTable.get(upperCaseKey);

        Assert.assertEquals(symboltableLookup.toString(),"Room");

    }

    @Test
    public void testOutACreateRectangleOneProgramStart() {
        TypeChecker typeChecker = new TypeChecker();
        typeChecker.symbolTable.put("P1","Point");

        var assertObj = new ACreateRectangleOneProgramStart();
        assertObj.setId(new TId("rec1"));

        ARelationExplicitAstRelation relation = new ARelationExplicitAstRelation();
        AAstRelationExplicit explicitRelation = new AAstRelationExplicit();
        explicitRelation.setId(new TId("p1"));

        relation.setAstRelationExplicit(explicitRelation);
        assertObj.setAstRelation(relation);

        typeChecker.outACreateRectangleOneProgramStart(assertObj);

        String key = assertObj.getId().getText();
        String upperCaseKey = key.toUpperCase();

        Object symboltableLookup = typeChecker.symbolTable.get(upperCaseKey);

        Assert.assertEquals(symboltableLookup.toString(),"Rectangle");
    }

    @Test
    public void testOutACreateRectangleTwoProgramStart() {
        TypeChecker typeChecker = new TypeChecker();
        var assertObj = new ACreateRectangleTwoProgramStart();
        assertObj.setRight(new TId("r1"));
        assertObj.setLeft1(new TId("p1"));
        assertObj.setLeft2(new TId("p2"));
        typeChecker.symbolTable.put("P1","Point");
        typeChecker.symbolTable.put("P2","Point");

        typeChecker.outACreateRectangleTwoProgramStart(assertObj);

        String key = assertObj.getRight().getText();
        String upperCaseKey = key.toUpperCase();

        Object symboltableLookup = typeChecker.symbolTable.get(upperCaseKey);

        Assert.assertEquals(symboltableLookup.toString(),"Rectangle");
    }

    @Test
    public void testOutACreateCircleProgramStart() {
        TypeChecker typeChecker = new TypeChecker();
        typeChecker.symbolTable.put("P1","Point");

        var assertObj = new ACreateCircleProgramStart();
        ARelationExplicitAstRelation relation = new ARelationExplicitAstRelation();
        AAstRelationExplicit explicitRelation = new AAstRelationExplicit();
        explicitRelation.setId(new TId("p1"));

        relation.setAstRelationExplicit(explicitRelation);
        assertObj.setAstRelation(relation);

        assertObj.setFloat(new TFloat("30.0"));
        assertObj.setId(new TId("c1"));

        typeChecker.outACreateCircleProgramStart(assertObj);

        String key = assertObj.getId().getText();
        String upperCaseKey = key.toUpperCase();

        Object symboltableLookup = typeChecker.symbolTable.get(upperCaseKey);

        Assert.assertEquals(symboltableLookup.toString(),"Circle");
    }

    @Test
    public void testOutACopyPProgramStart() {
        TypeChecker typeChecker = new TypeChecker();
        typeChecker.symbolTable.put("P1","Point");
        typeChecker.symbolTable.put("P2","Point");
        var assertObj = new ACopyPProgramStart();
        ACopyPoints points = new ACopyPoints();

        AERelationAstNullableRelation relation = new AERelationAstNullableRelation();
        AAstPreciseDir dir = new AAstPreciseDir();
        dir.setAstDir(new ADownDirAstDir());
        dir.setFloat(new TFloat("10.0"));
        dir.setAstSecondPreciseDir(new AAstSecondPreciseDir());
        relation.setAstPreciseDir(dir);

        AAstMultiId lhs = new AAstMultiId();
        lhs.setId(new TId("p1"));
        var lhsTail = new AAstMultiId();
        lhsTail.setId(new TId("p2"));
        lhs.setAstMultiId(lhsTail);

        AAstMultiId rhs = new AAstMultiId();
        rhs.setId(new TId("p3"));
        var rhsTail = new AAstMultiId();
        rhsTail.setId(new TId("p4"));
        rhs.setAstMultiId(rhsTail);

        points.setAstNullableRelation(relation);
        points.setLeft(lhs);
        points.setRight(rhs);

        assertObj.setCopyPoints(points);

        typeChecker.outACopyPProgramStart(assertObj);

        ACopyPoints assertPoints = (ACopyPoints) assertObj.getCopyPoints();
        AAstMultiId assertLhs = (AAstMultiId) assertPoints.getLeft();
        AAstMultiId assertRhs = (AAstMultiId) assertPoints.getRight();

        String rhsKey = assertRhs.getId().getText();
        String upperCaseRhsKey = rhsKey.toUpperCase();

        String lhsKey = assertLhs.getId().getText();
        String upperCaseLhsKey = lhsKey.toUpperCase();

        Object symboltableLookupLhs = typeChecker.symbolTable.get(upperCaseLhsKey);
        Object symboltableLookupRhs = typeChecker.symbolTable.get(upperCaseRhsKey);

        Assert.assertEquals(symboltableLookupLhs.toString(),"Point");
        Assert.assertEquals(symboltableLookupRhs.toString(),"Point");
    }

    @Test
    public void testOutACopyTProgramStart() {
        TypeChecker typeChecker = new TypeChecker();
        typeChecker.symbolTable.put("T1","Template");

        var assertObj = new ACopyTProgramStart();
        ACopyTemplate template = new ACopyTemplate();

        AERelationAstNullableRelation relation = new AERelationAstNullableRelation();
        AAstPreciseDir dir = new AAstPreciseDir();
        dir.setAstDir(new ADownDirAstDir());
        dir.setFloat(new TFloat("10.0"));
        dir.setAstSecondPreciseDir(new AAstSecondPreciseDir());
        relation.setAstPreciseDir(dir);

        template.setLeft(new TId("t1"));
        template.setRight(new TId("t2"));

        template.setAstNullableRelation(relation);
        assertObj.setCopyTemplate(template);

        typeChecker.outACopyTProgramStart(assertObj);

        ACopyTemplate assertTemplate = (ACopyTemplate) assertObj.getCopyTemplate();

        String key = assertTemplate.getRight().getText();
        String upperCaseKey = key.toUpperCase();

        Object symboltableLookupLhs = typeChecker.symbolTable.get(upperCaseKey);
        Assert.assertEquals(symboltableLookupLhs.toString(),"Template");
    }

    @Test
    public void testOutACopySProgramStart() {
        TypeChecker typeChecker = new TypeChecker();
        typeChecker.symbolTable.put("S1","Shape");
        typeChecker.symbolTable.put("S2","Shape");

        var assertObj = new ACopySProgramStart();
        ACopyShapes shapes = new ACopyShapes();

        AERelationAstNullableRelation relation = new AERelationAstNullableRelation();
        AAstPreciseDir dir = new AAstPreciseDir();
        dir.setAstDir(new ADownDirAstDir());
        dir.setFloat(new TFloat("10.0"));
        dir.setAstSecondPreciseDir(new AAstSecondPreciseDir());
        relation.setAstPreciseDir(dir);

        AAstMultiId lhs = new AAstMultiId();
        lhs.setId(new TId("s1"));
        var lhsTail = new AAstMultiId();
        lhsTail.setId(new TId("s2"));
        lhs.setAstMultiId(lhsTail);

        AAstMultiId rhs = new AAstMultiId();
        rhs.setId(new TId("s3"));
        var rhsTail = new AAstMultiId();
        rhsTail.setId(new TId("s4"));
        rhs.setAstMultiId(rhsTail);

        shapes.setLeft(lhs);
        shapes.setRight(rhs);
        shapes.setAstNullableRelation(relation);

        assertObj.setCopyShapes(shapes);

        typeChecker.outACopySProgramStart(assertObj);

        ACopyShapes assertShapes = (ACopyShapes) assertObj.getCopyShapes();
        AAstMultiId assertLhs = (AAstMultiId) assertShapes.getLeft();
        AAstMultiId assertRhs = (AAstMultiId) assertShapes.getRight();

        String rhsKey = assertRhs.getId().getText();
        String upperCaseRhsKey = rhsKey.toUpperCase();

        String lhsKey = assertLhs.getId().getText();
        String upperCaseLhsKey = lhsKey.toUpperCase();

        Object symboltableLookupLhs = typeChecker.symbolTable.get(upperCaseLhsKey);
        Object symboltableLookupRhs = typeChecker.symbolTable.get(upperCaseRhsKey);

        Assert.assertEquals(symboltableLookupLhs.toString(),"Shape");
        Assert.assertEquals(symboltableLookupRhs.toString(),"Shape");

    }

    //add to room
    @Test
    public void testOutAAddToRoomProgramStart() {
        TypeChecker typeChecker = new TypeChecker();
        typeChecker.symbolTable.put("R1","Room");
        typeChecker.symbolTable.put("REC1","Rectangle");

        var assertObj = new AAddToRoomProgramStart();

        AAddToRoomIdRoomType roomType = new AAddToRoomIdRoomType();
        roomType.setId(new TId("rec1"));
        assertObj.setId(new TId("r1"));

        assertObj.setRoomType(roomType);

        typeChecker.outAAddToRoomProgramStart(assertObj);

        String key = assertObj.getId().getText();
        String upperCaseKey = key.toUpperCase();

        Object symboltableLookup = typeChecker.symbolTable.get(upperCaseKey);

        Assert.assertEquals(symboltableLookup.toString(),"Room");

    }
    @Test
    public void testOutAAddToRoomPointsRoomType () {
        TypeChecker typeChecker = new TypeChecker();
        typeChecker.symbolTable.put("P1","Point");
        typeChecker.symbolTable.put("P2","Point");
        var assertObj = new AAddToRoomPointsRoomType();

        AAstMultiId points = new AAstMultiId();
        points.setId(new TId("p1"));
        var pointsTail = new AAstMultiId();
        pointsTail.setId(new TId("p2"));
        points.setAstMultiId(pointsTail);

        assertObj.setAstMultiId(points);

        typeChecker.outAAddToRoomPointsRoomType(assertObj);

        AAstMultiId assertIds = (AAstMultiId) assertObj.getAstMultiId();

        String key = assertIds.getId().getText();
        String upperCaseKey = key.toUpperCase();

        Object symboltableLookup = typeChecker.symbolTable.get(upperCaseKey);

        Assert.assertEquals(symboltableLookup.toString(),"Point");
    }
    @Test
    public void testOutAAddToRoomShapesRoomType () {
        TypeChecker typeChecker = new TypeChecker();
        typeChecker.symbolTable.put("S1","Shape");
        typeChecker.symbolTable.put("S2","Shape");

        var assertObj = new AAddToRoomShapesRoomType();

        AAstMultiId points = new AAstMultiId();
        points.setId(new TId("s1"));
        var pointsTail = new AAstMultiId();
        pointsTail.setId(new TId("s2"));
        points.setAstMultiId(pointsTail);

        assertObj.setAstMultiId(points);

        typeChecker.outAAddToRoomShapesRoomType(assertObj);

        AAstMultiId assertIds = (AAstMultiId) assertObj.getAstMultiId();

        String key = assertIds.getId().getText();
        String upperCaseKey = key.toUpperCase();

        Object symboltableLookup = typeChecker.symbolTable.get(upperCaseKey);

        Assert.assertEquals(symboltableLookup.toString(),"Shape");
    }
    @Test
    public void testOutAAddToRoomIdRoomType () {
        TypeChecker typeChecker = new TypeChecker();
        typeChecker.symbolTable.put("T1","Template");

        var assertObj = new AAddToRoomIdRoomType();
        assertObj.setId(new TId("t1"));

        typeChecker.outAAddToRoomIdRoomType(assertObj);

        String key = assertObj.getId().getText();
        String upperCaseKey = key.toUpperCase();

        Object symboltableLookup = typeChecker.symbolTable.get(upperCaseKey);

        Assert.assertEquals(symboltableLookup.toString(),"Template");
    }


    //add to shape
    @Test
    public void testOutAAddToShapeProgramStart() {
        TypeChecker typeChecker = new TypeChecker();
        typeChecker.symbolTable.put("S1","Shape");
        typeChecker.symbolTable.put("P1","Point");
        typeChecker.symbolTable.put("P2","Point");

        var assertObj = new AAddToShapeProgramStart();
        assertObj.setId(new TId("s1"));
        AAddPointsToShapeTypeList typeList = new AAddPointsToShapeTypeList();

        AAstMultiId points = new AAstMultiId();
        points.setId(new TId("p1"));
        var pointsTail = new AAstMultiId();
        pointsTail.setId(new TId("p2"));
        points.setAstMultiId(pointsTail);
        typeList.setAstMultiId(points);

        assertObj.setTypeList(typeList);

        typeChecker.outAAddToShapeProgramStart(assertObj);

        AAddPointsToShapeTypeList asserTypelist = (AAddPointsToShapeTypeList) assertObj.getTypeList();
        AAstMultiId assertIds = (AAstMultiId) asserTypelist.getAstMultiId();

        String pointKey = assertIds.getId().getText();
        String upperCasePointKey = pointKey.toUpperCase();

        String shapeKey = assertObj.getId().getText();
        String upperCaseShapeKey = shapeKey.toUpperCase();

        Object symboltableLookupPointKey = typeChecker.symbolTable.get(upperCasePointKey);
        Object symboltableLookupShapeKey = typeChecker.symbolTable.get(upperCaseShapeKey);

        Assert.assertEquals(symboltableLookupPointKey.toString(),"Point");
        Assert.assertEquals(symboltableLookupShapeKey.toString(),"Shape");

    }


    //type list
    @Test
    public void testOutAAddPointsToShapeTypeList() {
        TypeChecker typeChecker = new TypeChecker();
        typeChecker.symbolTable.put("P1","Point");
        typeChecker.symbolTable.put("P2","Point");
        var assertObj = new AAddPointsToShapeTypeList();

        AAstMultiId points = new AAstMultiId();
        points.setId(new TId("p1"));
        var pointsTail = new AAstMultiId();
        pointsTail.setId(new TId("p2"));
        points.setAstMultiId(pointsTail);

        assertObj.setAstMultiId(points);

        typeChecker.outAAddPointsToShapeTypeList(assertObj);

        AAstMultiId assertIds = (AAstMultiId) assertObj.getAstMultiId();

        String pointKey = assertIds.getId().getText();
        String upperCasePointKey = pointKey.toUpperCase();

        Object symboltableLookupPointKey = typeChecker.symbolTable.get(upperCasePointKey);

        Assert.assertEquals(symboltableLookupPointKey.toString(),"Point");
    }
    @Test
    public void testOutAAddShapesToShapeTypeList() {
        TypeChecker typeChecker = new TypeChecker();
        typeChecker.symbolTable.put("S1","Shape");
        typeChecker.symbolTable.put("S2","Shape");
        var assertObj = new AAddShapesToShapeTypeList();

        AAstMultiId points = new AAstMultiId();
        points.setId(new TId("s1"));
        var pointsTail = new AAstMultiId();
        pointsTail.setId(new TId("s2"));
        points.setAstMultiId(pointsTail);

        assertObj.setAstMultiId(points);

        typeChecker.outAAddShapesToShapeTypeList(assertObj);

        AAstMultiId assertIds = (AAstMultiId) assertObj.getAstMultiId();

        String pointKey = assertIds.getId().getText();
        String upperCasePointKey = pointKey.toUpperCase();

        Object symboltableLookupPointKey = typeChecker.symbolTable.get(upperCasePointKey);

        Assert.assertEquals(symboltableLookupPointKey.toString(),"Shape");
    }
    @Test
    public void testOutAAddTemplateToShapeTypeList() {
        TypeChecker typeChecker = new TypeChecker();
        typeChecker.symbolTable.put("T1","Template");
        typeChecker.symbolTable.put("T2","Template");
        var assertObj = new AAddTemplateToShapeTypeList();

        AAstMultiId points = new AAstMultiId();
        points.setId(new TId("t1"));
        var pointsTail = new AAstMultiId();
        pointsTail.setId(new TId("t2"));
        points.setAstMultiId(pointsTail);

        assertObj.setAstMultiId(points);

        typeChecker.outAAddTemplateToShapeTypeList(assertObj);

        AAstMultiId assertIds = (AAstMultiId) assertObj.getAstMultiId();

        String pointKey = assertIds.getId().getText();
        String upperCasePointKey = pointKey.toUpperCase();

        Object symboltableLookupPointKey = typeChecker.symbolTable.get(upperCasePointKey);

        Assert.assertEquals(symboltableLookupPointKey.toString(),"Template");
    }


    //add to template
    @Test
    public void testOutAAddToTemplateProgramStart() {
        TypeChecker typeChecker = new TypeChecker();
        typeChecker.symbolTable.put("T1","Template");
        var assertObj = new AAddToTemplateProgramStart();
        assertObj.setId(new TId("t1"));
        AAddPointsToShapeTypeList typeList = new AAddPointsToShapeTypeList();

        AAstMultiId points = new AAstMultiId();
        points.setId(new TId("p1"));
        var pointsTail = new AAstMultiId();
        pointsTail.setId(new TId("p2"));
        points.setAstMultiId(pointsTail);
        typeList.setAstMultiId(points);

        assertObj.setTypeList(typeList);

        typeChecker.outAAddToTemplateProgramStart(assertObj);

        String key = assertObj.getId().getText();
        String upperCaseKey = key.toUpperCase();

        Object symboltableLookupKey = typeChecker.symbolTable.get(upperCaseKey);

        Assert.assertEquals(symboltableLookupKey.toString(),"Template");

    }



    //position
    @Test
    public void testOutADistanceAstPosition () {
        TypeChecker typeChecker = new TypeChecker();
        typeChecker.symbolTable.put("P1","Point");
        typeChecker.symbolTable.put("P2","Point");

        var assertObj = new ADistanceAstPosition();

        AEndAstDis dis = new AEndAstDis();
        dis.setEnd(new TEnd());

        assertObj.setAstDis(dis);
        assertObj.setL(new TId("p1"));
        assertObj.setR(new TId("p2"));

        typeChecker.outADistanceAstPosition(assertObj);

        String lKey = assertObj.getL().getText();
        String upperCaseLKey = lKey.toUpperCase();

        String rKey = assertObj.getR().getText();
        String upperCaseRKey = rKey.toUpperCase();

        Object symboltableLookupLKey = typeChecker.symbolTable.get(upperCaseLKey);
        Object symboltableLookupRKey = typeChecker.symbolTable.get(upperCaseRKey);

        Assert.assertEquals(symboltableLookupLKey.toString(),"Point");
        Assert.assertEquals(symboltableLookupRKey.toString(),"Point");

    }

    @Test
    public void testOutAAddDoorwayPositionProgramStart() {
        TypeChecker typeChecker = new TypeChecker();
        typeChecker.symbolTable.put("MAP1","Map");

        var assertObj = new AAddDoorwayPositionProgramStart();
        assertObj.setId(new TId("map1"));
        assertObj.setInt(new TInt("1"));

        var astPosition = new ADistanceAstPosition();

        AEndAstDis dis = new AEndAstDis();
        dis.setEnd(new TEnd());

        astPosition.setAstDis(dis);
        astPosition.setL(new TId("p1"));
        astPosition.setR(new TId("p2"));

        assertObj.setAstPosition(astPosition);

        typeChecker.outAAddDoorwayPositionProgramStart(assertObj);

        String key = assertObj.getId().getText();
        String upperCaseKey = key.toUpperCase();

        Object symboltableLookupKey = typeChecker.symbolTable.get(upperCaseKey);

        Assert.assertEquals(symboltableLookupKey.toString(),"Map");
    }

    @Test
    public void testOutAAddWindowPositionProgramStart() {
        TypeChecker typeChecker = new TypeChecker();
        typeChecker.symbolTable.put("MAP1","Map");

        var assertObj = new AAddWindowPositionProgramStart();
        assertObj.setId(new TId("map1"));
        assertObj.setInt(new TInt("1"));

        var astPosition = new ADistanceAstPosition();

        AEndAstDis dis = new AEndAstDis();
        dis.setEnd(new TEnd());

        astPosition.setAstDis(dis);
        astPosition.setL(new TId("p1"));
        astPosition.setR(new TId("p2"));

        assertObj.setAstPosition(astPosition);

        typeChecker.outAAddWindowPositionProgramStart(assertObj);

        String key = assertObj.getId().getText();
        String upperCaseKey = key.toUpperCase();

        Object symboltableLookupKey = typeChecker.symbolTable.get(upperCaseKey);

        Assert.assertEquals(symboltableLookupKey.toString(),"Map");
    }

    @Test
    public void testOutAAddExitPositionProgramStart() {
        TypeChecker typeChecker = new TypeChecker();
        typeChecker.symbolTable.put("MAP1","Map");

        var assertObj = new AAddExitPositionProgramStart();
        assertObj.setId(new TId("map1"));
        assertObj.setInt(new TInt("1"));

        var astPosition = new ADistanceAstPosition();

        AEndAstDis dis = new AEndAstDis();
        dis.setEnd(new TEnd());

        astPosition.setAstDis(dis);
        astPosition.setL(new TId("p1"));
        astPosition.setR(new TId("p2"));

        assertObj.setAstPosition(astPosition);

        typeChecker.outAAddExitPositionProgramStart(assertObj);

        String key = assertObj.getId().getText();
        String upperCaseKey = key.toUpperCase();

        Object symboltableLookupKey = typeChecker.symbolTable.get(upperCaseKey);

        Assert.assertEquals(symboltableLookupKey.toString(),"Map");
    }

}
