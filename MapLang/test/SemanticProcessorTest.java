import MapLang.classes.Point;
import MapLang.node.*;
import MapLang.runner.SemanticProcessor;
import org.junit.Assert;
import org.junit.Test;

public class SemanticProcessorTest {
    private static final double DELTA = 1e-15;

    @Test
    public void testOutACopyPProgramStart() {
        SemanticProcessor semanticProcessor = new SemanticProcessor();
        semanticProcessor.symbolTable.put("P1", new Point("p1", (float) -35.0,(float) 25.0));
        semanticProcessor.symbolTable.put("P2", new Point("p2", (float) -25.0,(float) 25.0));
        semanticProcessor.symbolTable.put("P3", new Point("p3", (float) -15.0,(float) 10.0));

        ACopyPoints assertObj = new ACopyPoints();

        AERelationExplicitAstNullableRelation relation = new AERelationExplicitAstNullableRelation();
        AAstPreciseDir dir = new AAstPreciseDir();
        var rightDir = new ARightDirAstDir();
        rightDir.setRight(new TRight());
        dir.setAstDir(rightDir);
        dir.setFloat(new TFloat("30.0"));
        dir.setAstSecondPreciseDir(new AAstSecondPreciseDir());
        dir.setAstSecondPreciseDir(null);
        relation.setAstPreciseDir(dir);

        AAstRelationExplicit explicit = new AAstRelationExplicit();
        explicit.setId(new TId("p3"));

        relation.setAstRelationExplicit(explicit);

        AAstMultiId lhs = new AAstMultiId();
        lhs.setId(new TId("p1"));
        var lhsTail = new AAstMultiId();
        lhsTail.setId(new TId("p2"));
        lhs.setAstMultiId(lhsTail);

        AAstMultiId rhs = new AAstMultiId();
        rhs.setId(new TId("p4"));
        var rhsTail = new AAstMultiId();
        rhsTail.setId(new TId("p5"));
        rhs.setAstMultiId(rhsTail);

        assertObj.setAstNullableRelation(relation);
        assertObj.setLeft(lhs);
        assertObj.setRight(rhs);

        semanticProcessor.outACopyPoints(assertObj);

        AAstMultiId assertLhs = (AAstMultiId) assertObj.getLeft();
        AAstMultiId assertRhs = (AAstMultiId) assertObj.getRight();

        String rhsKey1 = assertRhs.getId().getText();
        String upperCaseRhsKey1 = rhsKey1.toUpperCase();

        var tail = (AAstMultiId) assertRhs.getAstMultiId();
        String rhsKey2 = tail.getId().getText();
        String upperCaseRhsKey2 = rhsKey2.toUpperCase();

        Object symboltableLookupRhs1 = semanticProcessor.symbolTable.get(upperCaseRhsKey1);
        Object symboltableLookupRhs2 = semanticProcessor.symbolTable.get(upperCaseRhsKey2);

        Assert.assertEquals(symboltableLookupRhs1.getClass(), Point.class);
        Point rhsPoint1 = (Point) symboltableLookupRhs1;
        Assert.assertEquals("P4", rhsPoint1.getId());
        Assert.assertEquals((float) 15.0, rhsPoint1.getX(), DELTA);
        Assert.assertEquals((float) 10.0, rhsPoint1.getY(), DELTA);

        Assert.assertEquals(symboltableLookupRhs2.getClass(), Point.class);
        Point rhsPoint2 = (Point) symboltableLookupRhs2;
        Assert.assertEquals("P5", rhsPoint2.getId());
        Assert.assertEquals((float) 25.0, rhsPoint2.getX(), DELTA);
        Assert.assertEquals((float) 10.0, rhsPoint2.getY(), DELTA);
    }
}
