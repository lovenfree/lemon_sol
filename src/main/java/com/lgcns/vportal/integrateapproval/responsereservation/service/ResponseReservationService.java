package com.lgcns.vportal.integrateapproval.responsereservation.service;

import com.lgcns.vportal.common.Feign.interceptor.InterceptorEnum;
import com.lgcns.vportal.common.Feign.service.RequestFeignService;
import com.lgcns.vportal.integrateapproval.responsereservation.model.ResponseFromBEVO;
import com.lgcns.vportal.integrateapproval.responsereservation.model.ResponseReservationVO;
import com.lgcns.vportal.integrateapproval.responsereservation.model.ResponseVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class ResponseReservationService {

  @Autowired
  private RequestFeignService<ResponseFromBEVO> service;

  public ResponseVO callResponseReservation(String location, ResponseReservationVO req, String path) throws Exception {
    service.setLocation(location);
    service.setInterceptor(InterceptorEnum.CORID.getInterceptor(path));

    ResponseFromBEVO resultService = service.callService(req, ResponseFromBEVO.class);
    ResponseVO result = resultService.getData();
    return result;
  }
}
