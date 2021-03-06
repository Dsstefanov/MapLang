/* This file was generated by SableCC (http://www.sablecc.org/). */

package MapLang.node;

import MapLang.analysis.*;

@SuppressWarnings("nls")
public final class THalfway extends Token
{
    public THalfway()
    {
        super.setText("halfway");
    }

    public THalfway(int line, int pos)
    {
        super.setText("halfway");
        setLine(line);
        setPos(pos);
    }

    @Override
    public Object clone()
    {
      return new THalfway(getLine(), getPos());
    }

    @Override
    public void apply(Switch sw)
    {
        ((Analysis) sw).caseTHalfway(this);
    }

    @Override
    public void setText(@SuppressWarnings("unused") String text)
    {
        throw new RuntimeException("Cannot change THalfway text.");
    }
}
