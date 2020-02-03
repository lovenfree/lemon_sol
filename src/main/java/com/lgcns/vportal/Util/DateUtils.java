package com.lgcns.vportal.Util;

import java.time.*;
import java.time.format.DateTimeFormatter;
import java.util.Locale;

public class DateUtils {
  public static String getCurrentDateRFC1123Format(){
    DateTimeFormatter formatter = DateTimeFormatter.RFC_1123_DATE_TIME.withZone(ZoneId.systemDefault());
    return formatter.format(ZonedDateTime.now());
  }
}
