import MapLang.lexer.Lexer;
import MapLang.lexer.LexerException;
import MapLang.node.*;
import org.junit.Assert;
import org.junit.Test;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class ScannerTest {
    @Test
    public void testCreateMapTokenization() {
        File in = new File("test/scannerLegalInput.txt");
        Lexer lex;
        FileReader reader = null;
        PushbackReader pushbackReader = null;

        List<AssertObjToken> expectedTokens = new ArrayList<AssertObjToken>();
        expectedTokens.add(new AssertObjToken(1,1," ", TBlank.class));
        expectedTokens.add(new AssertObjToken(1,2,"*", TStar.class));
        expectedTokens.add(new AssertObjToken(1,3,"/", TSlash.class));
        expectedTokens.add(new AssertObjToken(1,4,"+", TPlus.class));
        expectedTokens.add(new AssertObjToken(1,5,"-", TMinus.class));
        expectedTokens.add(new AssertObjToken(1,6,"(", TLParen.class));
        expectedTokens.add(new AssertObjToken(1,7,")", TRParen.class));
        expectedTokens.add(new AssertObjToken(1,8,";", TSemicolon.class));
        expectedTokens.add(new AssertObjToken(1,9,"=", TEqual.class));
        expectedTokens.add(new AssertObjToken(1,10,",", TComma.class));
        expectedTokens.add(new AssertObjToken(1,11,"\r\n", TEol.class));

        expectedTokens.add(new AssertObjToken(2,1,"0", TInt.class));
        expectedTokens.add(new AssertObjToken(2,2," ", TBlank.class));
        expectedTokens.add(new AssertObjToken(2,3,"1230", TInt.class));
        expectedTokens.add(new AssertObjToken(2,7,"\r\n", TEol.class));

        expectedTokens.add(new AssertObjToken(3,1,"1.222", TFloat.class));
        expectedTokens.add(new AssertObjToken(3,6," ", TBlank.class));
        expectedTokens.add(new AssertObjToken(3,7,"0.2222", TFloat.class));
        expectedTokens.add(new AssertObjToken(3,13," ", TBlank.class));
        expectedTokens.add(new AssertObjToken(3,14,"-0.00123", TFloat.class));
        expectedTokens.add(new AssertObjToken(3,22,"\r\n", TEol.class));

        expectedTokens.add(new AssertObjToken(4, 1, "create", TCreate.class));
        expectedTokens.add(new AssertObjToken(4, 7, "\r\n", TEol.class));

        expectedTokens.add(new AssertObjToken(5, 1, "map", TMap.class));
        expectedTokens.add(new AssertObjToken(5, 4, "\r\n", TEol.class));

        expectedTokens.add(new AssertObjToken(6, 1, "point", TPoint.class));
        expectedTokens.add(new AssertObjToken(6, 6, "\r\n", TEol.class));

        expectedTokens.add(new AssertObjToken(7, 1, "from", TFrom.class));
        expectedTokens.add(new AssertObjToken(7, 5, "\r\n", TEol.class));

        expectedTokens.add(new AssertObjToken(8, 1, "to", TTo.class));
        expectedTokens.add(new AssertObjToken(8, 3, "\r\n", TEol.class));

        expectedTokens.add(new AssertObjToken(9, 1, "at", TAt.class));
        expectedTokens.add(new AssertObjToken(9, 3, "\r\n", TEol.class));

        expectedTokens.add(new AssertObjToken(10, 1, "by", TBy.class));
        expectedTokens.add(new AssertObjToken(10, 3, "\r\n", TEol.class));

        expectedTokens.add(new AssertObjToken(11, 1, "right", TRight.class));
        expectedTokens.add(new AssertObjToken(11, 6, "\r\n", TEol.class));

        expectedTokens.add(new AssertObjToken(12, 1, "left", TLeft.class));
        expectedTokens.add(new AssertObjToken(12, 5, "\r\n", TEol.class));

        expectedTokens.add(new AssertObjToken(13, 1, "up", TUp.class));
        expectedTokens.add(new AssertObjToken(13, 3, "\r\n", TEol.class));

        expectedTokens.add(new AssertObjToken(14, 1, "down", TDown.class));
        expectedTokens.add(new AssertObjToken(14, 5, "\r\n", TEol.class));

        expectedTokens.add(new AssertObjToken(15, 1, "and", TAnd.class));
        expectedTokens.add(new AssertObjToken(15, 4, "\r\n", TEol.class));

        expectedTokens.add(new AssertObjToken(16, 1, "copy", TCopy.class));
        expectedTokens.add(new AssertObjToken(16, 5, "\r\n", TEol.class));

        expectedTokens.add(new AssertObjToken(17, 1, "add", TAdd.class));
        expectedTokens.add(new AssertObjToken(17, 4, "\r\n", TEol.class));

        expectedTokens.add(new AssertObjToken(18, 1, "room", TRoom.class));
        expectedTokens.add(new AssertObjToken(18, 5, "\r\n", TEol.class));

        expectedTokens.add(new AssertObjToken(19, 1, "template", TTemplate.class));
        expectedTokens.add(new AssertObjToken(19, 9, "\r\n", TEol.class));

        expectedTokens.add(new AssertObjToken(20, 1, "templates", TTemplates.class));
        expectedTokens.add(new AssertObjToken(20, 10, "\r\n", TEol.class));

        expectedTokens.add(new AssertObjToken(21, 1, "shape", TShape.class));
        expectedTokens.add(new AssertObjToken(21, 6, "\r\n", TEol.class));

        expectedTokens.add(new AssertObjToken(22, 1, "coordinates", TCoordinates.class));
        expectedTokens.add(new AssertObjToken(22, 12, "\r\n", TEol.class));

        expectedTokens.add(new AssertObjToken(23, 1, "in", TIn.class));
        expectedTokens.add(new AssertObjToken(23, 3, "\r\n", TEol.class));

        expectedTokens.add(new AssertObjToken(24, 1, "floor", TFloor.class));
        expectedTokens.add(new AssertObjToken(24, 6, "\r\n", TEol.class));

        expectedTokens.add(new AssertObjToken(25, 1, "rectangle", TRectangle.class));
        expectedTokens.add(new AssertObjToken(25, 10, "\r\n", TEol.class));

        expectedTokens.add(new AssertObjToken(26, 1, "circle", TCircle.class));
        expectedTokens.add(new AssertObjToken(26, 7, "\r\n", TEol.class));

        expectedTokens.add(new AssertObjToken(27, 1, "points", TPoints.class));
        expectedTokens.add(new AssertObjToken(27, 7, "\r\n", TEol.class));

        expectedTokens.add(new AssertObjToken(28, 1, "shapes", TShapes.class));
        expectedTokens.add(new AssertObjToken(28, 7, "\r\n", TEol.class));

        expectedTokens.add(new AssertObjToken(29, 1, "doorway", TDoorway.class));
        expectedTokens.add(new AssertObjToken(29, 8, "\r\n", TEol.class));

        expectedTokens.add(new AssertObjToken(30, 1, "window", TWindow.class));
        expectedTokens.add(new AssertObjToken(30, 7, "\r\n", TEol.class));

        expectedTokens.add(new AssertObjToken(31, 1, "exit", TExit.class));
        expectedTokens.add(new AssertObjToken(31, 5, "\r\n", TEol.class));

        expectedTokens.add(new AssertObjToken(32, 1, "start", TStart.class));
        expectedTokens.add(new AssertObjToken(32, 6, "\r\n", TEol.class));

        expectedTokens.add(new AssertObjToken(33, 1, "nearstart", TNearstart.class));
        expectedTokens.add(new AssertObjToken(33, 10, "\r\n", TEol.class));

        expectedTokens.add(new AssertObjToken(34, 1, "halfway", THalfway.class));
        expectedTokens.add(new AssertObjToken(34, 8, "\r\n", TEol.class));

        expectedTokens.add(new AssertObjToken(35, 1, "nearend", TNearend.class));
        expectedTokens.add(new AssertObjToken(35, 8, "\r\n", TEol.class));

        expectedTokens.add(new AssertObjToken(36, 1, "end", TEnd.class));
        expectedTokens.add(new AssertObjToken(36, 4, "\r\n", TEol.class));

        expectedTokens.add(new AssertObjToken(37, 1, "move", TMove.class));
        expectedTokens.add(new AssertObjToken(37, 5, "\r\n", TEol.class));

        expectedTokens.add(new AssertObjToken(38, 1, "cassiopeia", TId.class));
        expectedTokens.add(new AssertObjToken(38, 11,"\r\n", TEol.class));

        expectedTokens.add(new AssertObjToken(39, 1,"testid", TId.class));
        expectedTokens.add(new AssertObjToken(39, 7,"\r\n", TEol.class));

        List<Token> actualTokens = new ArrayList<Token>();


        try {
            reader = new FileReader(in);
            BufferedReader buffer = new BufferedReader(reader);
            pushbackReader = new PushbackReader(buffer);
            lex = new Lexer(pushbackReader);
            Token current = lex.next();

            var tokenClass = current.getClass();
            while(tokenClass != EOF.class){
                actualTokens.add(current);
                current = lex.next();
                tokenClass = current.getClass();
            }

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (LexerException e) {
            e.printStackTrace();
        }

        for (int i = 0; i < actualTokens.size(); i++){
            Token actual = actualTokens.get(i);
            AssertObjToken expectedToken = expectedTokens.get(i);
            Assert.assertEquals(expectedToken.getType(), actual.getClass());
            Assert.assertEquals(expectedToken.getPosition(), actual.getPos());
            Assert.assertEquals(expectedToken.getLineNumber(), actual.getLine());
        }
    }

    @Test
    public void testCreateMapTokenizationIllegal() {
        File in = new File("test/scannerIlligalInput.txt");
        Lexer lex;
        FileReader reader = null;
        PushbackReader pushbackReader = null;

        List<AssertObjToken> expectedTokens = new ArrayList<AssertObjToken>();
        expectedTokens.add(new AssertObjToken(1,1,"#", null));

        List<Token> actualTokens = new ArrayList<Token>();


        try {
            reader = new FileReader(in);
            BufferedReader buffer = new BufferedReader(reader);
            pushbackReader = new PushbackReader(buffer);
            lex = new Lexer(pushbackReader);
            Token current = lex.next();

            var tokenClass = current.getClass();
            while(tokenClass != EOF.class){
                actualTokens.add(current);
                current = lex.next();
                tokenClass = current.getClass();
            }

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (LexerException e) {
            Assert.assertEquals(expectedTokens.get(0).getText(), e.getToken().getText());
            Assert.assertEquals(expectedTokens.get(0).getLineNumber(), e.getToken().getLine());
            Assert.assertEquals(expectedTokens.get(0).getPosition(), e.getToken().getPos());
        }
    }
}
