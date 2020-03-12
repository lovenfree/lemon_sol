package com.lgcns.vportal.integrateapproval.requestreservation.service;

import com.lgcns.vportal.common.Feign.interceptor.InterceptorEnum;
import com.lgcns.vportal.common.Feign.service.RequestFeignService;
import com.lgcns.vportal.common.model.ResponseBEVO;
import com.lgcns.vportal.integrateapproval.requestreservation.model.RequestReservationVO;
import com.lgcns.vportal.integrateapproval.requestreservation.model.ReservationVO;
import com.lgcns.vportal.integrateapproval.requestreservation.model.ResponseVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class RequestReservationService {

  @Autowired
  private RequestFeignService<ResponseVO> service;

  public ResponseBEVO<ResponseVO> callRequestReservation(String location, RequestReservationVO req) throws Exception {
    service.setLocation(location);
    service.setInterceptor(InterceptorEnum.BASE.getInterceptor(""));
    ReservationVO reservationData = req.getData();

    ResponseVO resultService = service.callService(reservationData, ResponseVO.class);
    ResponseBEVO<ResponseVO> result  = new ResponseBEVO();
    result.setSuccessOrNot(resultService.getSuccessYesOrNo());
    result.setStatusMessage(resultService.getStatusMessage());
    result.setStatusCode(resultService.getStatusCode());
    result.setData(resultService);
    return result;
  }
}
