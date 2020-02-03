package com.lgcns.vportal.integrateapproval.responsereservation.model;

import lombok.Data;

import java.io.Serializable;

@Data
public class APIKeyResponseVO implements Serializable {
  private String isValid;

  @Override
  public String toString() {
    return "APIKeyResponseVO{" +
      "isValid='" + isValid + '\'' +
      '}';
  }
}
