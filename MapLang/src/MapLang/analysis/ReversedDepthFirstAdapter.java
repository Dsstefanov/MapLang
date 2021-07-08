/* This file was generated by SableCC (http://www.sablecc.org/). */

package MapLang.analysis;

import MapLang.node.*;

public class ReversedDepthFirstAdapter extends AnalysisAdapter
{
    public void inStart(Start node)
    {
        defaultIn(node);
    }

    public void outStart(Start node)
    {
        defaultOut(node);
    }

    public void defaultIn(@SuppressWarnings("unused") Node node)
    {
        // Do nothing
    }

    public void defaultOut(@SuppressWarnings("unused") Node node)
    {
        // Do nothing
    }

    @Override
    public void caseStart(Start node)
    {
        inStart(node);
        node.getEOF().apply(this);
        node.getPProgramStart().apply(this);
        outStart(node);
    }

    public void inAInitProgramStart(AInitProgramStart node)
    {
        defaultIn(node);
    }

    public void outAInitProgramStart(AInitProgramStart node)
    {
        defaultOut(node);
    }

    @Override
    public void caseAInitProgramStart(AInitProgramStart node)
    {
        inAInitProgramStart(node);
        if(node.getStatementTwo() != null)
        {
            node.getStatementTwo().apply(this);
        }
        if(node.getStatementOne() != null)
        {
            node.getStatementOne().apply(this);
        }
        outAInitProgramStart(node);
    }

    public void inACreateMapProgramStart(ACreateMapProgramStart node)
    {
        defaultIn(node);
    }

    public void outACreateMapProgramStart(ACreateMapProgramStart node)
    {
        defaultOut(node);
    }

    @Override
    public void caseACreateMapProgramStart(ACreateMapProgramStart node)
    {
        inACreateMapProgramStart(node);
        if(node.getCreateMap() != null)
        {
            node.getCreateMap().apply(this);
        }
        outACreateMapProgramStart(node);
    }

    public void inACreatePointProgramStart(ACreatePointProgramStart node)
    {
        defaultIn(node);
    }

    public void outACreatePointProgramStart(ACreatePointProgramStart node)
    {
        defaultOut(node);
    }

    @Override
    public void caseACreatePointProgramStart(ACreatePointProgramStart node)
    {
        inACreatePointProgramStart(node);
        if(node.getCreatePoint() != null)
        {
            node.getCreatePoint().apply(this);
        }
        outACreatePointProgramStart(node);
    }

    public void inACreateTemplateProgramStart(ACreateTemplateProgramStart node)
    {
        defaultIn(node);
    }

    public void outACreateTemplateProgramStart(ACreateTemplateProgramStart node)
    {
        defaultOut(node);
    }

    @Override
    public void caseACreateTemplateProgramStart(ACreateTemplateProgramStart node)
    {
        inACreateTemplateProgramStart(node);
        if(node.getCreateTemplate() != null)
        {
            node.getCreateTemplate().apply(this);
        }
        outACreateTemplateProgramStart(node);
    }

    public void inACreateRoomProgramStart(ACreateRoomProgramStart node)
    {
        defaultIn(node);
    }

    public void outACreateRoomProgramStart(ACreateRoomProgramStart node)
    {
        defaultOut(node);
    }

    @Override
    public void caseACreateRoomProgramStart(ACreateRoomProgramStart node)
    {
        inACreateRoomProgramStart(node);
        if(node.getRight() != null)
        {
            node.getRight().apply(this);
        }
        if(node.getInt() != null)
        {
            node.getInt().apply(this);
        }
        if(node.getLeft() != null)
        {
            node.getLeft().apply(this);
        }
        outACreateRoomProgramStart(node);
    }

    public void inACreateRectangleOneProgramStart(ACreateRectangleOneProgramStart node)
    {
        defaultIn(node);
    }

    public void outACreateRectangleOneProgramStart(ACreateRectangleOneProgramStart node)
    {
        defaultOut(node);
    }

