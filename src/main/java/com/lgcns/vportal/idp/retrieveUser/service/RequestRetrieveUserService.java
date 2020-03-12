package com.lgcns.vportal.idp.retrieveUser.service;

import com.lgcns.vportal.common.Feign.interceptor.InterceptorEnum;
import com.lgcns.vportal.common.Feign.service.RequestFeignService;
import com.lgcns.vportal.common.model.ResponseBEVO;
import com.lgcns.vportal.idp.retrieveUser.model.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class RequestRetrieveUserService {

  @Autowired
  private RequestFeignService<ResponseRetrieveUserRootVO> service;

  public ResponseBEVO<ResponseRetrieveUserDetailVO> callRetrieveUser(String location, RequestFromBEVO req, String sifSystemId) throws Exception {
    service.setLocation(location);
    service.setInterceptor(InterceptorEnum.IDP.getInterceptor(sifSystemId));
    RequestRetrieveUserVO reservationData = req.getData();
    RequestRetrieveUserBodyVO reqBody = new RequestRetrieveUserBodyVO();
    RequestRetrieveUserVO[] reqList = new RequestRetrieveUserVO[]{
      reservationData
    };
    reqBody.setUserInfoList(reqList);
    ResponseBEVO<ResponseRetrieveUserDetailVO> result = new ResponseBEVO();
    ResponseRetrieveUserRootVO resultService = service.callService(reqBody, ResponseRetrieveUserRootVO.class);
    ResponseRetrieveUserListVO[] userVoList = resultService.getBody().getUserInfoList();
    ResponseRetrieveUserDetailVO user = new ResponseRetrieveUserDetailVO();
    if(userVoList.length > 0){
      user.setUserId(userVoList[0].getChkUserId());
    }
    result.setData(user);
    ResponseRetrieveUserHeaderVO headerVo = resultService.getHeader();
    result.setSuccessOrNot(headerVo.getRsltCd().equals("00") ? "Y" : "N");
    result.setStatusCode(headerVo.getRsltCd());
    result.setStatusMessage(headerVo.getRsltMsg());
    return result;
  }
}
