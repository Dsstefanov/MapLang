package MapLang.runner;
import MapLang.lexer.*;
import MapLang.node.*;
import MapLang.parser.*;
import java.io.* ;

/**
 * Created by CodeCouple
 */
public class Main {
    public static void main(String[] arguments)
    {
        File in;
        File dir;

        try
        {
            in = new File(arguments[0]);

            FileReader temp = new FileReader(in);

            // Build the AST
            Node ast = new Parser(
                    new Lexer(
                            new PushbackReader(
                                    new BufferedReader(
                                            new FileReader(arguments[0])), 1024))).parse();

            //ast.apply(semanticAnalyzer);
            ast.apply(new TypeChecker());
            ast.apply(new SemanticProcessor());
            //PrettyPrinter.print(ast);
        }
        catch(LexerException le)
        {
            System.out.println("Lexer : "+le.getMessage());
            le.printStackTrace();
        }
        catch(ParserException pe)
        {
            System.out.println("Parser : "+pe.getMessage());
            pe.printStackTrace();
        }
        catch(Exception e)
        {
            System.out.println("Other Exception : "+e.getMessage());
            e.printStackTrace();
        }
    }}
