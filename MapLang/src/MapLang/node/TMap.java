/* This file was generated by SableCC (http://www.sablecc.org/). */

package MapLang.node;

import MapLang.analysis.*;

@SuppressWarnings("nls")
public final class TMap extends Token
{
    public TMap()
    {
        super.setText("map");
    }

    public TMap(int line, int pos)
    {
        super.setText("map");
        setLine(line);
        setPos(pos);
    }

    @Override
    public Object clone()
    {
      return new TMap(getLine(), getPos());
    }

    @Override
    public void apply(Switch sw)
    {
        ((Analysis) sw).caseTMap(this);
    }

    @Override
    public void setText(@SuppressWarnings("unused") String text)
    {
        throw new RuntimeException("Cannot change TMap text.");
    }
}
