package com.lgcns.vportal.common.response;

import com.google.gson.GsonBuilder;
import com.lgcns.vportal.common.constants.Constants;
import com.lgcns.vportal.common.model.APIKeyErrorVO;
import com.lgcns.vportal.common.model.InternalErrorVO;
import com.lgcns.vportal.common.model.ResponseBEVO;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;

public class CreateResponse<ResVO> {
  public ResponseEntity createSuccess(ResVO resvo, HttpHeaders headers){
    return ResponseEntity.ok().headers(headers).body(resvo);
  }
  public ResponseEntity createSuccessToBE(ResVO resvo, HttpHeaders headers){
    return ResponseEntity.ok().headers(headers).body(resvo);
  }
  public ResponseEntity createInternalError(HttpHeaders headers){
    InternalErrorVO vo = new InternalErrorVO("ERROR", "Internal Server Error");
    return ResponseEntity.status(500).headers(headers).body(vo);
  }
  public ResponseEntity createApiKeyError(HttpHeaders headers){
    APIKeyErrorVO vo = new APIKeyErrorVO("ERROR", "API Key is not invalid");
    return ResponseEntity.status(500).headers(headers).body(vo);
  }
}
