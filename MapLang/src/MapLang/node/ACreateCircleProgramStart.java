/* This file was generated by SableCC (http://www.sablecc.org/). */

package MapLang.node;

import MapLang.analysis.*;

@SuppressWarnings("nls")
public final class ACreateCircleProgramStart extends PProgramStart
{
    private TFloat _float_;
    private PAstRelation _astRelation_;
    private TId _id_;

    public ACreateCircleProgramStart()
    {
        // Constructor
    }

    public ACreateCircleProgramStart(
        @SuppressWarnings("hiding") TFloat _float_,
        @SuppressWarnings("hiding") PAstRelation _astRelation_,
        @SuppressWarnings("hiding") TId _id_)
    {
        // Constructor
        setFloat(_float_);

        setAstRelation(_astRelation_);

        setId(_id_);

    }

    @Override
    public Object clone()
    {
        return new ACreateCircleProgramStart(
            cloneNode(this._float_),
            cloneNode(this._astRelation_),
            cloneNode(this._id_));
    }

    @Override
    public void apply(Switch sw)
    {
        ((Analysis) sw).caseACreateCircleProgramStart(this);
    }

    public TFloat getFloat()
    {
        return this._float_;
    }

    public void setFloat(TFloat node)
    {
        if(this._float_ != null)
        {
            this._float_.parent(null);
        }

        if(node != null)
        {
            if(node.parent() != null)
            {
                node.parent().removeChild(node);
            }

            node.parent(this);
        }

        this._float_ = node;
    }

    public PAstRelation getAstRelation()
    {
        return this._astRelation_;
    }

    public void setAstRelation(PAstRelation node)
    {
        if(this._astRelation_ != null)
        {
            this._astRelation_.parent(null);
        }

        if(node != null)
        {
            if(node.parent() != null)
            {
                node.parent().removeChild(node);
            }

            node.parent(this);
        }

        this._astRelation_ = node;
    }

    public TId getId()
    {
        return this._id_;
    }

    public void setId(TId node)
    {
        if(this._id_ != null)
        {
            this._id_.parent(null);
        }

        if(node != null)
        {
            if(node.parent() != null)
            {
                node.parent().removeChild(node);
            }

            node.parent(this);
        }

        this._id_ = node;
    }

    @Override
    public String toString()
    {
        return ""
            + toString(this._float_)
            + toString(this._astRelation_)
            + toString(this._id_);
    }

    @Override
    void removeChild(@SuppressWarnings("unused") Node child)
    {
        // Remove child
        if(this._float_ == child)
        {
            this._float_ = null;
            return;
        }

        if(this._astRelation_ == child)
        {
            this._astRelation_ = null;
            return;
        }

        if(this._id_ == child)
        {
            this._id_ = null;
            return;
        }

        throw new RuntimeException("Not a child.");
    }

    @Override
    void replaceChild(@SuppressWarnings("unused") Node oldChild, @SuppressWarnings("unused") Node newChild)
    {
        // Replace child
        if(this._float_ == oldChild)
        {
            setFloat((TFloat) newChild);
            return;
        }

        if(this._astRelation_ == oldChild)
        {
            setAstRelation((PAstRelation) newChild);
            return;
        }

        if(this._id_ == oldChild)
        {
            setId((TId) newChild);
            return;
        }

        throw new RuntimeException("Not a child.");
    }
}