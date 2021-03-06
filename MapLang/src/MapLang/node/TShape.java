/* This file was generated by SableCC (http://www.sablecc.org/). */

package MapLang.node;

import MapLang.analysis.*;

@SuppressWarnings("nls")
public final class TShape extends Token
{
    public TShape()
    {
        super.setText("shape");
    }

    public TShape(int line, int pos)
    {
        super.setText("shape");
        setLine(line);
        setPos(pos);
    }

    @Override
    public Object clone()
    {
      return new TShape(getLine(), getPos());
    }

    @Override
    public void apply(Switch sw)
    {
        ((Analysis) sw).caseTShape(this);
    }

    @Override
    public void setText(@SuppressWarnings("unused") String text)
    {
        throw new RuntimeException("Cannot change TShape text.");
    }
}
