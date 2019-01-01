package org.netsharp.organization.base;

import org.netsharp.base.IPersistableService;
import org.netsharp.organization.entity.Position;

public interface IPositionService extends IPersistableService<Position> {

	Position byCode(String code);
}
