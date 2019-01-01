package org.netsharp.panda.controls.query;

import org.netsharp.core.Mtable;
import org.netsharp.panda.controls.Control;
import org.netsharp.panda.entity.PQueryItem;

public interface IPropertyQueryControl {
	
	 Control create(PQueryItem queryItem, Mtable meta);
}
