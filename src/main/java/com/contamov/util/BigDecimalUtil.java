package com.contamov.util;

import java.math.BigDecimal;

/**
 * @author abner
 */
public class BigDecimalUtil {
 
  public static BigDecimal format(BigDecimal n, int prec) {
    if (n == null)
      return new BigDecimal(0);
    if (n.compareTo(new BigDecimal(0)) == 0)
      return n.setScale(2, BigDecimal.ROUND_HALF_UP);
    else
      return n.setScale(prec, BigDecimal.ROUND_HALF_UP);
  }
  
  public static BigDecimal add(BigDecimal value, BigDecimal add, int prec) {
    if (value == null)
      value = new BigDecimal(0);
    if (add == null)
      add = new BigDecimal(0);
    return format(value.add(add),prec);
  }
  
  public static BigDecimal subtract(BigDecimal value, BigDecimal subtract, int prec) {
    if (value == null)
      value = new BigDecimal(0);
    if (subtract == null)
      subtract = new BigDecimal(0);
    return format(value.subtract(subtract),prec);
  }
  
  public static boolean isNegative(BigDecimal value) {
    value = format(value,2);
    return value.compareTo(new BigDecimal(0)) == -1;
  }
  
}