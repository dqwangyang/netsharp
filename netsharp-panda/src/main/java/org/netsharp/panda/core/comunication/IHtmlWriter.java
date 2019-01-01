package org.netsharp.panda.core.comunication;

public interface IHtmlWriter
{
    void write(String html);
    
    String getWriteHtml();
    
    void clearWriteHtml();
}