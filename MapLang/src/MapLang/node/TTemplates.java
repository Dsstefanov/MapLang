/* This file was generated by SableCC (http://www.sablecc.org/). */

package MapLang.node;

import MapLang.analysis.*;

@SuppressWarnings("nls")
public final class TTemplates extends Token
{
    public TTemplates()
    {
        super.setText("templates");
    }

    public TTemplates(int line, int pos)
    {
        super.setText("templates");
        setLine(line);
        setPos(pos);
    }

    @Override
    public Object clone()
    {
      return new TTemplates(getLine(), getPos());
    }

    @Override
    public void apply(Switch sw)
    {
        ((Analysis) sw).caseTTemplates(this);
    }

    @Override
    public void setText(@SuppressWarnings("unused") String text)
    {
        throw new RuntimeException("Cannot change TTemplates text.");
    }
}