    @Override
    public void caseACreateRectangleOneProgramStart(ACreateRectangleOneProgramStart node)
    {
        inACreateRectangleOneProgramStart(node);
        if(node.getId() != null)
        {
            node.getId().apply(this);
        }
        if(node.getAstRelation() != null)
        {
            node.getAstRelation().apply(this);
        }
        if(node.getHeight() != null)
        {
            node.getHeight().apply(this);
        }
        if(node.getWidth() != null)
        {
            node.getWidth().apply(this);
        }
        outACreateRectangleOneProgramStart(node);
    }

    public void inACreateRectangleTwoProgramStart(ACreateRectangleTwoProgramStart node)
    {
        defaultIn(node);
    }

    public void outACreateRectangleTwoProgramStart(ACreateRectangleTwoProgramStart node)
    {
        defaultOut(node);
    }

    @Override
    public void caseACreateRectangleTwoProgramStart(ACreateRectangleTwoProgramStart node)
    {
        inACreateRectangleTwoProgramStart(node);
        if(node.getRight() != null)
        {
            node.getRight().apply(this);
        }
        if(node.getLeft2() != null)
        {
            node.getLeft2().apply(this);
        }
        if(node.getLeft1() != null)
        {
            node.getLeft1().apply(this);
        }
        outACreateRectangleTwoProgramStart(node);
    }

    public void inACreateCircleProgramStart(ACreateCircleProgramStart node)
    {
        defaultIn(node);
    }

    public void outACreateCircleProgramStart(ACreateCircleProgramStart node)
    {
        defaultOut(node);
    }

    @Override
    public void caseACreateCircleProgramStart(ACreateCircleProgramStart node)
    {
        inACreateCircleProgramStart(node);
        if(node.getId() != null)
        {
            node.getId().apply(this);
        }
        if(node.getAstRelation() != null)
        {
            node.getAstRelation().apply(this);
        }
        if(node.getFloat() != null)
        {
            node.getFloat().apply(this);
        }
        outACreateCircleProgramStart(node);
    }

    public void inACreateLineProgramStart(ACreateLineProgramStart node)
    {
        defaultIn(node);
    }

    public void outACreateLineProgramStart(ACreateLineProgramStart node)
    {
        defaultOut(node);
    }

    @Override
    public void caseACreateLineProgramStart(ACreateLineProgramStart node)
    {
        inACreateLineProgramStart(node);
        if(node.getId() != null)
        {
            node.getId().apply(this);
        }
        if(node.getRight() != null)
        {
            node.getRight().apply(this);
        }
        if(node.getLeft() != null)
        {
            node.getLeft().apply(this);
        }
        outACreateLineProgramStart(node);
    }

    public void inACopyPProgramStart(ACopyPProgramStart node)
    {
        defaultIn(node);
    }

    public void outACopyPProgramStart(ACopyPProgramStart node)
    {
        defaultOut(node);
    }

    @Override
    public void caseACopyPProgramStart(ACopyPProgramStart node)
    {
        inACopyPProgramStart(node);
        if(node.getCopyPoints() != null)
        {
            node.getCopyPoints().apply(this);
        }
        outACopyPProgramStart(node);
    }

    public void inACopyTProgramStart(ACopyTProgramStart node)
    {
        defaultIn(node);
    }

    public void outACopyTProgramStart(ACopyTProgramStart node)
    {
        defaultOut(node);
    }

    @Override
    public void caseACopyTProgramStart(ACopyTProgramStart node)
    {
        inACopyTProgramStart(node);
        if(node.getCopyTemplate() != null)
        {
            node.getCopyTemplate().apply(this);
        }
        outACopyTProgramStart(node);
    }

    public void inACopySProgramStart(ACopySProgramStart node)
    {
        defaultIn(node);
    }

    public void outACopySProgramStart(ACopySProgramStart node)
    {
        defaultOut(node);
    }

    @Override
    public void caseACopySProgramStart(ACopySProgramStart node)
    {
        inACopySProgramStart(node);
        if(node.getCopyShapes() != null)
        {
            node.getCopyShapes().apply(this);
        }
        outACopySProgramStart(node);
    }

    public void inAAddToRoomProgramStart(AAddToRoomProgramStart node)
    {
        defaultIn(node);
    }

