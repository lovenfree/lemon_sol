package com.lgcns.vportal.common.model;

import lombok.Data;

@Data
public class InternalErrorVO {
  private String code;
  private String message;

  public InternalErrorVO(String code, String message) {
    this.code = code;
    this.message = message;
  }
}
