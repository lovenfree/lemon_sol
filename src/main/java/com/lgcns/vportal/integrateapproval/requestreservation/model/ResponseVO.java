package com.lgcns.vportal.integrateapproval.requestreservation.model;

import lombok.Data;

import java.io.Serializable;

@Data
public class ResponseVO implements Serializable {
  private String visitRequestId;
  private String visitGroupCompanyId;
  private String visitWorkplaceId;
  private String visitWorkplace;
  private String successYesOrNo;
  private String statusCode;
  private String statusMessage;
}
