package com.lgcns.vportal.health.restcontroller;

import com.lgcns.vportal.common.header.HeaderUtil;
import com.lgcns.vportal.integrateapproval.requestreservation.service.RequestReservationService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
public class HealthController {

  private final Logger log = LoggerFactory.getLogger(getClass());

  @Value("${env}")
  private String env;

  @Autowired
  private RequestReservationService requestReservationService;

  @CrossOrigin(origins = "*")
  @RequestMapping(path = "/health", method = RequestMethod.GET)
  public ResponseEntity Health() {
    log.info("Health Controller");

    HttpHeaders headers = HeaderUtil.getHeadersWithCorrelationID("health");

    return ResponseEntity.ok().headers(headers).body("{\"env\":\"" + env + "\", \"status\":\"!!!Success!!!\"}");
  }
}
