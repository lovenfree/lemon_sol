package com.lgcns.vportal.common.Feign.interceptor;

import feign.RequestInterceptor;

import java.util.function.Function;

public enum InterceptorEnum {
  BASE(value -> new BaseInterceptor()),
  CORID(value -> new CorrelationIdInterceptor(value)),
  IDP(value -> new IdpInterceptor(value));

  private Function<String, RequestInterceptor> expression;

  InterceptorEnum(Function<String, RequestInterceptor> expression) {
    this.expression = expression;
  }

  public RequestInterceptor getInterceptor(String path) {
    return expression.apply(path);
  }
}
