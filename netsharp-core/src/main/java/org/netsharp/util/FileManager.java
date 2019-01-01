package org.netsharp.util;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

public class FileManager {

	public static String read(String fileName) {

		File file = new File(fileName);
		String content = read(file);
		return content;
	}

	public static String read(InputStream stream) {
		StringBuilder builder = new StringBuilder();
		try {

			InputStreamReader read = new InputStreamReader(stream, "UTF-8");
			BufferedReader reader = new BufferedReader(read);

			String lineTxt = null;

			while ((lineTxt = reader.readLine()) != null) {

				builder.append(lineTxt).append(
						System.getProperty("line.separator"));

			}

			read.close();
			stream.close();
			reader.close();

		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}

		return builder.toString();
	}

	public static TextReader reader(String fileName) {
		BufferedReader reader = null;
		try {

			FileInputStream inStream = new FileInputStream(fileName);
			InputStreamReader read = new InputStreamReader(inStream, "UTF-8");
			reader = new BufferedReader(read);

			TextReader txtReader = new TextReader();
			{
				txtReader.FileInputStream = inStream;
				txtReader.InputStreamReader = read;
				txtReader.BufferedReader = reader;
			}

			return txtReader;

		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			try {
				reader.close();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}

		return null;
	}

	public static String read(File file) {

		StringBuilder builder = new StringBuilder();

		try {

			FileInputStream inStream = new FileInputStream(file);
			InputStreamReader read = new InputStreamReader(inStream, "UTF-8");
			BufferedReader reader = new BufferedReader(read);

			String lineTxt = null;

			while ((lineTxt = reader.readLine()) != null) {

				builder.append(lineTxt).append(
						System.getProperty("line.separator"));

			}

			read.close();
			inStream.close();
			reader.close();

		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}

		return builder.toString();
	}

	public static void write(String fileName, String content) {
		File file = new File(fileName);
		write(file, content);
	}

	public static void write(File file, String content) {
		try {
			FileWriter writer = new FileWriter(file, false);
			writer.write(content);

			writer.flush();
			writer.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

}