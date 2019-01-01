package org.netsharp.persistence.oql.languangeEngine;


public interface ILanguange
{
    /// <summary>
    /// 运行
    /// </summary>
    void run(EngineContext context);

    EngineContext getContext();
}