package com.lgcns.vportal.integrateapproval.responsereservation.restcontroller;

import com.lgcns.vportal.common.Feign.Exception.FeignCustomException;
import com.lgcns.vportal.common.header.HeaderUtil;
import com.lgcns.vportal.common.response.CreateResponse;
import com.lgcns.vportal.integrateapproval.responsereservation.model.APIKeyRequestVO;
import com.lgcns.vportal.integrateapproval.responsereservation.model.APIKeyResponseVO;
import com.lgcns.vportal.integrateapproval.responsereservation.model.ResponseReservationVO;
import com.lgcns.vportal.integrateapproval.responsereservation.model.ResponseVO;
import com.lgcns.vportal.integrateapproval.responsereservation.service.RequestAPIKeyValidateService;
import com.lgcns.vportal.integrateapproval.responsereservation.service.ResponseReservationService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/integrateapproval/api/reservation/v1")
public class ResponseReservationRestController {

  private final Logger log = LoggerFactory.getLogger(getClass());

  @Value("${vportal.visit.host}")
  private String visitHost;

  @Value("${vportal.visit.port}")
  private String visitPort;

  @Value("${vportal.visit.uri.approved}")
  private String visitApprovedUri;

  @Value("${apim.host}")
  private String apimHost;

  @Value("${apim.port}")
  private String apimPort;

  @Value("${apim.uri.check}")
  private String apimCheckUri;

  @Autowired
  private ResponseReservationService responseReservationService;

  @Autowired
  private RequestAPIKeyValidateService requestAPIKeyValidateService;

  @CrossOrigin(origins = "*")
  @RequestMapping(path = "/responseReservation", method = RequestMethod.POST)
  public ResponseEntity<ResponseVO> ResponseReservation(@RequestHeader(value="X-VPORTAL-APIKEY", required = false) String apiKey,  @RequestBody ResponseReservationVO req) {
    log.info("ResponseReservation Controller");

    log.debug("apiKey: " + apiKey);

    String apimLocation = apimHost + ((apimPort.equals("") || apimPort == null) ? "" : ":" + apimPort) + apimCheckUri;

    String location = visitHost + ((visitPort.equals("") || visitPort == null) ? "" : ":" + visitPort) + visitApprovedUri;

    log.debug("apimLocation: " + apimLocation);
    log.debug("location: " + location);

    HttpHeaders headers = HeaderUtil.getHeaders();
    /*
    // APIM Check API Key
    APIKeyRequestVO apiReq = new APIKeyRequestVO();
    apiReq.setApiKey(apiKey);

    APIKeyResponseVO checkResult = null;
    HttpHeaders headers = HeaderUtil.getHeaders();
    try {
      log.debug("Service API Call Start");
      checkResult = requestAPIKeyValidateService.callRequestAPIKey(apimLocation, apiReq);
      log.debug("Service API Call End");
    } catch (FeignCustomException f) {
      log.error("Service API Call Error");
      return new CreateResponse().createInternalError(headers);
    } catch (Exception e){
      log.error(e.getMessage());
    }
    if(checkResult == null || !checkResult.getIsValid().equals("Y")){
      return new CreateResponse().createApiKeyError(headers);
    }
    */
    if(apiKey == null || apiKey.equals("") || !apiKey.equals("V7vportalKJHD110")){
      return new CreateResponse().createApiKeyError(headers);
    }

    // IDP CreateV

    // Send approved data to VisitBE
    ResponseVO result = null;
    try {
      log.debug("Service Call Start");
      result = responseReservationService.callResponseReservation(location, req, this.getClass().getSimpleName());
      log.debug("Service Call End");
    } catch (FeignCustomException f) {
      log.error("Service Call Error");
      return new CreateResponse().createInternalError(headers);
    } catch (Exception e){
      log.error(e.getMessage());
    }

    return new CreateResponse<ResponseVO>().createSuccess(result, headers);

  }
}
