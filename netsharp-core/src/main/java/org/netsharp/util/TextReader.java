package org.netsharp.util;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;

public class TextReader
{
	public FileInputStream FileInputStream;
	public InputStreamReader InputStreamReader;
	public BufferedReader BufferedReader;
	
	public void close(){
		try {
			this.FileInputStream.close();
			this.InputStreamReader.close();
			this.BufferedReader.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}