package MapLang.runner;

import MapLang.analysis.DepthFirstAdapter;
import MapLang.node.*;

import java.util.*;

public class TypeChecker extends DepthFirstAdapter {
    Helpers helper = new Helpers();
    public Hashtable symbolTable = new Hashtable();
    public TypeChecker() {
        super();
    }

    @Override
    public void outACreateMapProgramStart(ACreateMapProgramStart node) {
        super.outACreateMapProgramStart(node);
        TId identifier = ((ACreateMap) node.getCreateMap()).getId();
        validateSingleIdUsage(identifier, "Map");
    }

    @Override
    public void outACreatePointProgramStart(ACreatePointProgramStart node) {
        super.outACreatePointProgramStart(node);
        try {
            ACreatePointCreatePoint createPoint = (ACreatePointCreatePoint) node.getCreatePoint();
            TId identifier = createPoint.getId();
            validateSingleIdUsage(identifier, "Point");
        } catch (Exception e) {
            ACreatePointExtendedCreatePoint createPoint =
                    (ACreatePointExtendedCreatePoint) node.getCreatePoint();
            TId identifier = createPoint.getId();
            PAstRelation relation = createPoint.getAstRelation();
            typeCheckRelation(relation, node, "Point");
            validateSingleIdUsage(identifier, "Point");
        }
    }

    @Override
    public void outACreateTemplateProgramStart(ACreateTemplateProgramStart node) {
        super.outACreateTemplateProgramStart(node);
        TId identifier = ((ACreateTemplate) node.getCreateTemplate()).getId();
        validateSingleIdUsage(identifier, "Template");
    }

    @Override
    public void outACreateRoomProgramStart(ACreateRoomProgramStart node) {
        super.outACreateRoomProgramStart(node);
        TId leftId = node.getLeft();
        TId rightId = node.getRight();
        idTypeCheck(leftId, node, "Map");
        validateSingleIdUsage(rightId, "Room");
    }

    @Override
    public void outACreateLineProgramStart(ACreateLineProgramStart node) {
        super.outACreateLineProgramStart(node);
        TId identifier = node.getId();
        validateSingleIdUsage(identifier, "Shape");
    }

    @Override
    public void outACreateRectangleOneProgramStart(ACreateRectangleOneProgramStart node) {
        super.outACreateRectangleOneProgramStart(node);
        PAstRelation relation = node.getAstRelation();
        TId identifier = node.getId();
        typeCheckRelation(relation, node, "Point");
        validateSingleIdUsage(identifier, "Rectangle");
    }

    @Override
    public void outACreateRectangleTwoProgramStart(ACreateRectangleTwoProgramStart node) {
        super.outACreateRectangleTwoProgramStart(node);
        TId left1 = node.getLeft1();
        TId left2 = node.getLeft2();
        TId rightId = node.getRight();
        idTypeCheck(left1, node, "Point");
        idTypeCheck(left2, node, "Point");
        validateSingleIdUsage(rightId, "Rectangle");
    }

    @Override
    public void outACreateCircleProgramStart(ACreateCircleProgramStart node) {
        super.outACreateCircleProgramStart(node);
        TId identifier = node.getId();
        PAstRelation relation = node.getAstRelation();
        typeCheckRelation(relation, node, "Point");
        validateSingleIdUsage(identifier, "Circle");
    }

    @Override
    public void outACopyPProgramStart(ACopyPProgramStart node) {
        super.outACopyPProgramStart(node);
        ACopyPoints copyPoints = (ACopyPoints) node.getCopyPoints();
        int line = (((AAstMultiId) copyPoints.getLeft()).getId()).getLine();

        AAstMultiId LHS_multiId = (AAstMultiId) copyPoints.getLeft();
        TId LHS_identifier = LHS_multiId.getId();
        idTypeCheck(LHS_identifier, node, "Point");
        verifyIdListLHS((AAstMultiId) LHS_multiId.getAstMultiId(), "Point");
        validateUniqueIdList(LHS_multiId, node);

        PAstNullableRelation relation = copyPoints.getAstNullableRelation();
        typeCheckRelation(relation, node, "Point");

        AAstMultiId RHS_multiId = (AAstMultiId) copyPoints.getRight();
        TId RHS_identifier = RHS_multiId.getId();
        validateSingleIdUsage(RHS_identifier, "Point");
        verifyIdListRHS((AAstMultiId) RHS_multiId.getAstMultiId(), "Point");
        validateUniqueIdList(RHS_multiId, node);


        validateNumberOfIdsMatch(LHS_multiId, RHS_multiId, node, line);
    }

