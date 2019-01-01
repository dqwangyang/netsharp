package org.netsharp.util.sqlbuilder;

public interface IPlaceHolderMaker {
	String makePlaceHolder(String orginalStr);

	public boolean isPlaceHolder(String valueStr);
}
