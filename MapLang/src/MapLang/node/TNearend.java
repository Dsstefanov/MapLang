/* This file was generated by SableCC (http://www.sablecc.org/). */

package MapLang.node;

import MapLang.analysis.*;

@SuppressWarnings("nls")
public final class TNearend extends Token
{
    public TNearend()
    {
        super.setText("nearend");
    }

    public TNearend(int line, int pos)
    {
        super.setText("nearend");
        setLine(line);
        setPos(pos);
    }

    @Override
    public Object clone()
    {
      return new TNearend(getLine(), getPos());
    }

    @Override
    public void apply(Switch sw)
    {
        ((Analysis) sw).caseTNearend(this);
    }

    @Override
    public void setText(@SuppressWarnings("unused") String text)
    {
        throw new RuntimeException("Cannot change TNearend text.");
    }
}
