package org.netsharp.panda.controls.input;

public class ReferencePopupbox extends Input
{
    @Override
    public void initialize()
    {
        super.initialize();

        this.className = "easyui-searchbox";

        this.dataOptions.add( "prompt:'Please Input Value',searcher:doSearch");

        //function doSearch(value){
        //    alert('You input: ' + value);
        //}

    }
}