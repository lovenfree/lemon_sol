package com.lgcns.vportal.common.Feign.common;

import com.lgcns.vportal.common.Feign.service.RequestFeignMapper;
import feign.FeignException;
import feign.Response;
import feign.hystrix.FallbackFactory;
import org.springframework.stereotype.Component;

@Component
public class FeignFallback implements FallbackFactory<RequestFeignMapper> {

  @Override
  public RequestFeignMapper create(Throwable cause) {
    String httpStatus = cause instanceof FeignException ? Integer.toString(((FeignException) cause).status()) : "";

    return new RequestFeignMapper() {
      @Override
      public Response callService(Object request) {
//        logger.error(httpStatus);

        return null;
      }
    };
  }
}
