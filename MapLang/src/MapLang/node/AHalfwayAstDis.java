/* This file was generated by SableCC (http://www.sablecc.org/). */

package MapLang.node;

import MapLang.analysis.*;

@SuppressWarnings("nls")
public final class AHalfwayAstDis extends PAstDis
{
    private THalfway _halfway_;

    public AHalfwayAstDis()
    {
        // Constructor
    }

    public AHalfwayAstDis(
        @SuppressWarnings("hiding") THalfway _halfway_)
    {
        // Constructor
        setHalfway(_halfway_);

    }

    @Override
    public Object clone()
    {
        return new AHalfwayAstDis(
            cloneNode(this._halfway_));
    }

    @Override
    public void apply(Switch sw)
    {
        ((Analysis) sw).caseAHalfwayAstDis(this);
    }

    public THalfway getHalfway()
    {
        return this._halfway_;
    }

    public void setHalfway(THalfway node)
    {
        if(this._halfway_ != null)
        {
            this._halfway_.parent(null);
        }

        if(node != null)
        {
            if(node.parent() != null)
            {
                node.parent().removeChild(node);
            }

            node.parent(this);
        }

        this._halfway_ = node;
    }

    @Override
    public String toString()
    {
        return ""
            + toString(this._halfway_);
    }

    @Override
    void removeChild(@SuppressWarnings("unused") Node child)
    {
        // Remove child
        if(this._halfway_ == child)
        {
            this._halfway_ = null;
            return;
        }

        throw new RuntimeException("Not a child.");
    }

    @Override
    void replaceChild(@SuppressWarnings("unused") Node oldChild, @SuppressWarnings("unused") Node newChild)
    {
        // Replace child
        if(this._halfway_ == oldChild)
        {
            setHalfway((THalfway) newChild);
            return;
        }

        throw new RuntimeException("Not a child.");
    }
}
