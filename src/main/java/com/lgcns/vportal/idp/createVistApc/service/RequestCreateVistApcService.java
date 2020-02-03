package com.lgcns.vportal.idp.createVistApc.service;

import com.lgcns.vportal.common.Feign.interceptor.InterceptorEnum;
import com.lgcns.vportal.common.Feign.service.RequestFeignService;
import com.lgcns.vportal.idp.createVistApc.model.RequestCreateVistApcVO;
import com.lgcns.vportal.idp.createVistApc.model.RequestFromBEVO;
import com.lgcns.vportal.idp.createVistApc.model.ResponseCreateVistApcVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class RequestCreateVistApcService {

  @Autowired
  private RequestFeignService<ResponseCreateVistApcVO> service;

  public ResponseCreateVistApcVO callCreateVistApc(String location, RequestFromBEVO req, String sifSystemId) throws Exception {
    service.setLocation(location);
    service.setInterceptor(InterceptorEnum.IDP.getInterceptor(sifSystemId));
    RequestCreateVistApcVO reservationData = req.getData();

    ResponseCreateVistApcVO result = service.callService(reservationData, ResponseCreateVistApcVO.class);

    return result;
  }
}
