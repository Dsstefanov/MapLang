package MapLang.runner;
import MapLang.analysis.*;
import MapLang.node.*;

import java.util.Hashtable;

public class ASTFixer extends DepthFirstAdapter
{
    // A single instance.  (Assumes no multi-threading).
    private static final ASTFixer instance = new ASTFixer();

    private final Hashtable results = new Hashtable();

    private ASTFixer()
    {
    }

    public static void fix(Node ast)
    {
        ast.apply(instance);
    }
}