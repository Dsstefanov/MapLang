/* This file was generated by SableCC (http://www.sablecc.org/). */

package MapLang.node;

import MapLang.analysis.*;

@SuppressWarnings("nls")
public final class TRight extends Token
{
    public TRight()
    {
        super.setText("right");
    }

    public TRight(int line, int pos)
    {
        super.setText("right");
        setLine(line);
        setPos(pos);
    }

    @Override
    public Object clone()
    {
      return new TRight(getLine(), getPos());
    }

    @Override
    public void apply(Switch sw)
    {
        ((Analysis) sw).caseTRight(this);
    }

    @Override
    public void setText(@SuppressWarnings("unused") String text)
    {
        throw new RuntimeException("Cannot change TRight text.");
    }
}
