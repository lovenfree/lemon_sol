package com.lgcns.vportal.common.Feign.Exception;

import feign.Response;
import feign.codec.ErrorDecoder;

public class FeignErrorDecoder implements ErrorDecoder {

  @Override
  public Exception decode(String methodKey, Response response) {
    return new FeignCustomException(response.status(), response.reason());
  }
}
