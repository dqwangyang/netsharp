package org.netsharp.scrum.base;

import org.netsharp.scrum.entity.Iteration;
import org.netsharp.base.IPersistableService;

public interface IIterationService extends IPersistableService<Iteration> {

	Iteration getCurrentIteration();
}
