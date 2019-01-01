package org.netsharp.util;

public class CoupleManage <T1, T2>{
	public CoupleManage() {
		//
	}

	public CoupleManage(T1 first, T2 second) {
		this.first = first;
		this.second = second;
	}

	public static <T1, T2> CoupleManage<T1, T2> newOne() {
		return new CoupleManage<T1, T2>();
	}

	public static <T1, T2> CoupleManage<T1, T2> newOne(T1 first, T2 second) {
		return new CoupleManage<T1, T2>(first, second);
	}

	protected T1 first;
	protected T2 second;

	public T1 getFirst() {
		return first;
	}

	public void setFirst(T1 first) {
		this.first = first;
	}

	public T2 getSecond() {
		return second;
	}

	public void setSecond(T2 second) {
		this.second = second;
	}

	public String toString() {
		return this.first + "," + this.second;
	}
}
