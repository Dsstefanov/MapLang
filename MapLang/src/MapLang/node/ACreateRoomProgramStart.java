/* This file was generated by SableCC (http://www.sablecc.org/). */

package MapLang.node;

import MapLang.analysis.*;

@SuppressWarnings("nls")
public final class ACreateRoomProgramStart extends PProgramStart
{
    private TId _left_;
    private TInt _int_;
    private TId _right_;

    public ACreateRoomProgramStart()
    {
        // Constructor
    }

    public ACreateRoomProgramStart(
        @SuppressWarnings("hiding") TId _left_,
        @SuppressWarnings("hiding") TInt _int_,
        @SuppressWarnings("hiding") TId _right_)
    {
        // Constructor
        setLeft(_left_);

        setInt(_int_);

        setRight(_right_);

    }

    @Override
    public Object clone()
    {
        return new ACreateRoomProgramStart(
            cloneNode(this._left_),
            cloneNode(this._int_),
            cloneNode(this._right_));
    }

    @Override
    public void apply(Switch sw)
    {
        ((Analysis) sw).caseACreateRoomProgramStart(this);
    }

    public TId getLeft()
    {
        return this._left_;
    }

    public void setLeft(TId node)
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

    public TInt getInt()
    {
        return this._int_;
    }

    public void setInt(TInt node)
    {
        if(this._int_ != null)
        {
            this._int_.parent(null);
        }

        if(node != null)
        {
            if(node.parent() != null)
            {
                node.parent().removeChild(node);
            }

            node.parent(this);
        }

        this._int_ = node;
    }

    public TId getRight()
    {
        return this._right_;
    }

    public void setRight(TId node)
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

    @Override
    public String toString()
    {
        return ""
            + toString(this._left_)
            + toString(this._int_)
            + toString(this._right_);
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

        if(this._int_ == child)
        {
            this._int_ = null;
            return;
        }

        if(this._right_ == child)
        {
            this._right_ = null;
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
            setLeft((TId) newChild);
            return;
        }

        if(this._int_ == oldChild)
        {
            setInt((TInt) newChild);
            return;
        }

        if(this._right_ == oldChild)
        {
            setRight((TId) newChild);
            return;
        }

        throw new RuntimeException("Not a child.");
    }
}