    @Override
    public void outACopyTProgramStart(ACopyTProgramStart node) {
        super.outACopyTProgramStart(node);
        ACopyTemplate copyTemplate = (ACopyTemplate) node.getCopyTemplate();
        TId templateId = copyTemplate.getLeft();
        TId newTemplateId = copyTemplate.getRight();
        PAstNullableRelation relation = copyTemplate.getAstNullableRelation();

        idTypeCheck(templateId, node, "Template");
        typeCheckRelation(relation, node, "Point");
        validateSingleIdUsage(newTemplateId, "Template");
    }

    @Override
    public void outACopySProgramStart(ACopySProgramStart node) {
        super.outACopySProgramStart(node);

        ACopyShapes copyShapes = (ACopyShapes) node.getCopyShapes();

        AAstMultiId LHS_multiId = (AAstMultiId) copyShapes.getLeft();
        TId LHS_identifier = LHS_multiId.getId();
        idTypeCheck(LHS_identifier, node, "Shape");
        verifyIdListLHS((AAstMultiId) LHS_multiId.getAstMultiId(), "Shape");
        validateUniqueIdList(LHS_multiId, node);

        PAstNullableRelation relation = copyShapes.getAstNullableRelation();
        typeCheckRelation(relation, node, "Point");


        AAstMultiId RHS_multiId = (AAstMultiId) copyShapes.getRight();
        TId RHS_identifier = RHS_multiId.getId();
        validateSingleIdUsage(RHS_identifier, "Shape");
        verifyIdListRHS((AAstMultiId) RHS_multiId.getAstMultiId(), "Shape");
        validateUniqueIdList(RHS_multiId, node);
    }

    //add to room
    @Override
    public void outAAddToRoomProgramStart(AAddToRoomProgramStart node) {
        super.outAAddToRoomProgramStart(node);
        idTypeCheck(node.getId(), node, "Room");

    }
    @Override
    public void outAAddToRoomPointsRoomType (AAddToRoomPointsRoomType node) {
        super.outAAddToRoomPointsRoomType(node);
        multiIdTypeCheck(node.getAstMultiId(), node, "Point");
    }
    @Override
    public void outAAddToRoomShapesRoomType (AAddToRoomShapesRoomType node) {
        super.outAAddToRoomShapesRoomType(node);
        multiIdTypeCheck(node.getAstMultiId(), node, "Shape");
    }
    @Override
    public void outAAddToRoomIdRoomType (AAddToRoomIdRoomType node) {
        super.outAAddToRoomIdRoomType(node);
        idTypeCheck(node.getId(), node, "Template");
    }


    //add to shape
    @Override
    public void outAAddToShapeProgramStart(AAddToShapeProgramStart node) {
        super.outAAddToShapeProgramStart(node);
        idTypeCheck(node.getId(), node, "Shape");

    }


    //type list
    @Override
    public void outAAddPointsToShapeTypeList(AAddPointsToShapeTypeList node) {
        super.outAAddPointsToShapeTypeList(node);
        multiIdTypeCheck(node.getAstMultiId(), node, "Point");
    }
    @Override
    public void outAAddShapesToShapeTypeList(AAddShapesToShapeTypeList node) {
        super.outAAddShapesToShapeTypeList(node);
        multiIdTypeCheck(node.getAstMultiId(), node, "Shape");
    }
    @Override
    public void outAAddTemplateToShapeTypeList(AAddTemplateToShapeTypeList node) {
        super.outAAddTemplateToShapeTypeList(node);
        multiIdTypeCheck(node.getAstMultiId(), node, "Template");
    }


    //add to template
    @Override
    public void outAAddToTemplateProgramStart(AAddToTemplateProgramStart node) {
        super.outAAddToTemplateProgramStart(node);
        idTypeCheck(node.getId(), node, "Template");
    }



    //position
    @Override
    public void outADistanceAstPosition (ADistanceAstPosition node) {
        super.outADistanceAstPosition(node);
        idTypeCheck(node.getL(), node, "Point");
        idTypeCheck(node.getR(), node, "Point");
    }


