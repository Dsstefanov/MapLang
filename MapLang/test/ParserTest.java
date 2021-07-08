import MapLang.lexer.Lexer;
import MapLang.lexer.LexerException;
import MapLang.node.*;
import MapLang.parser.Parser;
import MapLang.parser.ParserException;
import org.junit.Assert;
import org.junit.Test;

import java.io.*;

public class ParserTest {
    @Test
    public void testCreateMapAST() {
        File in = new File("test/ast-test_create_map.txt");
        Parser parser;
        Start ast = null;
        try {
            FileReader reader = new FileReader(in);
            BufferedReader buffer = new BufferedReader(reader);
            PushbackReader pushbackReader = new PushbackReader(buffer);
            Lexer lex = new Lexer(pushbackReader);
            parser = new Parser(lex);
            ast = parser.parse();

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (LexerException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ParserException e) {
            e.printStackTrace();
        }

        Assert.assertNotNull(ast);
        Assert.assertEquals(ast.getClass(), Start.class);
        Assert.assertEquals(ast.getPProgramStart().getClass(), AInitProgramStart.class);

        var aInitProgramStartNode = (AInitProgramStart) ast.getPProgramStart();

        Assert.assertEquals(aInitProgramStartNode.getStatementOne().getClass(), ACreateMapProgramStart.class);
        Assert.assertEquals(aInitProgramStartNode.getStatementTwo().getClass(), AProgramStart.class);

        var aCreateMapProgramStartNode = (ACreateMapProgramStart) aInitProgramStartNode.getStatementOne();
        var aProgramStartNode = (AProgramStart) aInitProgramStartNode.getStatementTwo();

        Assert.assertEquals(aCreateMapProgramStartNode.getCreateMap().getClass(), ACreateMap.class);
        Assert.assertEquals(aProgramStartNode.getClass(), AProgramStart.class);

    }

    @Test
    public void testCreatePointASTV1() {
        File in = new File("test/ast-test_create_point_v1.txt");
        Parser parser;
        Start ast = null;
        try {
            FileReader reader = new FileReader(in);
            BufferedReader buffer = new BufferedReader(reader);
            PushbackReader pushbackReader = new PushbackReader(buffer);
            Lexer lex = new Lexer(pushbackReader);
            parser = new Parser(lex);
            ast = parser.parse();

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (LexerException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ParserException e) {
            e.printStackTrace();
        }

        Assert.assertNotNull(ast);
        Assert.assertEquals(ast.getClass(), Start.class);
        Assert.assertEquals(ast.getPProgramStart().getClass(), AInitProgramStart.class);

        var aInitProgramStartNode = (AInitProgramStart) ast.getPProgramStart();

        Assert.assertEquals(aInitProgramStartNode.getStatementOne().getClass(), ACreatePointProgramStart.class);
        Assert.assertEquals(aInitProgramStartNode.getStatementTwo().getClass(), AProgramStart.class);

        var aCreatePointProgramStartNode = (ACreatePointProgramStart) aInitProgramStartNode.getStatementOne();
        var aProgramStartNode = (AProgramStart) aInitProgramStartNode.getStatementTwo();

        Assert.assertEquals(aCreatePointProgramStartNode.getCreatePoint().getClass(), ACreatePointCreatePoint.class);
        Assert.assertEquals(aProgramStartNode.getClass(), AProgramStart.class);

    }

    @Test
    public void testCopyPointsASTV1() {
        File in = new File("test/ast-test_copy_points_v1.txt");
        Parser parser;
        Start ast = null;
        try {
            FileReader reader = new FileReader(in);
            BufferedReader buffer = new BufferedReader(reader);
            PushbackReader pushbackReader = new PushbackReader(buffer);
            Lexer lex = new Lexer(pushbackReader);
            parser = new Parser(lex);
            ast = parser.parse();

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (LexerException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ParserException e) {
            e.printStackTrace();
        }

        Assert.assertNotNull(ast);
        Assert.assertEquals(ast.getClass(), Start.class);
        Assert.assertEquals(ast.getPProgramStart().getClass(), AInitProgramStart.class);

        var aInitProgramStartNode = (AInitProgramStart) ast.getPProgramStart();
        Assert.assertEquals(aInitProgramStartNode.getStatementOne().getClass(), ACopyPProgramStart.class);
        Assert.assertEquals(aInitProgramStartNode.getStatementTwo().getClass(), AProgramStart.class);

        var aCopyPProgramStartNode = (ACopyPProgramStart) aInitProgramStartNode.getStatementOne();
        Assert.assertEquals(aCopyPProgramStartNode.getCopyPoints().getClass(), ACopyPoints.class);

        var aCopyPointsNode = (ACopyPoints) aCopyPProgramStartNode.getCopyPoints();
        Assert.assertEquals(aCopyPointsNode.getLeft().getClass(), AAstMultiId.class);
        Assert.assertEquals(aCopyPointsNode.getAstNullableRelation().getClass(), AERelationExplicitAstNullableRelation.class);
        Assert.assertEquals(aCopyPointsNode.getRight().getClass(), AAstMultiId.class);

        var leftAAstMultiId = (AAstMultiId) aCopyPointsNode.getLeft();
        Assert.assertEquals(leftAAstMultiId.getAstMultiId().getClass(), AAstMultiId.class);

        var aERelationExplicitAstNullableRelationNode = (AERelationExplicitAstNullableRelation) aCopyPointsNode.getAstNullableRelation();
        Assert.assertEquals(aERelationExplicitAstNullableRelationNode.getAstPreciseDir().getClass(), AAstPreciseDir.class);
        Assert.assertEquals(aERelationExplicitAstNullableRelationNode.getAstRelationExplicit().getClass(), AAstRelationExplicit.class);

        var rightAAstMultiId = (AAstMultiId) aCopyPointsNode.getRight();
        Assert.assertEquals(rightAAstMultiId.getAstMultiId().getClass(), AAstMultiId.class);

        var aAstPreciseDir = (AAstPreciseDir) aERelationExplicitAstNullableRelationNode.getAstPreciseDir();
        Assert.assertEquals(aAstPreciseDir.getAstDir().getClass(), ARightDirAstDir.class);
    }
}
