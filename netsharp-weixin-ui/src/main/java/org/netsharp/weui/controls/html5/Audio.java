package org.netsharp.weui.controls.html5;

import org.netsharp.weui.annotation.HtmlAttr;
import org.netsharp.weui.annotation.HtmlNode;
import org.netsharp.weui.controls.Control;


@HtmlNode(html="audio")
public class Audio extends Control
{
    /// <summary>
    /// 值：autoplay，音频在就绪后马上播放。
    /// </summary>
    @HtmlAttr(html="autoplay")
    public String autoplay;

    /// <summary>
    /// 值：controls，向用户显示控件
    /// </summary>
    @HtmlAttr(html="controls")
    public String controls;

    /// <summary>
    /// 值：loop,每当音频结束时重新开始播放
    /// </summary>
    @HtmlAttr(html="loop")
    public String loop;

    /// <summary>
    ///  值：muted,静音
    /// </summary>
    @HtmlAttr(html="muted")
    public String muted;

    /// <summary>
    /// 值：preload，音频在页面加载时进行加载，并预备播放。如果使用 "autoplay"，则忽略该属性。
    /// </summary>
    @HtmlAttr(html="preload")
    public String preload;

    /// <summary>
    /// 连接地址
    /// </summary>
    @HtmlAttr(html="src")
    public String src;
}