    /*edit
    @Override
    public void outAEditPointProgramStart(AEditPointProgramStart node) {
        super.outAEditPointProgramStart(node);
        idTypeCheck(node.getId(), node, "Point");
        typeCheckRelation(node.getAstNullableRelation(), node, "Point");
    }*/

    @Override
    public void outAAddDoorwayPositionProgramStart(AAddDoorwayPositionProgramStart node) {
        super.outAAddDoorwayPositionProgramStart(node);
        idTypeCheck(node.getId(), node, "Map");
    }

    @Override
    public void outAAddWindowPositionProgramStart(AAddWindowPositionProgramStart node) {
        super.outAAddWindowPositionProgramStart(node);
        idTypeCheck(node.getId(), node, "Map");
    }

    @Override
    public void outAAddExitPositionProgramStart(AAddExitPositionProgramStart node) {
        super.outAAddExitPositionProgramStart(node);
        idTypeCheck(node.getId(), node, "Map");
    }



    private void idTypeCheck(TId tId, Node node, String type) {
        String id = tId.getText();
        int line = tId.getLine();
        int col = tId.getPos();
        String typeValue = (String) symbolTable.get(id.toUpperCase());
        if(typeValue == null) {
            System.out.println("Identifier doesn't exist: " + id + ", line: " + line + ", column: " + col);
            System.exit(1);
        } else if (type.equals("Shape")) {
           ArrayList<String> types = new ArrayList<>();
           types.add(type);
           types.add("Rectangle");
           types.add("Circle");
           if(!types.contains(typeValue.toString())) {
               System.out.println("A type mismatch at: " + node.toString() + " at identifier: " + id + ", line: " + line);
               System.out.println("Expected: " + types.toString() + ", Actual: " + typeValue);
               System.exit(1);

           }
        } else if (!typeValue.equals(type)) {
            System.out.println("A type mismatch at: " + node.toString() + " at identifier: " + id + ", line: " + line);
            System.out.println("Expected: " + type + ", Actual: " + typeValue);
            System.exit(1);
        }
    }

    private void validateSingleIdUsage(TId tid, String type) {
        String key = tid.getText();
        String upperCaseKey = key.toUpperCase();
        int col = tid.getPos();
        int line = tid.getLine();
        if (symbolTable.containsKey(upperCaseKey)) {
            System.out.println("Duplicate identifier defined in line: " + line + ", position: " +
                    col + " at key: " + key);
            System.exit(1);
        }
        else {
            symbolTable.put(upperCaseKey, type);
        }
    }



    private void verifyIdListLHS(AAstMultiId idList, String type) {
        if(idList == null) return;
        TId initialId = idList.getId();
        idTypeCheck(initialId, idList, type);
        PAstMultiId tempIdList = idList.getAstMultiId();
        if (tempIdList == null || tempIdList.toString().isEmpty()) return;
        else verifyIdListLHS((AAstMultiId) tempIdList, type);
    }

    private void verifyIdListRHS(AAstMultiId idList, String type) {
        if(idList == null) return;
        TId initialId = idList.getId();
        validateSingleIdUsage(initialId, type);
        PAstMultiId tempIdList = idList.getAstMultiId();
        if (tempIdList == null || tempIdList.toString().isEmpty()) return;
        else verifyIdListRHS((AAstMultiId) tempIdList, type);
    }

    private void validateUniqueIdList(AAstMultiId multiId, Node node) {
        List<String> resList = new ArrayList<>();
        List<String> list = toNormalList(multiId, resList);
        Set<String> duplicates = findDuplicates(list);
        if(!duplicates.isEmpty())
        {
            System.out.print("Duplication of one or more identifiers: ");
            duplicates.forEach(System.out::print);
            System.out.print("in declaration: \n" + node.toString());
            System.exit(0);
        }
    }

    private Set<String> findDuplicates(List<String> listContainingDuplicates)
    {
        final Set<String> setToReturn = new HashSet<String>();
        final Set<String> set1 = new HashSet<>();

        for (String yourString : listContainingDuplicates)
        {
            if (!set1.add(yourString))
            {
                setToReturn.add(yourString);
            }
        }
        return setToReturn;
    }

    private List<String> toNormalList(AAstMultiId list, List<String> res) {
        TId id = list.getId();
        res.add(id.getText().toUpperCase());

        PAstMultiId tempIdList = list.getAstMultiId();
        if(tempIdList == null || tempIdList.toString().equals("")) return res;
        return toNormalList((AAstMultiId) tempIdList,res);
    }

