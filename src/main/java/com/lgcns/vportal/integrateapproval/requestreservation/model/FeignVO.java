package com.lgcns.vportal.integrateapproval.requestreservation.model;

import lombok.Data;

@Data
public class FeignVO<T> {
  private int code;
  private String message;
  private T data;
}
