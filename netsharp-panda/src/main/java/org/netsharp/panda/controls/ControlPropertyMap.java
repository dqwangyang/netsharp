package org.netsharp.panda.controls;

import java.util.ArrayList;

import org.netsharp.panda.annotation.DataOptionAnnotation;
import org.netsharp.panda.annotation.EditorOptionAnnotation;
import org.netsharp.panda.annotation.HtmlAttrAnnotation;

public class ControlPropertyMap
{
    public ArrayList<HtmlAttrAnnotation> HtmlProperties = new ArrayList<HtmlAttrAnnotation>();
    public ArrayList<DataOptionAnnotation> DataOptionProperties = new ArrayList<DataOptionAnnotation>();
    public ArrayList<EditorOptionAnnotation> EditorOptionProperties = new ArrayList<EditorOptionAnnotation>(); 
}