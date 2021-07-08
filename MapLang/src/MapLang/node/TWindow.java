/* This file was generated by SableCC (http://www.sablecc.org/). */

package MapLang.node;

import MapLang.analysis.*;

@SuppressWarnings("nls")
public final class TWindow extends Token
{
    public TWindow()
    {
        super.setText("window");
    }

    public TWindow(int line, int pos)
    {
        super.setText("window");
        setLine(line);
        setPos(pos);
    }

    @Override
    public Object clone()
    {
      return new TWindow(getLine(), getPos());
    }

    @Override
    public void apply(Switch sw)
    {
        ((Analysis) sw).caseTWindow(this);
    }

    @Override
    public void setText(@SuppressWarnings("unused") String text)
    {
        throw new RuntimeException("Cannot change TWindow text.");
    }
}