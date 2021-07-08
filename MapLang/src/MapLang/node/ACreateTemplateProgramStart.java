/* This file was generated by SableCC (http://www.sablecc.org/). */

package MapLang.node;

import MapLang.analysis.*;

@SuppressWarnings("nls")
public final class ACreateTemplateProgramStart extends PProgramStart
{
    private PCreateTemplate _createTemplate_;

    public ACreateTemplateProgramStart()
    {
        // Constructor
    }

    public ACreateTemplateProgramStart(
        @SuppressWarnings("hiding") PCreateTemplate _createTemplate_)
    {
        // Constructor
        setCreateTemplate(_createTemplate_);

    }

    @Override
    public Object clone()
    {
        return new ACreateTemplateProgramStart(
            cloneNode(this._createTemplate_));
    }

    @Override
    public void apply(Switch sw)
    {
        ((Analysis) sw).caseACreateTemplateProgramStart(this);
    }

    public PCreateTemplate getCreateTemplate()
    {
        return this._createTemplate_;
    }

    public void setCreateTemplate(PCreateTemplate node)
    {
        if(this._createTemplate_ != null)
        {
            this._createTemplate_.parent(null);
        }

        if(node != null)
        {
            if(node.parent() != null)
            {
                node.parent().removeChild(node);
            }

            node.parent(this);
        }

        this._createTemplate_ = node;
    }

    @Override
    public String toString()
    {
        return ""
            + toString(this._createTemplate_);
    }

    @Override
    void removeChild(@SuppressWarnings("unused") Node child)
    {
        // Remove child
        if(this._createTemplate_ == child)
        {
            this._createTemplate_ = null;
            return;
        }

        throw new RuntimeException("Not a child.");
    }

    @Override
    void replaceChild(@SuppressWarnings("unused") Node oldChild, @SuppressWarnings("unused") Node newChild)
    {
        // Replace child
        if(this._createTemplate_ == oldChild)
        {
            setCreateTemplate((PCreateTemplate) newChild);
            return;
        }

        throw new RuntimeException("Not a child.");
    }
}
