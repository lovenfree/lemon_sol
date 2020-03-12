package com.lgcns.vportal.common.Feign.service;

import com.google.gson.Gson;
import com.lgcns.vportal.Util.GsonUtils;
import com.lgcns.vportal.common.Feign.Exception.FeignCustomException;
import com.lgcns.vportal.common.Feign.Exception.FeignErrorDecoder;
import com.lgcns.vportal.common.Feign.common.FeignCustomConfiguration;
import com.lgcns.vportal.common.Feign.common.FeignRetryer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpHeaders;
import feign.*;
import feign.gson.GsonDecoder;
import feign.jackson.JacksonDecoder;
import feign.jackson.JacksonEncoder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.util.Collection;
import java.util.Map;

@Component
public class RequestFeignService<ResVO> {

  private final Logger log = LoggerFactory.getLogger(getClass());

  private String location;
  private RequestFeignMapper service;
  private Feign.Builder b;
  private RequestInterceptor interceptor;

  @Value("${feign.client.config.default.connectTimeout}")
  private int connectTimeout;

  @Value("${feign.client.config.default.readTimeout}")
  private int readTimeout;

  @Value("${feign.retryer.period}")
  private int period;

  @Value("${feign.retryer.maxPeriod}")
  private int maxPeriod;

  @Value("${feign.retryer.maxAttempts}")
  private int maxAttempts;

  @Autowired
  private FeignCustomConfiguration feignCustomConfiguration;

  public void setLocation(String location){
    this.location = location;
  }
  public void setInterceptor(RequestInterceptor requestInterceptor){
    this.interceptor = requestInterceptor;
  }

  private Feign.Builder getInstance(){
//    if(this.b == null){
      this.b = Feign.builder().encoder(new JacksonEncoder()).decoder(new JacksonDecoder()).errorDecoder(new FeignErrorDecoder()).retryer(new FeignRetryer(period, maxPeriod, maxAttempts).retryer())
      .options(new Request.Options(connectTimeout, readTimeout)).client(feignCustomConfiguration.feignClient());
//    }
    if(this.interceptor != null){
      this.b.requestInterceptor(this.interceptor);
    }
    return this.b;
  }

  private RequestFeignMapper getService(Feign.Builder b, String url){
    return b.target(RequestFeignMapper.class, url);
  }

  private RequestFeignMapper getService(String url){
    return getInstance().target(RequestFeignMapper.class, url);
  }

  public ResVO callService(Object req, Class<ResVO> classType) throws Exception {
    ResVO res = null;
    try {
      this.service = getService(this.location);
      log.debug("Request Data: " + req.toString());
      Gson gson = new Gson();
      String reqStr = gson.toJson(req);
      log.debug("Request Data Str: " + reqStr);
      Response result = service.callService(req);
      log.debug("Result status: " + result.status());
      log.debug("Result headers: " + result.headers().toString());
      log.debug("Result body: " + result.body().toString());

      res = (ResVO) new GsonDecoder().decode(result, classType);
      log.debug("Result res: " + res.toString());
    } catch (Exception e) {
      throw new FeignCustomException(500, e.getMessage());
    }
    return res;
  }
}
