//package org.netsharp.core.id;
//
//import java.io.BufferedReader;
//import java.io.IOException;
//import java.io.InputStreamReader;
//import java.net.InetAddress;
//import java.net.NetworkInterface;
//import java.net.SocketException;
//import java.net.UnknownHostException;
//
//import org.netsharp.util.StringManager;
//
//public class MacAddress{
//	
//	public static String getMacAddress() {
//		try {
//			String os = System.getProperty("os.name").toLowerCase();
//			String mac = "";
//			if (os.startsWith("windows")) {
//				// 本地是windows
//				mac = getWindowsMacAddress();
//			} else {
//				// 本地是非windows系统 一般就是unix
//				mac = getLinuxMacAddress();
//			}
//
//			return mac.toUpperCase();
//		} catch (Exception e) {
//			e.printStackTrace();
//			return null;
//		}
//
//	}
//
//	private static String getLinuxMacAddress() {
//
//		String mac = MacAddress.getLinuxMacAddress("ifconfig eth0");
//		if(StringManager.isNullOrEmpty(mac)) {
//			mac= MacAddress.getLinuxMacAddress("ifconfig");
//		}
//		
//		return mac;
//	}
//
//	private static String getLinuxMacAddress(String cmd) {
//		
//		// centos6读取hwaddr
//		// centos7读取ether
//		
//		String mac = null;
//		BufferedReader bufferedReader = null;
//		Process process = null;
//		try {
//			process = Runtime.getRuntime().exec(cmd);
//			
//			bufferedReader = new BufferedReader(new InputStreamReader(process.getInputStream()));
//			String line = null;
//			int index = -1;
//			while ((line = bufferedReader.readLine()) != null) {
//				index = line.toLowerCase().indexOf("hwaddr");
//				if (index >= 0) {// 找到了
//					mac = line.substring(index + "hwaddr".length() + 1).trim();// 取出mac地址并去除2边空格
//					break;
//				}
//				
//				index = line.toLowerCase().indexOf("ether");// 寻找标示字符串[ether]
//				if (index >= 0) {// 找到了
//					mac = line.substring(index + "ether".length() + 1,index + "ether".length() + 18).trim();// 取出mac地址并去除2边空格
//					break;
//				}
//			}
//		} catch (IOException e) {
//			e.printStackTrace();
//		} finally {
//			try {
//				if (bufferedReader != null) {
//					bufferedReader.close();
//				}
//			} catch (IOException e1) {
//				e1.printStackTrace();
//			}
//			bufferedReader = null;
//			process = null;
//		}
//		
//		if(mac==null) {
//			return null;
//		}
//
//		return mac.toUpperCase().replace(":", "-");
//	}
//
//	private static String getWindowsMacAddress() throws UnknownHostException, SocketException {
//		InetAddress ia = InetAddress.getLocalHost();
//		// 获取网卡，获取地址
//		byte[] mac = NetworkInterface.getByInetAddress(ia).getHardwareAddress();
//		// System.out.println("mac数组长度："+mac.length);
//		StringBuffer sb = new StringBuffer("");
//		for (int i = 0; i < mac.length; i++) {
//			if (i != 0) {
//				sb.append("-");
//			}
//			// 字节转换为整数
//			int temp = mac[i] & 0xff;
//			String str = Integer.toHexString(temp);
//			// System.out.println("每8位:"+str);
//			if (str.length() == 1) {
//				sb.append("0" + str);
//			} else {
//				sb.append(str);
//			}
//		}
//		return sb.toString().toUpperCase();
//	}
//}