    public void outAAddToRoomProgramStart(AAddToRoomProgramStart node)
    {
        defaultOut(node);
    }

    @Override
    public void caseAAddToRoomProgramStart(AAddToRoomProgramStart node)
    {
        inAAddToRoomProgramStart(node);
        if(node.getRoomType() != null)
        {
            node.getRoomType().apply(this);
        }
        if(node.getId() != null)
        {
            node.getId().apply(this);
        }
        outAAddToRoomProgramStart(node);
    }

    public void inAAddToShapeProgramStart(AAddToShapeProgramStart node)
    {
        defaultIn(node);
    }

    public void outAAddToShapeProgramStart(AAddToShapeProgramStart node)
    {
        defaultOut(node);
    }

    @Override
    public void caseAAddToShapeProgramStart(AAddToShapeProgramStart node)
    {
        inAAddToShapeProgramStart(node);
        if(node.getTypeList() != null)
        {
            node.getTypeList().apply(this);
        }
        if(node.getId() != null)
        {
            node.getId().apply(this);
        }
        outAAddToShapeProgramStart(node);
    }

    public void inAAddToTemplateProgramStart(AAddToTemplateProgramStart node)
    {
        defaultIn(node);
    }

    public void outAAddToTemplateProgramStart(AAddToTemplateProgramStart node)
    {
        defaultOut(node);
    }

    @Override
    public void caseAAddToTemplateProgramStart(AAddToTemplateProgramStart node)
    {
        inAAddToTemplateProgramStart(node);
        if(node.getTypeList() != null)
        {
            node.getTypeList().apply(this);
        }
        if(node.getId() != null)
        {
            node.getId().apply(this);
        }
        outAAddToTemplateProgramStart(node);
    }

    public void inAAddDoorwayPositionProgramStart(AAddDoorwayPositionProgramStart node)
    {
        defaultIn(node);
    }

    public void outAAddDoorwayPositionProgramStart(AAddDoorwayPositionProgramStart node)
    {
        defaultOut(node);
    }

    @Override
    public void caseAAddDoorwayPositionProgramStart(AAddDoorwayPositionProgramStart node)
    {
        inAAddDoorwayPositionProgramStart(node);
        if(node.getInt() != null)
        {
            node.getInt().apply(this);
        }
        if(node.getAstPosition() != null)
        {
            node.getAstPosition().apply(this);
        }
        if(node.getId() != null)
        {
            node.getId().apply(this);
        }
        outAAddDoorwayPositionProgramStart(node);
    }

    public void inAAddWindowPositionProgramStart(AAddWindowPositionProgramStart node)
    {
        defaultIn(node);
    }

    public void outAAddWindowPositionProgramStart(AAddWindowPositionProgramStart node)
    {
        defaultOut(node);
    }

    @Override
    public void caseAAddWindowPositionProgramStart(AAddWindowPositionProgramStart node)
    {
        inAAddWindowPositionProgramStart(node);
        if(node.getInt() != null)
        {
            node.getInt().apply(this);
        }
        if(node.getAstPosition() != null)
        {
            node.getAstPosition().apply(this);
        }
        if(node.getId() != null)
        {
            node.getId().apply(this);
        }
        outAAddWindowPositionProgramStart(node);
    }

    public void inAAddExitPositionProgramStart(AAddExitPositionProgramStart node)
    {
        defaultIn(node);
    }

    public void outAAddExitPositionProgramStart(AAddExitPositionProgramStart node)
    {
        defaultOut(node);
    }

    @Override
    public void caseAAddExitPositionProgramStart(AAddExitPositionProgramStart node)
    {
        inAAddExitPositionProgramStart(node);
        if(node.getInt() != null)
        {
            node.getInt().apply(this);
        }
        if(node.getAstPosition() != null)
        {
            node.getAstPosition().apply(this);
        }
        if(node.getId() != null)
        {
            node.getId().apply(this);
        }
        outAAddExitPositionProgramStart(node);
    }

    public void inAProgramStart(AProgramStart node)
    {
        defaultIn(node);
    }

    public void outAProgramStart(AProgramStart node)
    {
        defaultOut(node);
    }

