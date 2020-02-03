package com.lgcns.vportal.common.Feign.service;

import feign.RequestLine;
import feign.Response;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.RequestBody;

@FeignClient(name = "callService")
public interface RequestFeignMapper {
  @RequestLine("POST")
  Response callService(@RequestBody Object request);
}
