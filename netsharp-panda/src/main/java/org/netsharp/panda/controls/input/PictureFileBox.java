package org.netsharp.panda.controls.input;

public class PictureFileBox extends FileBox{

    @Override
    public void initialize()
    {
        super.initialize();
    	this.setClassName("easyui-filebox nsInput");
        this.controlType="PictureFileBox";
        this.accept = "image/jp2,image/jpeg,image/png,image/vnd.dwg,image/vnd.dxf,image/gif,image/tiff";
    }
}
