package org.netsharp.panda.core.pad;

import org.netsharp.panda.controls.Control;

public interface IPad {
	Control create();

	boolean condition();
}
