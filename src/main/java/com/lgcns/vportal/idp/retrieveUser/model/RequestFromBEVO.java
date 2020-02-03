package com.lgcns.vportal.idp.retrieveUser.model;

import lombok.Data;

@Data
public class RequestFromBEVO {
  private String location;
  private RequestRetrieveUserVO data;
}
