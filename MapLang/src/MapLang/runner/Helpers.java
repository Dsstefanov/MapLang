package MapLang.runner;
import MapLang.node.*;


import java.util.ArrayList;

public class Helpers {

    public ArrayList<TId> multiIdToTIdList(PAstMultiId pidList) {
        ArrayList<TId> tIds = new ArrayList<TId>();
        AAstMultiId multiId = (AAstMultiId) pidList;
        TId id = multiId.getId();
        tIds.add(id);
        return getIdentifierFromIdList((AAstMultiId) multiId.getAstMultiId(), tIds);
    }

    public ArrayList<AAstCoordinate> multiCoordinateToTCoordinateList(PAstMultiCoordinate pcoordinateList) {
        ArrayList<AAstCoordinate> tCoordinates = new ArrayList<AAstCoordinate>();
        AAstMultiCoordinate coordinateList = (AAstMultiCoordinate) pcoordinateList;
        PAstCoordinate coordinate = coordinateList.getAstCoordinate();
        tCoordinates.add((AAstCoordinate) coordinate);
        return getCoordinateFromCoordinateList((AAstMultiCoordinate) coordinateList.getAstMultiCoordinate(), tCoordinates);
    }

    public ArrayList<TId> typeListToTIdList(PTypeList typeList) {
        ArrayList<TId> tIds = new ArrayList<TId>();

        Class<? extends PTypeList> switchCase = typeList.getClass();
        switch (switchCase.getSimpleName()) {
            case  "AAddShapesToShapeTypeList":
                AAddShapesToShapeTypeList shapeIds = (AAddShapesToShapeTypeList) typeList;
                tIds = multiIdToTIdList(shapeIds.getAstMultiId());
                break;
            case  "AAddPointsToShapeTypeList":
                AAddPointsToShapeTypeList pointIds = (AAddPointsToShapeTypeList) typeList;
                tIds = multiIdToTIdList(pointIds.getAstMultiId());
                break;
            case  "AAddTemplateToShapeTypeList":
                AAddTemplateToShapeTypeList templateIds = (AAddTemplateToShapeTypeList) typeList;
                tIds = multiIdToTIdList(templateIds.getAstMultiId());
                break;
            case  "AAddCoordinateToShapeTypeList":
                tIds = new ArrayList<>();
                break;
            default: break;
        }
        return tIds;
    }

    public ArrayList<TId> roomTypeToTIdList(PRoomType typeList) {
        ArrayList<TId> tIds = new ArrayList<TId>();

        Class<? extends PRoomType> switchCase = typeList.getClass();
        switch (switchCase.getSimpleName()) {
            case  "AAddToRoomShapesRoomType":
                AAddToRoomShapesRoomType shapeIds = (AAddToRoomShapesRoomType) typeList;
                tIds = multiIdToTIdList(shapeIds.getAstMultiId());
                break;
            case  "AAddToRoomPointsRoomType":
                AAddToRoomPointsRoomType pointIds = (AAddToRoomPointsRoomType) typeList;
                tIds = multiIdToTIdList(pointIds.getAstMultiId());
                break;
            case  "AAddToRoomIdRoomType": // Template
                AAddToRoomIdRoomType templateIds = (AAddToRoomIdRoomType) typeList;
                ArrayList<TId> templateList = new ArrayList<TId>();
                templateList.add(templateIds.getId());
                tIds = templateList;
                break;
            case  "AAddToRoomMultiCoordinateRoomType":
                tIds = new ArrayList<>();
                break;
            default: break;
        }
        return tIds;
    }

    public ArrayList<AAstCoordinate> typeListToTCoordinateList(PTypeList typeList) {
        ArrayList<AAstCoordinate> coordinates = new ArrayList<AAstCoordinate>();

        Class<? extends PTypeList> switchCase = typeList.getClass();
        switch (switchCase.getSimpleName()) {
            case  "AAddPointsToShapeTypeList":
                coordinates =  new ArrayList<>();
                break;
            case  "AAddShapesToShapeTypeList":
                coordinates =  new ArrayList<>();
                break;
            case  "AAddTemplateToShapeTypeList":
                coordinates =  new ArrayList<>();
                break;
            case  "AAddCoordinateToShapeTypeList":
                AAddCoordinateToShapeTypeList astCoordinates = (AAddCoordinateToShapeTypeList) typeList;
                coordinates = multiCoordinateToTCoordinateList(astCoordinates.getAstMultiCoordinate());
                break;
            default: break;
        }
        return coordinates;
    }

    public ArrayList<AAstCoordinate> roomTypeToTCoordinateList(PRoomType typeList) {
        ArrayList<AAstCoordinate> coordinates = new ArrayList<AAstCoordinate>();

        Class<? extends PRoomType> switchCase = typeList.getClass();
        switch (switchCase.getSimpleName()) {
            case  "AAddToRoomPointsRoomType":
            case  "AAddToRoomShapesRoomType":
            case  "AAddToRoomIdRoomType":
                coordinates =  new ArrayList<>();
                break;
            case  "AAddToRoomMultiCoordinateRoomType":
                AAddToRoomMultiCoordinateRoomType astCoordinates = (AAddToRoomMultiCoordinateRoomType) typeList;
                coordinates = multiCoordinateToTCoordinateList(astCoordinates.getAstMultiCoordinate());
                break;
            default: break;
        }
        return coordinates;
    }

    public ArrayList<TId> getIdentifierFromIdList(AAstMultiId idList, ArrayList<TId> tIds) {
        if(idList == null) {
            return tIds;
        }
        TId initialId = idList.getId();
        String initialKey = initialId.getText();
        tIds.add(initialId);
        PAstMultiId tempIdList = idList.getAstMultiId();
        if (tempIdList == null || tempIdList.toString().isEmpty()) return tIds;
        else return getIdentifierFromIdList((AAstMultiId) tempIdList, tIds);
    }

    public ArrayList<AAstCoordinate> getCoordinateFromCoordinateList(AAstMultiCoordinate coordinateList, ArrayList<AAstCoordinate> tCoordiantes) {
        if (coordinateList == null) {
            return tCoordiantes;
        }
        AAstCoordinate coordinate = (AAstCoordinate) coordinateList.getAstCoordinate();

        tCoordiantes.add(coordinate);
        PAstMultiCoordinate tempIdList = coordinateList.getAstMultiCoordinate();
        if (tempIdList == null || tempIdList.toString().isEmpty()) return tCoordiantes;
        else return getCoordinateFromCoordinateList((AAstMultiCoordinate) tempIdList, tCoordiantes);
    }

}
