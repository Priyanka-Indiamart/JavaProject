package com.amdocs.challenge.test.task3;
public class CalculateRotation {
		
	public static int shiftedDiff(String str1, String str2) {

		if (str1.length() != str2.length()){
			return -1;
		}

		int i, j, res = 0;
		int count[] = new int[256];

		for (int k = 0; k < str1.length(); k++) {
			count[str1.charAt(k)]++;
			count[str2.charAt(k)]--;
		}

		for (int l = 0; l < 256; l++){
			if (count[l] != 0){
				return -1;
			}
		}

		boolean isRotationStr = (str1+str1).indexOf(str2) != -1;
		if(!isRotationStr){
			return -1;
		}

		i = str1.length() - 1;
		j = str2.length() - 1;
		
		while (i >= 0) {
			if (str1.charAt(i) != str2.charAt(j))
				res++;
			else
				j--;
			i--;
		}
		return res;
	}
}