    @Override
    public void caseAProgramStart(AProgramStart node)
    {
        inAProgramStart(node);
        outAProgramStart(node);
    }

    public void inACreateMap(ACreateMap node)
    {
        defaultIn(node);
    }

    public void outACreateMap(ACreateMap node)
    {
        defaultOut(node);
    }

    @Override
    public void caseACreateMap(ACreateMap node)
    {
        inACreateMap(node);
        if(node.getId() != null)
        {
            node.getId().apply(this);
        }
        outACreateMap(node);
    }

    public void inACreatePointCreatePoint(ACreatePointCreatePoint node)
    {
        defaultIn(node);
    }

    public void outACreatePointCreatePoint(ACreatePointCreatePoint node)
    {
        defaultOut(node);
    }

    @Override
    public void caseACreatePointCreatePoint(ACreatePointCreatePoint node)
    {
        inACreatePointCreatePoint(node);
        if(node.getId() != null)
        {
            node.getId().apply(this);
        }
        outACreatePointCreatePoint(node);
    }

    public void inACreatePointExtendedCreatePoint(ACreatePointExtendedCreatePoint node)
    {
        defaultIn(node);
    }

    public void outACreatePointExtendedCreatePoint(ACreatePointExtendedCreatePoint node)
    {
        defaultOut(node);
    }

    @Override
    public void caseACreatePointExtendedCreatePoint(ACreatePointExtendedCreatePoint node)
    {
        inACreatePointExtendedCreatePoint(node);
        if(node.getId() != null)
        {
            node.getId().apply(this);
        }
        if(node.getAstRelation() != null)
        {
            node.getAstRelation().apply(this);
        }
        outACreatePointExtendedCreatePoint(node);
    }

    public void inACreateTemplate(ACreateTemplate node)
    {
        defaultIn(node);
    }

    public void outACreateTemplate(ACreateTemplate node)
    {
        defaultOut(node);
    }

    @Override
    public void caseACreateTemplate(ACreateTemplate node)
    {
        inACreateTemplate(node);
        if(node.getId() != null)
        {
            node.getId().apply(this);
        }
        outACreateTemplate(node);
    }

    public void inACopyPoints(ACopyPoints node)
    {
        defaultIn(node);
    }

    public void outACopyPoints(ACopyPoints node)
    {
        defaultOut(node);
    }

    @Override
    public void caseACopyPoints(ACopyPoints node)
    {
        inACopyPoints(node);
        if(node.getRight() != null)
        {
            node.getRight().apply(this);
        }
        if(node.getAstNullableRelation() != null)
        {
            node.getAstNullableRelation().apply(this);
        }
        if(node.getLeft() != null)
        {
            node.getLeft().apply(this);
        }
        outACopyPoints(node);
    }

    public void inACopyTemplate(ACopyTemplate node)
    {
        defaultIn(node);
    }

    public void outACopyTemplate(ACopyTemplate node)
    {
        defaultOut(node);
    }

    @Override
    public void caseACopyTemplate(ACopyTemplate node)
    {
        inACopyTemplate(node);
        if(node.getRight() != null)
        {
            node.getRight().apply(this);
        }
        if(node.getAstNullableRelation() != null)
        {
            node.getAstNullableRelation().apply(this);
        }
        if(node.getLeft() != null)
        {
            node.getLeft().apply(this);
        }
        outACopyTemplate(node);
    }

    public void inACopyShapes(ACopyShapes node)
    {
        defaultIn(node);
    }

    public void outACopyShapes(ACopyShapes node)
    {
        defaultOut(node);
    }

    @Override
    public void caseACopyShapes(ACopyShapes node)
    {
        inACopyShapes(node);
        if(node.getRight() != null)
        {
            node.getRight().apply(this);
        }
        if(node.getAstNullableRelation() != null)
        {
            node.getAstNullableRelation().apply(this);
        }
        if(node.getLeft() != null)
        {
            node.getLeft().apply(this);
        }
        outACopyShapes(node);
    }

    public void inARelationExplicitAstRelation(ARelationExplicitAstRelation node)
    {
        defaultIn(node);
    }

    public void outARelationExplicitAstRelation(ARelationExplicitAstRelation node)
    {
        defaultOut(node);
    }

