package com.msqgl.app.utils;

import org.apache.commons.lang3.StringUtils;

public class ValidationUtils {

  public static boolean validateString(String... strings) {
    for (String str : strings) {
      if (StringUtils.isEmpty(str)) {
        return Boolean.FALSE;
      }
    }
    return Boolean.TRUE;
  }


}
