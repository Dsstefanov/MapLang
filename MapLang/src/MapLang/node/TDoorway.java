/* This file was generated by SableCC (http://www.sablecc.org/). */

package MapLang.node;

import MapLang.analysis.*;

@SuppressWarnings("nls")
public final class TDoorway extends Token
{
    public TDoorway()
    {
        super.setText("doorway");
    }

    public TDoorway(int line, int pos)
    {
        super.setText("doorway");
        setLine(line);
        setPos(pos);
    }

    @Override
    public Object clone()
    {
      return new TDoorway(getLine(), getPos());
    }

    @Override
    public void apply(Switch sw)
    {
        ((Analysis) sw).caseTDoorway(this);
    }

    @Override
    public void setText(@SuppressWarnings("unused") String text)
    {
        throw new RuntimeException("Cannot change TDoorway text.");
    }
}
