/* This file was generated by SableCC (http://www.sablecc.org/). */

package MapLang.node;

import MapLang.analysis.*;

@SuppressWarnings("nls")
public final class AERelationExplicitAstNullableRelation extends PAstNullableRelation
{
    private PAstPreciseDir _astPreciseDir_;
    private PAstRelationExplicit _astRelationExplicit_;

    public AERelationExplicitAstNullableRelation()
    {
        // Constructor
    }

    public AERelationExplicitAstNullableRelation(
        @SuppressWarnings("hiding") PAstPreciseDir _astPreciseDir_,
        @SuppressWarnings("hiding") PAstRelationExplicit _astRelationExplicit_)
    {
        // Constructor
        setAstPreciseDir(_astPreciseDir_);

        setAstRelationExplicit(_astRelationExplicit_);

    }

    @Override
    public Object clone()
    {
        return new AERelationExplicitAstNullableRelation(
            cloneNode(this._astPreciseDir_),
            cloneNode(this._astRelationExplicit_));
    }

    @Override
    public void apply(Switch sw)
    {
        ((Analysis) sw).caseAERelationExplicitAstNullableRelation(this);
    }

    public PAstPreciseDir getAstPreciseDir()
    {
        return this._astPreciseDir_;
    }

    public void setAstPreciseDir(PAstPreciseDir node)
    {
        if(this._astPreciseDir_ != null)
        {
            this._astPreciseDir_.parent(null);
        }

        if(node != null)
        {
            if(node.parent() != null)
            {
                node.parent().removeChild(node);
            }

            node.parent(this);
        }

        this._astPreciseDir_ = node;
    }

    public PAstRelationExplicit getAstRelationExplicit()
    {
        return this._astRelationExplicit_;
    }

    public void setAstRelationExplicit(PAstRelationExplicit node)
    {
        if(this._astRelationExplicit_ != null)
        {
            this._astRelationExplicit_.parent(null);
        }

        if(node != null)
        {
            if(node.parent() != null)
            {
                node.parent().removeChild(node);
            }

            node.parent(this);
        }

        this._astRelationExplicit_ = node;
    }

    @Override
    public String toString()
    {
        return ""
            + toString(this._astPreciseDir_)
            + toString(this._astRelationExplicit_);
    }

    @Override
    void removeChild(@SuppressWarnings("unused") Node child)
    {
        // Remove child
        if(this._astPreciseDir_ == child)
        {
            this._astPreciseDir_ = null;
            return;
        }

        if(this._astRelationExplicit_ == child)
        {
            this._astRelationExplicit_ = null;
            return;
        }

        throw new RuntimeException("Not a child.");
    }

    @Override
    void replaceChild(@SuppressWarnings("unused") Node oldChild, @SuppressWarnings("unused") Node newChild)
    {
        // Replace child
        if(this._astPreciseDir_ == oldChild)
        {
            setAstPreciseDir((PAstPreciseDir) newChild);
            return;
        }

        if(this._astRelationExplicit_ == oldChild)
        {
            setAstRelationExplicit((PAstRelationExplicit) newChild);
            return;
        }

        throw new RuntimeException("Not a child.");
    }
}
