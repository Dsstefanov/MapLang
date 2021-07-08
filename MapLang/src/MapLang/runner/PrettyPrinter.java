package MapLang.runner;

import MapLang.analysis.*;
import MapLang.node.*;

public class PrettyPrinter extends DepthFirstAdapter {
    protected String filename;

    private static final PrettyPrinter instance = new PrettyPrinter();
    public PrettyPrinter() { }

    private int indent = 0;

    private void indent()
    {
        System.out.print(System.getProperty("line.separator"));
        for(int i = 0; i < indent; i++)
        {
            System.out.print("  ");
        }
    }

    public static void print(Node ast)
    {
        ast.apply(instance);
    }

    public void defaultIn(Node node)
    {
        indent();
        System.out.print(node.getClass() + ":[");
        indent++;
    }

    public void defaultOut(Node node)
    {
        indent--;
        indent();
        System.out.print("]");
    }

    public void defaultCase(Node node)
    {
        indent();
        System.out.print("(" + ((Token) node).getText() + ")");
    }
}