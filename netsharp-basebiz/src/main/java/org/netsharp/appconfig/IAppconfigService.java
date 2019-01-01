package org.netsharp.appconfig;

import org.netsharp.base.IPersistableService;

public interface IAppconfigService extends IPersistableService<Appconfig> {
    Appconfig byCode(String code); 
}