/* This file was generated by SableCC (http://www.sablecc.org/). */

package MapLang.node;

import MapLang.analysis.*;

@SuppressWarnings("nls")
public final class ACreateLineProgramStart extends PProgramStart
{
    private PAstCoordinate _left_;
    private PAstCoordinate _right_;
    private TId _id_;

    public ACreateLineProgramStart()
    {
        // Constructor
    }

    public ACreateLineProgramStart(
        @SuppressWarnings("hiding") PAstCoordinate _left_,
        @SuppressWarnings("hiding") PAstCoordinate _right_,
        @SuppressWarnings("hiding") TId _id_)
    {
        // Constructor
        setLeft(_left_);

        setRight(_right_);

        setId(_id_);

    }

    @Override
    public Object clone()
    {
        return new ACreateLineProgramStart(
            cloneNode(this._left_),
            cloneNode(this._right_),
            cloneNode(this._id_));
    }

    @Override
    public void apply(Switch sw)
    {
        ((Analysis) sw).caseACreateLineProgramStart(this);
    }

    public PAstCoordinate getLeft()
    {
        return this._left_;
    }

    public void setLeft(PAstCoordinate node)
    {
        if(this._left_ != null)
        {
            this._left_.parent(null);
        }

        if(node != null)
        {
            if(node.parent() != null)
            {
                node.parent().removeChild(node);
            }

            node.parent(this);
        }

        this._left_ = node;
    }

    public PAstCoordinate getRight()
    {
        return this._right_;
    }

    public void setRight(PAstCoordinate node)
    {
        if(this._right_ != null)
        {
            this._right_.parent(null);
        }

        if(node != null)
        {
            if(node.parent() != null)
            {
                node.parent().removeChild(node);
            }

            node.parent(this);
        }

        this._right_ = node;
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
            + toString(this._left_)
            + toString(this._right_)
            + toString(this._id_);
    }

    @Override
    void removeChild(@SuppressWarnings("unused") Node child)
    {
        // Remove child
        if(this._left_ == child)
        {
            this._left_ = null;
            return;
        }

        if(this._right_ == child)
        {
            this._right_ = null;
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
        if(this._left_ == oldChild)
        {
            setLeft((PAstCoordinate) newChild);
            return;
        }

        if(this._right_ == oldChild)
        {
            setRight((PAstCoordinate) newChild);
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
