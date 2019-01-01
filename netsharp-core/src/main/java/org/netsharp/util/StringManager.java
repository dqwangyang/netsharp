package org.netsharp.util;

import org.apache.commons.lang.StringEscapeUtils;

import java.util.ArrayList;
import java.util.List;

public class StringManager {

	private static String Empty = "";
	public static String NewLine = System.getProperty("line.separator");

    public static String trimToEmpty(Object obj) {
        if (null == obj) {
            return "";
        }
        return trimToEmpty(obj.toString());
    }
    
	public static Boolean isNullOrEmpty(String value) {

		if (value == null) {
			return true;
		} else if (value.equals(Empty)) {
			return true;
		} else if (value.trim().equals(Empty)) {
			return true;
		} else {
			return false;
		}
	}

	public static boolean equals(String s1, String s2) {
		if (s1 == null) {
			return s2 == null;
		} else {
			return s1.equals(s2);
		}
	}

	public static boolean equals(String s1, String s2, boolean isIgnoreCase) {

		if (s1 == null && s2 == null) {
			return true;
		} else if (s1 != null && s2 != null) {
			if (isIgnoreCase) {
				return s1.equalsIgnoreCase(s2);
			} else {
				return s1.equals(s2);
			}
		} else {
			return false;
		}

	}
	
	public static String join(String[] ss) {
		return join(StringManager.NewLine,ss);
	}

	public static String join(List<?> ss) {
		return join(StringManager.NewLine,ss);
	}

	public static String join(ArrayList<String> ss) {
		return join(StringManager.NewLine,ss);
	}

	public static String join(String seprator, String[] ss) {

		if (ss == null || ss.length == 0) {
			return null;
		}

		StringBuilder builder = new StringBuilder();

		for (int i = 0; i < ss.length - 1; i++) {
			builder.append(ss[i]).append(seprator);
		}

		builder.append(ss[ss.length - 1]);

		return builder.toString();
	}

	public static String join(String seprator, List<?> ss) {

		if (ss == null || ss.size() == 0) {
			return null;
		}
		StringBuilder builder = new StringBuilder();

		for (int i = 0; i < ss.size() - 1; i++) {
			builder.append(ss.get(i)).append(seprator);
		}

		builder.append(ss.get(ss.size() - 1));

		return builder.toString();
	}

	public static String join(String seprator, ArrayList<String> ss) {

		if (ss == null || ss.size() == 0) {
			return null;
		}
		StringBuilder builder = new StringBuilder();

		for (int i = 0; i < ss.size() - 1; i++) {
			builder.append(ss.get(i)).append(seprator);
		}

		builder.append(ss.get(ss.size() - 1));

		return builder.toString();
	}

	public static int indexOf(String input, String patten, boolean isIgnoreCase) {

		if (isIgnoreCase) {
			input = input.toLowerCase();
			patten = patten.toLowerCase();
		}

		return input.indexOf(patten);
	}

	public static int indexOf(String input, String patten, int index, boolean isIgnoreCase) {
		if (isIgnoreCase) {
			input = input.toLowerCase();
			patten = patten.toLowerCase();
		}

		return input.indexOf(patten, index);
	}

	public static boolean startWith(String input, String patten, boolean isIgnoreCase) {
		if (!isIgnoreCase) {
			return input.startsWith(patten);
		}

		if (input.length() < patten.length()) {
			return false;
		}

		String t = input.substring(0, patten.length() - 1);

		return t.equalsIgnoreCase(patten);
	}

	public static String trimStart(String input, char patten) {

		for (int i = 0; i < input.length(); i++) {
			if (input.charAt(i) != patten) {
				if (i == 0) {
					return input;
				} else {
					return input.substring(i);
				}
			}
		}

		return "";
	}

	public static String trimEnd(String input, char patten) {

		for (int i = input.length() - 1; i >= 0; i--) {
			if (input.charAt(i) != patten) {
				if (i == input.length() - 1) {
					return input;
				} else {
					return input.substring(0, i + 1);
				}
			}
		}
		return "";
	}

	public static String trim(String input, char patten) {
		input = trimStart(input, patten);
		input = trimEnd(input, patten);

		return input;
	}

	public static String padLeft(String str, int length, char paddingChar) {
		int strLen = str.length();
		if (strLen >= length) {
			return str;
		}
		StringBuffer sb = new StringBuffer();
		for (int i = strLen; i < length; i++) {
			sb.append(paddingChar);
		}
		return sb.toString() + str;
	}

	public static String padRight(String str, int length, char paddingChar) {
		int strLen = str.length();
		if (strLen >= length) {
			return str;
		}
		StringBuffer sb = new StringBuffer();
		for (int i = strLen; i < length; i++) {
			sb.append(paddingChar);
		}
		return str + sb.toString();
	}

	public static String substring(String str, int index, int count) {
		return str.substring(index, index + count);
	}

	// 过滤表情符号
	private static String PatternEmoji = "[\ud83c\udc00-\ud83c\udfff]|[\ud83d\udc00-\ud83d\udfff]|[\u2600-\u27ff]";

	public static String filterEmojiChars(String srcStr, String replacement) {
		if (srcStr != null) {
			srcStr = srcStr.replaceAll(PatternEmoji, replacement);
		}
		return srcStr;
	}
	
	public static int indexOfCount(String input, String patten, boolean isIgnoreCase){
		if (isIgnoreCase) {
			input = input.toLowerCase();
			patten = patten.toLowerCase();
		}
		int count=0;
		int index=input.indexOf(patten, 0);
		
		while(index>-1){
			count++;
			index=input.indexOf(patten, index+patten.length());			
		}
		return count;
	}

	public static Object escapeSql(Object str){

		if(str instanceof String){

			String val=str.toString();
			if (val.startsWith("'") && val.endsWith("'")){
				return val;
			}
			return StringEscapeUtils.escapeSql(str.toString());
		}

		return str;
	}
			
}
