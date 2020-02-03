package com.lgcns.vportal.common.Feign.Exception;

import org.apache.http.client.HttpResponseException;

public class FeignCustomException extends HttpResponseException {
  public FeignCustomException(int statusCode, String reasonPhrase) {
    super(statusCode, reasonPhrase);
  }
}
