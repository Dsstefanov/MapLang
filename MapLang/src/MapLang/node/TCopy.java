/* This file was generated by SableCC (http://www.sablecc.org/). */

package MapLang.node;

import MapLang.analysis.*;

@SuppressWarnings("nls")
public final class TCopy extends Token
{
    public TCopy()
    {
        super.setText("copy");
    }

    public TCopy(int line, int pos)
    {
        super.setText("copy");
        setLine(line);
        setPos(pos);
    }

    @Override
    public Object clone()
    {
      return new TCopy(getLine(), getPos());
    }

    @Override
    public void apply(Switch sw)
    {
        ((Analysis) sw).caseTCopy(this);
    }

    @Override
    public void setText(@SuppressWarnings("unused") String text)
    {
        throw new RuntimeException("Cannot change TCopy text.");
    }
}