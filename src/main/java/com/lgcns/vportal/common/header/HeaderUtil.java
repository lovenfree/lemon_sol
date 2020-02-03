package com.lgcns.vportal.common.header;

import com.lgcns.vportal.Util.DateUtils;
import org.springframework.http.HttpHeaders;

import java.util.UUID;

public class HeaderUtil {
  public static HttpHeaders getHeaders(){
    HttpHeaders headers = getDefaultHeader();
    // Expires
    return headers;
  }

  public static HttpHeaders getHeadersWithCorrelationID(String path){
    HttpHeaders headers = getDefaultHeader();
    headers.add(HeaderConstants.CORRELATION_ID_KEY, getCorrelationID(path));
    // Expires
    return headers;
  }

  private static HttpHeaders getDefaultHeader(){
    HttpHeaders headers = new HttpHeaders();
    headers.add(HeaderConstants.DATE_KEY, DateUtils.getCurrentDateRFC1123Format());
    headers.add(HeaderConstants.ACCEPT_KEY, HeaderConstants.ACCEPT_VAL);
    headers.add(HeaderConstants.ACCEPT_CHARSET_KEY, HeaderConstants.ACCEPT_CHARSET_VAL);
    headers.add(HeaderConstants.CONTENTS_TYPE_KEY, HeaderConstants.CONTENTS_TYPE_VAL);

    return headers;
  }

  public static String getCorrelationID(String path){
    return path + "_" + UUID.randomUUID().toString();
  }
}
