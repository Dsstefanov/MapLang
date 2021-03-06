/* This file was generated by SableCC (http://www.sablecc.org/). */

package MapLang.node;

import MapLang.analysis.*;

@SuppressWarnings("nls")
public final class TAt extends Token
{
    public TAt()
    {
        super.setText("at");
    }

    public TAt(int line, int pos)
    {
        super.setText("at");
        setLine(line);
        setPos(pos);
    }

    @Override
    public Object clone()
    {
      return new TAt(getLine(), getPos());
    }

    @Override
    public void apply(Switch sw)
    {
        ((Analysis) sw).caseTAt(this);
    }

    @Override
    public void setText(@SuppressWarnings("unused") String text)
    {
        throw new RuntimeException("Cannot change TAt text.");
    }
}
