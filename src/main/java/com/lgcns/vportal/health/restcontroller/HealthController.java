package com.lgcns.vportal.health.restcontroller;

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
public class HealthController {

  private final Logger log = LoggerFactory.getLogger(getClass());

  @Autowired
  private RequestReservationService requestReservationService;

  @CrossOrigin(origins = "*")
  @RequestMapping(path = "/health", method = RequestMethod.GET)
  public ResponseEntity Health() {
    log.info("Health Controller");

    return ResponseEntity.ok().body("Success");
  }
}
