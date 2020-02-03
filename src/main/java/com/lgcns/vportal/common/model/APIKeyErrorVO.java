package com.lgcns.vportal.common.model;

import lombok.Data;

@Data
public class APIKeyErrorVO {
  private String code;
  private String message;

  public APIKeyErrorVO(String code, String message) {
    this.code = code;
    this.message = message;
  }
}
