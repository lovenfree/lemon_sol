package com.lgcns.vportal.common.model;

import lombok.Data;

@Data
public class ResponseBEVO<ResVO> {
  private String successOrNot;
  private String statusCode;
  private String statusMessage;
  private ResVO data;
}
