package org.netsharp.persistence;

import org.netsharp.core.Oql;
import org.netsharp.persistence.query.TSet;

public interface IQuery {
    TSet query(Oql oql);
}
