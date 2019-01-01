package org.netsharp.persistence.oql.parser.set;

import java.util.ArrayList;
import java.util.Iterator;

import org.netsharp.core.Mtable;



public class OqlStructs implements Iterable<OqlStruct>
{
    public Mtable Mtable ;
    public ArrayList<OqlStruct> innerList = new ArrayList<OqlStruct>();

    public OqlStruct get(int index)
    {
    	 return innerList.get(index);
    }

    public void add(OqlStruct os)
    {
        this.innerList.add(os);
    }

    int maxLevel = -1;
    public int getMaxLevel ()
    {
    	for (OqlStruct os : innerList)
        {
            if (maxLevel < os.Level)
            {
                maxLevel = os.Level;
            }

            this.Mtable = os.Mtable;
        }

        return maxLevel;
    }

    @Override
    public  String toString()
    {
        return this.getMaxLevel() + " : " + this.Mtable.getEntityId() +"  Count:"+innerList.size();
    }

	@Override
	public Iterator<OqlStruct> iterator() {
		return innerList.iterator();
	}
}