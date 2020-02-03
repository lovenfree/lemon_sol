package com.lgcns.vportal.common.model;

import com.lgcns.vportal.idp.retrieveUser.model.ResponseRetrieveUserVO;
import lombok.Data;

@Data
public class ResponseBEVO<ResVO> {
  private String successOrNot;
  private String statusCode;
  private String statusMessage;
  private ResVO data;
}