    @Override
    public void caseARelationExplicitAstRelation(ARelationExplicitAstRelation node)
    {
        inARelationExplicitAstRelation(node);
        if(node.getAstRelationExplicit() != null)
        {
            node.getAstRelationExplicit().apply(this);
        }
        if(node.getAstPreciseDir() != null)
        {
            node.getAstPreciseDir().apply(this);
        }
        outARelationExplicitAstRelation(node);
    }

    public void inARelationCoordinateAstRelation(ARelationCoordinateAstRelation node)
    {
        defaultIn(node);
    }

    public void outARelationCoordinateAstRelation(ARelationCoordinateAstRelation node)
    {
        defaultOut(node);
    }

    @Override
    public void caseARelationCoordinateAstRelation(ARelationCoordinateAstRelation node)
    {
        inARelationCoordinateAstRelation(node);
        if(node.getAstCoordinate() != null)
        {
            node.getAstCoordinate().apply(this);
        }
        outARelationCoordinateAstRelation(node);
    }

    public void inAERelationAstNullableRelation(AERelationAstNullableRelation node)
    {
        defaultIn(node);
    }

    public void outAERelationAstNullableRelation(AERelationAstNullableRelation node)
    {
        defaultOut(node);
    }

    @Override
    public void caseAERelationAstNullableRelation(AERelationAstNullableRelation node)
    {
        inAERelationAstNullableRelation(node);
        if(node.getAstPreciseDir() != null)
        {
            node.getAstPreciseDir().apply(this);
        }
        outAERelationAstNullableRelation(node);
    }

    public void inAERelationExplicitAstNullableRelation(AERelationExplicitAstNullableRelation node)
    {
        defaultIn(node);
    }

    public void outAERelationExplicitAstNullableRelation(AERelationExplicitAstNullableRelation node)
    {
        defaultOut(node);
    }

    @Override
    public void caseAERelationExplicitAstNullableRelation(AERelationExplicitAstNullableRelation node)
    {
        inAERelationExplicitAstNullableRelation(node);
        if(node.getAstRelationExplicit() != null)
        {
            node.getAstRelationExplicit().apply(this);
        }
        if(node.getAstPreciseDir() != null)
        {
            node.getAstPreciseDir().apply(this);
        }
        outAERelationExplicitAstNullableRelation(node);
    }

    public void inAERelationCoordinateAstNullableRelation(AERelationCoordinateAstNullableRelation node)
    {
        defaultIn(node);
    }

    public void outAERelationCoordinateAstNullableRelation(AERelationCoordinateAstNullableRelation node)
    {
        defaultOut(node);
    }

    @Override
    public void caseAERelationCoordinateAstNullableRelation(AERelationCoordinateAstNullableRelation node)
    {
        inAERelationCoordinateAstNullableRelation(node);
        if(node.getAstCoordinate() != null)
        {
            node.getAstCoordinate().apply(this);
        }
        outAERelationCoordinateAstNullableRelation(node);
    }

    public void inAAstRelationExplicit(AAstRelationExplicit node)
    {
        defaultIn(node);
    }

    public void outAAstRelationExplicit(AAstRelationExplicit node)
    {
        defaultOut(node);
    }

    @Override
    public void caseAAstRelationExplicit(AAstRelationExplicit node)
    {
        inAAstRelationExplicit(node);
        if(node.getId() != null)
        {
            node.getId().apply(this);
        }
        outAAstRelationExplicit(node);
    }

    public void inALeftDirAstDir(ALeftDirAstDir node)
    {
        defaultIn(node);
    }

    public void outALeftDirAstDir(ALeftDirAstDir node)
    {
        defaultOut(node);
    }

    @Override
    public void caseALeftDirAstDir(ALeftDirAstDir node)
    {
        inALeftDirAstDir(node);
        if(node.getLeft() != null)
        {
            node.getLeft().apply(this);
        }
        outALeftDirAstDir(node);
    }

    public void inARightDirAstDir(ARightDirAstDir node)
    {
        defaultIn(node);
    }

    public void outARightDirAstDir(ARightDirAstDir node)
    {
        defaultOut(node);
    }

