package com.lgcns.vportal.idp.createVistApc.model;

import lombok.Data;

import java.util.Date;

@Data
public class RequestFromBEVO {
  private String location;
  private ReservationVO data;
}
