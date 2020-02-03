package com.lgcns.vportal.integrateapproval.requestreservation.restcontroller;

import com.lgcns.vportal.common.Feign.Exception.FeignCustomException;
import com.lgcns.vportal.common.header.HeaderUtil;
import com.lgcns.vportal.common.response.CreateResponse;
import com.lgcns.vportal.integrateapproval.requestreservation.model.RequestReservationVO;
import com.lgcns.vportal.integrateapproval.requestreservation.model.ResponseVO;
import com.lgcns.vportal.integrateapproval.requestreservation.service.RequestReservationService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/integrateapproval/api/reservation/v1")
public class RequestReservationRestController {

  private final Logger log = LoggerFactory.getLogger(getClass());

  @Autowired
  private RequestReservationService requestReservationService;

  @CrossOrigin(origins = "*")
  @RequestMapping(path = "/requestReservation", method = RequestMethod.POST)
  public ResponseEntity<ResponseVO> RequestReservation(@RequestBody RequestReservationVO req) {
    log.info("RequestReservation Controller");

    String location = req.getLocation();
    log.debug("location: " + location);

    ResponseVO result = null;
    HttpHeaders headers = HeaderUtil.getHeaders();
    try {
      log.debug("Service Call Start");
      result = requestReservationService.callRequestReservation(location, req);
      log.debug("Service Call End");
    } catch (FeignCustomException f) {
      log.error("Service Call Error  " + f.getMessage());
      return new CreateResponse().createInternalError(headers);
    } catch (Exception e){
      log.error(e.getMessage());
    }

    return new CreateResponse<ResponseVO>().createSuccess(result, headers);

  }
}