    @Override
    public void caseARightDirAstDir(ARightDirAstDir node)
    {
        inARightDirAstDir(node);
        if(node.getRight() != null)
        {
            node.getRight().apply(this);
        }
        outARightDirAstDir(node);
    }

    public void inAUpDirAstDir(AUpDirAstDir node)
    {
        defaultIn(node);
    }

    public void outAUpDirAstDir(AUpDirAstDir node)
    {
        defaultOut(node);
    }

    @Override
    public void caseAUpDirAstDir(AUpDirAstDir node)
    {
        inAUpDirAstDir(node);
        if(node.getUp() != null)
        {
            node.getUp().apply(this);
        }
        outAUpDirAstDir(node);
    }

    public void inADownDirAstDir(ADownDirAstDir node)
    {
        defaultIn(node);
    }

    public void outADownDirAstDir(ADownDirAstDir node)
    {
        defaultOut(node);
    }

    @Override
    public void caseADownDirAstDir(ADownDirAstDir node)
    {
        inADownDirAstDir(node);
        if(node.getDown() != null)
        {
            node.getDown().apply(this);
        }
        outADownDirAstDir(node);
    }

    public void inAStartAstDis(AStartAstDis node)
    {
        defaultIn(node);
    }

    public void outAStartAstDis(AStartAstDis node)
    {
        defaultOut(node);
    }

    @Override
    public void caseAStartAstDis(AStartAstDis node)
    {
        inAStartAstDis(node);
        if(node.getStart() != null)
        {
            node.getStart().apply(this);
        }
        outAStartAstDis(node);
    }

    public void inANearStartAstDis(ANearStartAstDis node)
    {
        defaultIn(node);
    }

    public void outANearStartAstDis(ANearStartAstDis node)
    {
        defaultOut(node);
    }

    @Override
    public void caseANearStartAstDis(ANearStartAstDis node)
    {
        inANearStartAstDis(node);
        if(node.getNearstart() != null)
        {
            node.getNearstart().apply(this);
        }
        outANearStartAstDis(node);
    }

    public void inAHalfwayAstDis(AHalfwayAstDis node)
    {
        defaultIn(node);
    }

    public void outAHalfwayAstDis(AHalfwayAstDis node)
    {
        defaultOut(node);
    }

    @Override
    public void caseAHalfwayAstDis(AHalfwayAstDis node)
    {
        inAHalfwayAstDis(node);
        if(node.getHalfway() != null)
        {
            node.getHalfway().apply(this);
        }
        outAHalfwayAstDis(node);
    }

    public void inANearEndAstDis(ANearEndAstDis node)
    {
        defaultIn(node);
    }

    public void outANearEndAstDis(ANearEndAstDis node)
    {
        defaultOut(node);
    }

    @Override
    public void caseANearEndAstDis(ANearEndAstDis node)
    {
        inANearEndAstDis(node);
        if(node.getNearend() != null)
        {
            node.getNearend().apply(this);
        }
        outANearEndAstDis(node);
    }

    public void inAEndAstDis(AEndAstDis node)
    {
        defaultIn(node);
    }

    public void outAEndAstDis(AEndAstDis node)
    {
        defaultOut(node);
    }

    @Override
    public void caseAEndAstDis(AEndAstDis node)
    {
        inAEndAstDis(node);
        if(node.getEnd() != null)
        {
            node.getEnd().apply(this);
        }
        outAEndAstDis(node);
    }

    public void inADistanceAstPosition(ADistanceAstPosition node)
    {
        defaultIn(node);
    }

    public void outADistanceAstPosition(ADistanceAstPosition node)
    {
        defaultOut(node);
    }

    @Override
    public void caseADistanceAstPosition(ADistanceAstPosition node)
    {
        inADistanceAstPosition(node);
        if(node.getR() != null)
        {
            node.getR().apply(this);
        }
        if(node.getL() != null)
        {
            node.getL().apply(this);
        }
        if(node.getAstDis() != null)
        {
            node.getAstDis().apply(this);
        }
        outADistanceAstPosition(node);
    }

    public void inACoordinateAstPosition(ACoordinateAstPosition node)
    {
        defaultIn(node);
    }

