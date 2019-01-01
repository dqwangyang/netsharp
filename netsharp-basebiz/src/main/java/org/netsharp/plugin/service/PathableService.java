package org.netsharp.plugin.service;

import org.netsharp.communication.Service;
import org.netsharp.plugin.base.IPathableService;
import org.netsharp.plugin.entity.Pathable;
import org.netsharp.service.PersistableService;

@Service
public class PathableService extends PersistableService<Pathable> implements IPathableService {

}
