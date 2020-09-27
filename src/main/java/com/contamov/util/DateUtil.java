package com.contamov.util;

import java.text.ParseException;
import java.text.SimpleDateFormat;

/**
 * @author abner
 */
public class DateUtil {
  
  public static java.util.Date getDate(String data) {
    SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
    try {
      return format.parse(data);
    } catch (ParseException e) {
      e.printStackTrace();
      return null;
    }
  }
  
}