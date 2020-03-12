package com.lgcns.vportal.common.Feign.interceptor;

import com.lgcns.vportal.Util.DateUtils;
import com.lgcns.vportal.common.header.HeaderConstants;
import com.lgcns.vportal.common.header.HeaderUtil;
import feign.RequestInterceptor;
import feign.RequestTemplate;

public class CorrelationIdInterceptor implements RequestInterceptor {

  private String path;

  public CorrelationIdInterceptor(String path) {
    this.path = path;
  }

  @Override
  public void apply(RequestTemplate template) {
    template.header(HeaderConstants.DATE_KEY, DateUtils.getCurrentDateRFC1123Format());
    template.header(HeaderConstants.ACCEPT_KEY, HeaderConstants.ACCEPT_VAL);
    template.header(HeaderConstants.ACCEPT_CHARSET_KEY, HeaderConstants.ACCEPT_CHARSET_VAL);
    template.header(HeaderConstants.CONTENTS_TYPE_KEY, HeaderConstants.CONTENTS_TYPE_VAL);
    template.header(HeaderConstants.CORRELATION_ID_KEY, HeaderUtil.getCorrelationID(path));
    System.out.println("CorrelationIdInterceptor Header:" + template.headers());
  }
}
