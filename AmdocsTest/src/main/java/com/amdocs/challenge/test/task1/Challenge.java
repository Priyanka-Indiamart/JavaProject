package com.amdocs.challenge.test.task1;

import java.util.Arrays;

public class Challenge {

	public static boolean hasUniqueChars(String str) {
		char[] chArray = str.toCharArray();
		Arrays.sort(chArray);
		int len = chArray.length;
		for (int i = 0; i < len - 1; i++) {
			if (chArray[i] != chArray[i + 1])
				continue;
			else
				return false;
		}
		return true;
	}

	public static String markdownParser(String str) {
		String resultStr = "";
		String headerTag = "";
		String lastHeaderTag = "";
		String[] splitedArray = str.split("\\s+");
		int startIndex = 1;
		boolean isHashDiff = false;
		if (splitedArray.length > 0) {
			int index;
			if (splitedArray[0].length() == 0) {
				index = 1;
			} else {
				index = 0;
			}
			int noOfHeaderTag = splitedArray[index].length();
			char[] chArray = splitedArray[index].toCharArray();
			if (chArray.length < 7) {
				for (Character ch : chArray) {
					if (ch != '#') {
						isHashDiff = true;
					}
				}
				if (index == 1) {
					startIndex = 2;
				}
			} else {
				isHashDiff = true;
			}
			if (!isHashDiff) {
				if (noOfHeaderTag > 0) {
					headerTag = "h" + String.valueOf(noOfHeaderTag);
					resultStr = "<" + headerTag + ">";
					lastHeaderTag = "</" + headerTag + ">";
				}
			}
		}
		if (!isHashDiff) {
			for (int i = startIndex; i < splitedArray.length; i++) {
				if (i == (splitedArray.length - 1)) {
					resultStr = resultStr + splitedArray[i] + lastHeaderTag;
				} else {
					resultStr = resultStr + splitedArray[i] + " ";
				}
			}

		}
		if (resultStr == "") {
			resultStr = str;
		}
		return resultStr;
	}
	

}
