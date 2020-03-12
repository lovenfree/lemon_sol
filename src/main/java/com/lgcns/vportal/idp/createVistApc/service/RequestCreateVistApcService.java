package com.lgcns.vportal.idp.createVistApc.service;

import com.lgcns.vportal.common.Feign.interceptor.InterceptorEnum;
import com.lgcns.vportal.common.Feign.service.RequestFeignService;
import com.lgcns.vportal.common.model.ResponseBEVO;
import com.lgcns.vportal.idp.createVistApc.model.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class RequestCreateVistApcService {

  @Autowired
  private RequestFeignService<ResponseRootVO> service;

  public ResponseBEVO callCreateVistApc(String location, RequestFromBEVO req, String sifSystemId) throws Exception {
    service.setLocation(location);
    service.setInterceptor(InterceptorEnum.IDP.getInterceptor(sifSystemId));
    ReservationVO reservationData = req.getData();

    ResponseRootVO resultRoot = service.callService(reservationData, ResponseRootVO.class);
    ResponseHeaderVO headerVo = resultRoot.getHeader();
    ResponseBEVO result = new ResponseBEVO();
    result.setSuccessOrNot(headerVo.getRsltCd().equals("00") ? "Y" : "N");
    result.setStatusCode(headerVo.getRsltCd());
    result.setStatusMessage(headerVo.getRsltMsg());
    return result;
  }
}
