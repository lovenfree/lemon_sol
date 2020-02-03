package com.lgcns.vportal.integrateapproval.responsereservation.service;

import com.lgcns.vportal.common.Feign.interceptor.BaseInterceptor;
import com.lgcns.vportal.common.Feign.interceptor.InterceptorEnum;
import com.lgcns.vportal.common.Feign.service.RequestFeignService;

import com.lgcns.vportal.integrateapproval.responsereservation.model.APIKeyRequestVO;
import com.lgcns.vportal.integrateapproval.responsereservation.model.APIKeyResponseVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class RequestAPIKeyValidateService {

  @Autowired
  private RequestFeignService<APIKeyResponseVO> service;

  public APIKeyResponseVO callRequestAPIKey(String location, APIKeyRequestVO req) throws Exception {
    service.setLocation(location);
    service.setInterceptor(InterceptorEnum.BASE.getInterceptor(""));

    APIKeyResponseVO result = service.callService(req, APIKeyResponseVO.class);

    return result;
  }
}
