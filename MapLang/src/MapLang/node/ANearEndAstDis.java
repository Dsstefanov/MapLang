/* This file was generated by SableCC (http://www.sablecc.org/). */

package MapLang.node;

import MapLang.analysis.*;

@SuppressWarnings("nls")
public final class ANearEndAstDis extends PAstDis
{
    private TNearend _nearend_;

    public ANearEndAstDis()
    {
        // Constructor
    }

    public ANearEndAstDis(
        @SuppressWarnings("hiding") TNearend _nearend_)
    {
        // Constructor
        setNearend(_nearend_);

    }

    @Override
    public Object clone()
    {
        return new ANearEndAstDis(
            cloneNode(this._nearend_));
    }

    @Override
    public void apply(Switch sw)
    {
        ((Analysis) sw).caseANearEndAstDis(this);
    }

    public TNearend getNearend()
    {
        return this._nearend_;
    }

    public void setNearend(TNearend node)
    {
        if(this._nearend_ != null)
        {
            this._nearend_.parent(null);
        }

        if(node != null)
        {
            if(node.parent() != null)
            {
                node.parent().removeChild(node);
            }

            node.parent(this);
        }

        this._nearend_ = node;
    }

    @Override
    public String toString()
    {
        return ""
            + toString(this._nearend_);
    }

    @Override
    void removeChild(@SuppressWarnings("unused") Node child)
    {
        // Remove child
        if(this._nearend_ == child)
        {
            this._nearend_ = null;
            return;
        }

        throw new RuntimeException("Not a child.");
    }

    @Override
    void replaceChild(@SuppressWarnings("unused") Node oldChild, @SuppressWarnings("unused") Node newChild)
    {
        // Replace child
        if(this._nearend_ == oldChild)
        {
            setNearend((TNearend) newChild);
            return;
        }

        throw new RuntimeException("Not a child.");
    }
}
