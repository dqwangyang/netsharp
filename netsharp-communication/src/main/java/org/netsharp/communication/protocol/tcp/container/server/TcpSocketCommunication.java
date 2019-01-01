package org.netsharp.communication.protocol.tcp.container.server;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.netsharp.communication.protocol.tcp.core.TcpSocket;

public abstract class TcpSocketCommunication extends TcpSocket{
	
	protected static Log logger = LogFactory.getLog(TcpSocketCommunication.class);
	
	public abstract void execute(Object firstRequestValue) ;
}


//数据通信中，数据在线路上的传送方式可以分为单工通信、半双工通信和全双工通信三种。
//1.单工通信
//---------------------
//指消息只能单方向传输的工作方式。例如遥控、遥测就是单工通信方式。
//单工通信信道是单向信道，发送端和接收端的身份是固定的，发送端只能发送消息，不能接收消息；接收端只能接收消息，不能发送消息，数据信号仅从一端传送到另一端，即信息流是单方向的。
//通信双方采用“按--讲”（Push To Talk,PTT）单工通信属于点对点的通信，根据收发频率的异同，单工通信可分为同频通信和异频通信。

//2.半双工通信
//---------------------
//是指数据可以沿两个方向传送，但同一时刻一个信道只允许单方向传送，因此又被称为双向交替通信。（信息在两点之间能够在两个方向上进行发送，但不能同时发送的工作方式。）
//半双工方式要求收发两端都有发送装置和接收装置。由于这种方式要频繁变换信道方向，故效率低，但可以节约传输线路。半双工方式适用于终端与终端之间的会话式通信。方向的转变由软件控制的电子开关来控制的。
//例如：无线对讲机就是一种半双工设备，在同一时间内只允许一方讲话。

//3.全双工通信
//---------------------
//是指在通信的任意时刻，线路上可以同时存在A到B和B到A的双向信号传输。在全双工方式下，通信系统的每一端都设置了发送器和接收器，因此，能控制数据同时在两个方向上传送。
//全双工方式无需进行方向的切换，因此，没有切换操作所产生的时间延迟，这对那些不能有时间延误的交互式应用（例如远程监测和控制系统）十分有利。
//比如，电话机则是一种全双工设备，其通话双方可以同时进行对话。