    public void outACoordinateAstPosition(ACoordinateAstPosition node)
    {
        defaultOut(node);
    }

    @Override
    public void caseACoordinateAstPosition(ACoordinateAstPosition node)
    {
        inACoordinateAstPosition(node);
        if(node.getAstCoordinate() != null)
        {
            node.getAstCoordinate().apply(this);
        }
        outACoordinateAstPosition(node);
    }

    public void inAAstPreciseDir(AAstPreciseDir node)
    {
        defaultIn(node);
    }

    public void outAAstPreciseDir(AAstPreciseDir node)
    {
        defaultOut(node);
    }

    @Override
    public void caseAAstPreciseDir(AAstPreciseDir node)
    {
        inAAstPreciseDir(node);
        if(node.getAstSecondPreciseDir() != null)
        {
            node.getAstSecondPreciseDir().apply(this);
        }
        if(node.getAstDir() != null)
        {
            node.getAstDir().apply(this);
        }
        if(node.getFloat() != null)
        {
            node.getFloat().apply(this);
        }
        outAAstPreciseDir(node);
    }

    public void inAAstSecondPreciseDir(AAstSecondPreciseDir node)
    {
        defaultIn(node);
    }

    public void outAAstSecondPreciseDir(AAstSecondPreciseDir node)
    {
        defaultOut(node);
    }

    @Override
    public void caseAAstSecondPreciseDir(AAstSecondPreciseDir node)
    {
        inAAstSecondPreciseDir(node);
        if(node.getAstDir() != null)
        {
            node.getAstDir().apply(this);
        }
        if(node.getFloat() != null)
        {
            node.getFloat().apply(this);
        }
        outAAstSecondPreciseDir(node);
    }

    public void inAAstMultiId(AAstMultiId node)
    {
        defaultIn(node);
    }

    public void outAAstMultiId(AAstMultiId node)
    {
        defaultOut(node);
    }

    @Override
    public void caseAAstMultiId(AAstMultiId node)
    {
        inAAstMultiId(node);
        if(node.getAstMultiId() != null)
        {
            node.getAstMultiId().apply(this);
        }
        if(node.getId() != null)
        {
            node.getId().apply(this);
        }
        outAAstMultiId(node);
    }

    public void inAAstMultiCoordinate(AAstMultiCoordinate node)
    {
        defaultIn(node);
    }

    public void outAAstMultiCoordinate(AAstMultiCoordinate node)
    {
        defaultOut(node);
    }

    @Override
    public void caseAAstMultiCoordinate(AAstMultiCoordinate node)
    {
        inAAstMultiCoordinate(node);
        if(node.getAstMultiCoordinate() != null)
        {
            node.getAstMultiCoordinate().apply(this);
        }
        if(node.getAstCoordinate() != null)
        {
            node.getAstCoordinate().apply(this);
        }
        outAAstMultiCoordinate(node);
    }

    public void inAAddPointsToShapeTypeList(AAddPointsToShapeTypeList node)
    {
        defaultIn(node);
    }

    public void outAAddPointsToShapeTypeList(AAddPointsToShapeTypeList node)
    {
        defaultOut(node);
    }

    @Override
    public void caseAAddPointsToShapeTypeList(AAddPointsToShapeTypeList node)
    {
        inAAddPointsToShapeTypeList(node);
        if(node.getAstMultiId() != null)
        {
            node.getAstMultiId().apply(this);
        }
        outAAddPointsToShapeTypeList(node);
    }

    public void inAAddShapesToShapeTypeList(AAddShapesToShapeTypeList node)
    {
        defaultIn(node);
    }

    public void outAAddShapesToShapeTypeList(AAddShapesToShapeTypeList node)
    {
        defaultOut(node);
    }

    @Override
    public void caseAAddShapesToShapeTypeList(AAddShapesToShapeTypeList node)
    {
        inAAddShapesToShapeTypeList(node);
        if(node.getAstMultiId() != null)
        {
            node.getAstMultiId().apply(this);
        }
        outAAddShapesToShapeTypeList(node);
    }

