package com.lgcns.vportal.common.header;

import org.apache.http.HttpHeaders;
import org.springframework.http.MediaType;

public class HeaderConstants {
  public static final String DATE_KEY = HttpHeaders.DATE;
  public static final String ACCEPT_KEY = HttpHeaders.ACCEPT;
  public static final String ACCEPT_VAL = MediaType.APPLICATION_JSON_VALUE;
  public static final String ACCEPT_CHARSET_KEY = HttpHeaders.ACCEPT_CHARSET;
  public static final String ACCEPT_CHARSET_VAL = "UTF-8";
  public static final String CONTENTS_TYPE_KEY = HttpHeaders.CONTENT_TYPE;
  public static final String CONTENTS_TYPE_VAL = MediaType.APPLICATION_JSON_UTF8_VALUE;
  public static final String AUTHORIZATION_KEY = HttpHeaders.AUTHORIZATION;
  public static final String CORRELATION_ID_KEY = "X-Correlation-Id";
  public static final String SIF_SYSTEM_ID_KEY = "sifSystemId";
}
