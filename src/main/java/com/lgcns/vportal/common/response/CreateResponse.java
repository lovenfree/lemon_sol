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
    headers.setContentLength(new GsonBuilder().create().toJson(resvo).getBytes().length);
    return ResponseEntity.ok().headers(headers).body(resvo);
  }
  public ResponseEntity createSuccessToBE(ResVO resvo, HttpHeaders headers){
    ResponseBEVO<ResVO> result = new ResponseBEVO();
    if(resvo != null){
      result.setStatusCode(Constants.HTTP_OK);
      result.setStatusMessage(Constants.SUCCESS);
      result.setSuccessOrNot(Constants.Y);
      result.setData(resvo);
    }
    headers.setContentLength(new GsonBuilder().create().toJson(result).getBytes().length);
    return ResponseEntity.ok().headers(headers).body(result);
  }
  public ResponseEntity createInternalError(HttpHeaders headers){
    InternalErrorVO vo = new InternalErrorVO("ERROR", "Internal Server Error");
    headers.setContentLength(new GsonBuilder().create().toJson(vo).getBytes().length);
    return ResponseEntity.status(500).headers(headers).body(vo);
  }
  public ResponseEntity createApiKeyError(HttpHeaders headers){
    APIKeyErrorVO vo = new APIKeyErrorVO("ERROR", "API Key is not invalid");
    headers.setContentLength(new GsonBuilder().create().toJson(vo).getBytes().length);
    return ResponseEntity.status(500).headers(headers).body(vo);
  }
}