    public void inAAddTemplateToShapeTypeList(AAddTemplateToShapeTypeList node)
    {
        defaultIn(node);
    }

    public void outAAddTemplateToShapeTypeList(AAddTemplateToShapeTypeList node)
    {
        defaultOut(node);
    }

    @Override
    public void caseAAddTemplateToShapeTypeList(AAddTemplateToShapeTypeList node)
    {
        inAAddTemplateToShapeTypeList(node);
        if(node.getAstMultiId() != null)
        {
            node.getAstMultiId().apply(this);
        }
        outAAddTemplateToShapeTypeList(node);
    }

    public void inAAddCoordinateToShapeTypeList(AAddCoordinateToShapeTypeList node)
    {
        defaultIn(node);
    }

    public void outAAddCoordinateToShapeTypeList(AAddCoordinateToShapeTypeList node)
    {
        defaultOut(node);
    }

    @Override
    public void caseAAddCoordinateToShapeTypeList(AAddCoordinateToShapeTypeList node)
    {
        inAAddCoordinateToShapeTypeList(node);
        if(node.getAstMultiCoordinate() != null)
        {
            node.getAstMultiCoordinate().apply(this);
        }
        outAAddCoordinateToShapeTypeList(node);
    }

    public void inAAddToRoomPointsRoomType(AAddToRoomPointsRoomType node)
    {
        defaultIn(node);
    }

    public void outAAddToRoomPointsRoomType(AAddToRoomPointsRoomType node)
    {
        defaultOut(node);
    }

    @Override
    public void caseAAddToRoomPointsRoomType(AAddToRoomPointsRoomType node)
    {
        inAAddToRoomPointsRoomType(node);
        if(node.getAstMultiId() != null)
        {
            node.getAstMultiId().apply(this);
        }
        outAAddToRoomPointsRoomType(node);
    }

    public void inAAddToRoomShapesRoomType(AAddToRoomShapesRoomType node)
    {
        defaultIn(node);
    }

    public void outAAddToRoomShapesRoomType(AAddToRoomShapesRoomType node)
    {
        defaultOut(node);
    }

    @Override
    public void caseAAddToRoomShapesRoomType(AAddToRoomShapesRoomType node)
    {
        inAAddToRoomShapesRoomType(node);
        if(node.getAstMultiId() != null)
        {
            node.getAstMultiId().apply(this);
        }
        outAAddToRoomShapesRoomType(node);
    }

    public void inAAddToRoomIdRoomType(AAddToRoomIdRoomType node)
    {
        defaultIn(node);
    }

    public void outAAddToRoomIdRoomType(AAddToRoomIdRoomType node)
    {
        defaultOut(node);
    }

    @Override
    public void caseAAddToRoomIdRoomType(AAddToRoomIdRoomType node)
    {
        inAAddToRoomIdRoomType(node);
        if(node.getId() != null)
        {
            node.getId().apply(this);
        }
        outAAddToRoomIdRoomType(node);
    }

    public void inAAddToRoomMultiCoordinateRoomType(AAddToRoomMultiCoordinateRoomType node)
    {
        defaultIn(node);
    }

    public void outAAddToRoomMultiCoordinateRoomType(AAddToRoomMultiCoordinateRoomType node)
    {
        defaultOut(node);
    }

    @Override
    public void caseAAddToRoomMultiCoordinateRoomType(AAddToRoomMultiCoordinateRoomType node)
    {
        inAAddToRoomMultiCoordinateRoomType(node);
        if(node.getAstMultiCoordinate() != null)
        {
            node.getAstMultiCoordinate().apply(this);
        }
        outAAddToRoomMultiCoordinateRoomType(node);
    }

    public void inAAstCoordinate(AAstCoordinate node)
    {
        defaultIn(node);
    }

    public void outAAstCoordinate(AAstCoordinate node)
    {
        defaultOut(node);
    }

    @Override
    public void caseAAstCoordinate(AAstCoordinate node)
    {
        inAAstCoordinate(node);
        if(node.getY() != null)
        {
            node.getY().apply(this);
        }
        if(node.getX() != null)
        {
            node.getX().apply(this);
        }
        outAAstCoordinate(node);
    }
}
