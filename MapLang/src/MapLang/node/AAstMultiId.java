/* This file was generated by SableCC (http://www.sablecc.org/). */

package MapLang.node;

import MapLang.analysis.*;

@SuppressWarnings("nls")
public final class AAstMultiId extends PAstMultiId
{
    private TId _id_;
    private PAstMultiId _astMultiId_;

    public AAstMultiId()
    {
        // Constructor
    }

    public AAstMultiId(
        @SuppressWarnings("hiding") TId _id_,
        @SuppressWarnings("hiding") PAstMultiId _astMultiId_)
    {
        // Constructor
        setId(_id_);

        setAstMultiId(_astMultiId_);

    }

    @Override
    public Object clone()
    {
        return new AAstMultiId(
            cloneNode(this._id_),
            cloneNode(this._astMultiId_));
    }

    @Override
    public void apply(Switch sw)
    {
        ((Analysis) sw).caseAAstMultiId(this);
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

    public PAstMultiId getAstMultiId()
    {
        return this._astMultiId_;
    }

    public void setAstMultiId(PAstMultiId node)
    {
        if(this._astMultiId_ != null)
        {
            this._astMultiId_.parent(null);
        }

        if(node != null)
        {
            if(node.parent() != null)
            {
                node.parent().removeChild(node);
            }

            node.parent(this);
        }

        this._astMultiId_ = node;
    }

    @Override
    public String toString()
    {
        return ""
            + toString(this._id_)
            + toString(this._astMultiId_);
    }

    @Override
    void removeChild(@SuppressWarnings("unused") Node child)
    {
        // Remove child
        if(this._id_ == child)
        {
            this._id_ = null;
            return;
        }

        if(this._astMultiId_ == child)
        {
            this._astMultiId_ = null;
            return;
        }

        throw new RuntimeException("Not a child.");
    }

    @Override
    void replaceChild(@SuppressWarnings("unused") Node oldChild, @SuppressWarnings("unused") Node newChild)
    {
        // Replace child
        if(this._id_ == oldChild)
        {
            setId((TId) newChild);
            return;
        }

        if(this._astMultiId_ == oldChild)
        {
            setAstMultiId((PAstMultiId) newChild);
            return;
        }

        throw new RuntimeException("Not a child.");
    }
}
