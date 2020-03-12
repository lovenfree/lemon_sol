package com.lgcns.vportal.idp.retrieveUser.restcontroller;

import com.lgcns.vportal.common.Feign.Exception.FeignCustomException;
import com.lgcns.vportal.common.header.HeaderUtil;
import com.lgcns.vportal.common.model.SystemKeyVO;
import com.lgcns.vportal.common.response.CreateResponse;
import com.lgcns.vportal.idp.retrieveUser.model.RequestFromBEVO;
import com.lgcns.vportal.common.model.ResponseBEVO;
import com.lgcns.vportal.idp.retrieveUser.model.ResponseRetrieveUserDetailVO;
import com.lgcns.vportal.idp.retrieveUser.service.RequestRetrieveUserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/idp/api/visit/v1")
public class RequestRetrieveUserRestController {

  private final Logger log = LoggerFactory.getLogger(getClass());

  @Autowired
  private RequestRetrieveUserService requestRetrieveUserService;

  @Autowired
  private SystemKeyVO systemKeyVO;

  @CrossOrigin(origins = "*")
  @RequestMapping(path = "/retrieveuser", method = RequestMethod.POST)
  public ResponseEntity<ResponseBEVO> RequestRetrieveUserRestController(@RequestBody RequestFromBEVO req) {
    log.info("RequestRetrieveUserRestController Controller");

    String location = req.getLocation();
    log.debug("location: " + location);

    ResponseBEVO<ResponseRetrieveUserDetailVO> result = null;
    HttpHeaders headers = HeaderUtil.getHeaders();
    try {
      log.debug("Service Call Start");
      result = requestRetrieveUserService.callRetrieveUser(location, req, systemKeyVO.getSifSystemId());
      log.debug("Service Call End");
    } catch (FeignCustomException f) {
      log.error("Service Call Error  " + f.getMessage());
      return new CreateResponse().createInternalError(headers);
    } catch (Exception e){
      log.error(e.getMessage());
    }

    if(result == null){
      return new CreateResponse().createInternalError(headers);
    }

    return new CreateResponse<ResponseBEVO<ResponseRetrieveUserDetailVO>>().createSuccessToBE(result, headers);

  }
}
