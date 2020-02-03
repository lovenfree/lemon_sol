package com.lgcns.vportal.idp.createVistApc.restcontroller;

import com.lgcns.vportal.common.Feign.Exception.FeignCustomException;
import com.lgcns.vportal.common.header.HeaderUtil;
import com.lgcns.vportal.common.model.ResponseBEVO;
import com.lgcns.vportal.common.model.SystemKeyVO;
import com.lgcns.vportal.common.response.CreateResponse;
import com.lgcns.vportal.idp.createVistApc.model.RequestFromBEVO;
import com.lgcns.vportal.idp.createVistApc.model.ResponseCreateVistApcVO;
import com.lgcns.vportal.idp.createVistApc.service.RequestCreateVistApcService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/idp/api/visit/v1")
public class RequestCreateVistApcRestController {

  private final Logger log = LoggerFactory.getLogger(getClass());

  @Autowired
  private RequestCreateVistApcService requestCreateVistApcService;

  @Autowired
  private SystemKeyVO systemKeyVO;

  @CrossOrigin(origins = "*")
  @RequestMapping(path = "/createvisitapc", method = RequestMethod.POST)
  public ResponseEntity<ResponseBEVO> RequestCreateVistApcRestController(@RequestBody RequestFromBEVO req) {
    log.info("RequestCreateVistApcRestController Controller");

    String location = req.getLocation();
    log.debug("location: " + location);

    ResponseCreateVistApcVO result = null;
    HttpHeaders headers = HeaderUtil.getHeaders();
    try {
      log.debug("Service Call Start");
      result = requestCreateVistApcService.callCreateVistApc(location, req, systemKeyVO.getSifSystemId());
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

    return new CreateResponse<ResponseCreateVistApcVO>().createSuccessToBE(result, headers);

  }
}
