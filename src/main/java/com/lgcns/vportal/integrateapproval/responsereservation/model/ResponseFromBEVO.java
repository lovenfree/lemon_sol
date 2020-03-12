package com.lgcns.vportal.integrateapproval.responsereservation.model;

import com.lgcns.vportal.integrateapproval.responsereservation.model.ResponseVO;
import lombok.Data;

import java.io.Serializable;

@Data
public class ResponseFromBEVO implements Serializable {
  private String successOrNot;
  private String statusCode;
  private String statusMessage;
  private ResponseVO data;
}
