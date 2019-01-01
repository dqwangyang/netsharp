package org.netsharp.util;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.Inet4Address;
import java.net.InetAddress;
import java.net.NetworkInterface;
import java.net.SocketException;
import java.net.UnknownHostException;
import java.util.ArrayList;
import java.util.Enumeration;
import java.util.List;
import java.util.concurrent.locks.ReentrantLock;

public class NetUtil {
	public static void printNetInterfaces() {
		try {
			Enumeration<NetworkInterface> allNetInterfaces = NetworkInterface.getNetworkInterfaces();
			while (allNetInterfaces.hasMoreElements()) {
				NetworkInterface netInterface = (NetworkInterface) allNetInterfaces.nextElement();
				String realName = netInterface.getName();
				String dispayName = netInterface.getDisplayName();
				Enumeration<InetAddress> addresses = netInterface.getInetAddresses();
				while (addresses.hasMoreElements()) {
					InetAddress ip = (InetAddress) addresses.nextElement();
					if (ip != null && ip instanceof Inet4Address) {
						System.out.println(realName + " : " + ip.getHostAddress() + " [" + dispayName + " ]");
					}
				}
			}
		} catch (java.net.SocketException e) {
			e.printStackTrace();
		}
	}

	public static List<String> getHostIps() {
		try {
			List<String> hostIps = new ArrayList<String>();
			Enumeration<NetworkInterface> allNetInterfaces = NetworkInterface.getNetworkInterfaces();
			while (allNetInterfaces.hasMoreElements()) {
				NetworkInterface netInterface = (NetworkInterface) allNetInterfaces.nextElement();
				Enumeration<InetAddress> addresses = netInterface.getInetAddresses();
				while (addresses.hasMoreElements()) {
					InetAddress ip = (InetAddress) addresses.nextElement();
					if (ip != null && ip instanceof Inet4Address) {
						hostIps.add(ip.getHostAddress());
					}
				}
			}
			return hostIps;
		} catch (java.net.SocketException e) {
			e.printStackTrace();
			return null;
		}
	}

	private static List<String> HostIps = null;
	private static ReentrantLock HostIpsLock = new ReentrantLock();

	public static void cacheHostIps() {
		if (HostIps == null) {
			HostIpsLock.lock();
			if (HostIps == null) {
				HostIps = getHostIps();
			}
			HostIpsLock.unlock();
		}
	}

	public static boolean hasHostIp(String... ips) {
		cacheHostIps();
		for (String ip : ips) {
			if (HostIps.contains(ip)) {
				return true;
			}
		}
		return false;
	}

	/**
	 * 获取本机mac地址
	 * 
	 * @param ia
	 * @return
	 */
	public static String getLocalMac() {
		try {
			String os = getOSName();
			String mac = "";
			if (os.startsWith("windows")) {
				// 本地是windows
				mac = getWindowsMACAddress();
			} else {
				// 本地是非windows系统 一般就是unix
				mac = getUnixMACAddress();

			}

			return mac.toUpperCase();
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}

	}

	private static String getOSName() {
		return System.getProperty("os.name").toLowerCase();
	}

	private static String getUnixMACAddress() {
		String mac = null;
		BufferedReader bufferedReader = null;
		Process process = null;
		try {
			process = Runtime.getRuntime().exec("ifconfig eth0");// linux下的命令，一般取eth0作为本地主网卡
			
			// 读取mac地址
			// centos6读取hwaddr
			// centos7读取ether
			bufferedReader = new BufferedReader(new InputStreamReader(process.getInputStream()));
			String line = null;
			int index = -1;
			while ((line = bufferedReader.readLine()) != null) {
				index = line.toLowerCase().indexOf("hwaddr");// 寻找标示字符串[hwaddr]
				if (index >= 0) {// 找到了
					mac = line.substring(index + "hwaddr".length() + 1).trim();// 取出mac地址并去除2边空格
					break;
				}
				
				index = line.toLowerCase().indexOf("ether");// 寻找标示字符串[ether]
				if (index >= 0) {// 找到了
					mac = line.substring(index + "ether".length() + 1,index + "ether".length() + 18).trim();// 取出mac地址并去除2边空格
					break;
				}
			}
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			try {
				if (bufferedReader != null) {
					bufferedReader.close();
				}
			} catch (IOException e1) {
				e1.printStackTrace();
			}
			bufferedReader = null;
			process = null;
		}

		return mac.toUpperCase().replace(":", "-");
	}

	private static String getWindowsMACAddress() throws UnknownHostException, SocketException {
		InetAddress ia = InetAddress.getLocalHost();
		// 获取网卡，获取地址
		byte[] mac = NetworkInterface.getByInetAddress(ia).getHardwareAddress();
		// System.out.println("mac数组长度："+mac.length);
		StringBuffer sb = new StringBuffer("");
		for (int i = 0; i < mac.length; i++) {
			if (i != 0) {
				sb.append("-");
			}
			// 字节转换为整数
			int temp = mac[i] & 0xff;
			String str = Integer.toHexString(temp);
			// System.out.println("每8位:"+str);
			if (str.length() == 1) {
				sb.append("0" + str);
			} else {
				sb.append(str);
			}
		}
		return sb.toString().toUpperCase();
	}

	public static void main(String[] args) {
		if (args.length == 0) {
			System.out.println("Function : Check whether host has given ips");
			System.out.println("Usage : java NetUtil ip1 ip2 ...");
			System.exit(0);
		}
		for (String ip : args) {
			if (hasHostIp(ip)) {
				System.out.println(ip + " - Yes");
			} else {
				System.out.println(ip + " - No");
			}
		}
	}
}
