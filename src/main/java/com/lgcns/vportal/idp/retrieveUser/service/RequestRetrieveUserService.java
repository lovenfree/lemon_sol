package com.lgcns.vportal.idp.retrieveUser.service;

import com.lgcns.vportal.common.Feign.interceptor.InterceptorEnum;
import com.lgcns.vportal.common.Feign.service.RequestFeignService;
import com.lgcns.vportal.idp.retrieveUser.model.RequestFromBEVO;
import com.lgcns.vportal.idp.retrieveUser.model.RequestRetrieveUserVO;
import com.lgcns.vportal.idp.retrieveUser.model.ResponseRetrieveUserVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class RequestRetrieveUserService {

  @Autowired
  private RequestFeignService<ResponseRetrieveUserVO> service;

  public ResponseRetrieveUserVO callRetrieveUser(String location, RequestFromBEVO req, String sifSystemId) throws Exception {
    service.setLocation(location);
    service.setInterceptor(InterceptorEnum.IDP.getInterceptor(sifSystemId));
    RequestRetrieveUserVO reservationData = req.getData();

    ResponseRetrieveUserVO result = service.callService(reservationData, ResponseRetrieveUserVO.class);

    return result;
  }
}
