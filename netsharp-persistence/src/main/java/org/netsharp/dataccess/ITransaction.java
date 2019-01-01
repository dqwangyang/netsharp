package org.netsharp.dataccess;

public interface ITransaction {
    void begin();
    void commit();
    void rollbak();
}