    private void validateNumberOfIdsMatch(PAstMultiId lhs, PAstMultiId rhs, Node node, Integer line){
        int RHS_length = getPIdListLength((AAstMultiId) rhs);
        int LHS_length = getPIdListLength((AAstMultiId) lhs);

        if(RHS_length!=LHS_length) {
            System.out.println("Number of identifiers do not match in declaration in line " + line + ": \n" + node.toString());
            System.exit(0);
        }
    }

    private int getPIdListLength(AAstMultiId list)
    {
        int count = 0;
        TId first = list.getId();
        if(first.getText().equals("")) return 0;

        PAstMultiId tempIdList = list.getAstMultiId();
        if (tempIdList.toString().isEmpty()) return 1;
        return getPIdListLength((AAstMultiId) tempIdList,1);
    }

    private int getPIdListLength(AAstMultiId list, int count) {
        if (list.toString().isEmpty()) return count;
        else
        {
            PAstMultiId tempIdList = list.getAstMultiId();
            if (tempIdList == null || tempIdList.toString().isEmpty()) return count+1;
            return getPIdListLength((AAstMultiId) tempIdList,count+1);
        }
    }

    private void typeCheckRelation(PAstRelation relation, Node node, String type) {
        try {
            ARelationExplicitAstRelation relRelation = (ARelationExplicitAstRelation) relation;
            typeCheckRelationExplicit(relRelation.getAstRelationExplicit(), node, type);
        } catch (ClassCastException e) {
            //no need to type check the coordinate
        }
    }

    private void typeCheckRelation(PAstNullableRelation relation, Node node, String type) {
        AERelationExplicitAstNullableRelation relRelation = null;
        try {
            relRelation = (AERelationExplicitAstNullableRelation) relation;
            PAstRelationExplicit relExplicit = relRelation.getAstRelationExplicit();
            typeCheckRelationExplicit(relExplicit, node, type);
        } catch (ClassCastException e) {
            //no need to type check the coordinate or the lack of id
        }

    }

    private void typeCheckRelationExplicit(PAstRelationExplicit relExplicit, Node node, String type) {
        //Type check the explicit relation
        TId tId = ((AAstRelationExplicit)relExplicit).getId();
        idTypeCheck(tId, node,type);
    }


    private void multiIdTypeCheck(PAstMultiId multiId, Node node, String type) {
        ArrayList<TId> tIds = helper.multiIdToTIdList(multiId);
        validateUniqueIdList((AAstMultiId) multiId, node);
        //Type checking
        tIds.forEach(tId -> {
            idTypeCheck(tId, node, type);
        });

    }




//    @Override
//    public void outACopyPointsCCopy(ACopyPointsCCopy node) {
//        super.outACopyPointsCCopy(node);
//        ACopyPoints cp = (ACopyPoints) node.getCopyPoints();
//        PNullableRelation relation = ((ACopyPoints) node.getCopyPoints()).getNullableRelation();
//
//
//        //Type check the relation type
//        typeCheckRelation(relation, node, "Point");
//
//        //Validate number of identifiers match
//        PMultiId rhs_ids = cp.getRight();
//        PMultiId lhs_ids = cp.getLeft();
//        ArrayList<TId> idList = idListToTIdList(rhs_ids);
//        ArrayList<TId> idList2 = idListToTIdList(lhs_ids);
//        idList.addAll(idList2);
//
//        // Type checking
//        idList.forEach(tId -> {
//            idTypeCheck(tId, node, "Point");
//        });
//    }
//

//

//

//
//    @Override
//    public void outAEqualRelationCreatePoint(AEqualRelationCreatePoint node) {
//        super.outAEqualRelationCreatePoint(node);
//        PRelation relation = node.getRelation();
//        //check if the semantic analyzer has put the correct type
//        TId tId = node.getId();
//        idTypeCheck(tId, node, "Point");
//        //type check the relation
//        typeCheckRelation(relation,node,"Point");
//    }
//
//    @Override
//    public void outACreateRoom(ACreateRoom node) {
//        super.outACreateRoom(node);
//
//        //Check if the room is creating in a Map
//        TId LHS_identifier = node.getLeft();
//        idTypeCheck(LHS_identifier, node, "Map");
//
//        //check if the semantic analyzer has put the correct type
//        TId RHS_identifier = node.getRight();
//        idTypeCheck(RHS_identifier, node, "Room");
//    }


}
