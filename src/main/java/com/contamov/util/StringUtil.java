package com.contamov.util;

/**
 * @author abner
 */
public class StringUtil {
  
  public static String deixarSoNumeros(String str, boolean clearIfEmpty) {
    if (clearIfEmpty && (str == null || str.trim().length() == 0))
      return "";
    //
    int i;
    StringBuilder result = new StringBuilder("");
    for (i = 0; i < str.length(); i++) {
      if (str.charAt(i) >= '0' && str.charAt(i) <= '9') {
        result.append(str.charAt(i));
      }
    }
    return result.toString();
  }
  
}
