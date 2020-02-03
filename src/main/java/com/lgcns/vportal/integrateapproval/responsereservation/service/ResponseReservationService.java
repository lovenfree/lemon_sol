package com.lgcns.vportal.integrateapproval.responsereservation.service;

import com.lgcns.vportal.common.Feign.interceptor.InterceptorEnum;
import com.lgcns.vportal.common.Feign.service.RequestFeignService;
import com.lgcns.vportal.integrateapproval.responsereservation.model.ResponseReservationVO;
import com.lgcns.vportal.integrateapproval.responsereservation.model.ResponseVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class ResponseReservationService {

  @Autowired
  private RequestFeignService<ResponseVO> service;

  public ResponseVO callResponseReservation(String location, ResponseReservationVO req, String path) throws Exception {
    service.setLocation(location);
    service.setInterceptor(InterceptorEnum.CORID.getInterceptor(path));

    ResponseVO result = service.callService(req, ResponseVO.class);

    return result;
  }
}
