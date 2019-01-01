package org.netsharp.util;

import java.util.Random;


public class RandomManage {
	
	private Random random=new Random();
	
	public int nextInt(int start,int end) {
		
		return start + random.nextInt(end-start);
	}
	
	public static String random(int bit) {
		
		Random random=new Random();
		StringBuilder sb = new StringBuilder(bit);

		for (int i = 0; i < bit; i++) {
			int nextInt = random.nextInt(10);
			sb.append(nextInt);
		}

		return sb.toString();
	}
}
