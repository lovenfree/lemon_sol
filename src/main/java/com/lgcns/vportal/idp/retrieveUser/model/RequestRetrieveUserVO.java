package com.lgcns.vportal.idp.retrieveUser.model;

import lombok.Data;

@Data
public class RequestRetrieveUserVO {
  private Number userId;
  private String ifUserId;
  private Number comId;
  private String userTypeCd;
  private String userEmpno;
  private String userNm;
  private String copcomNm;
  private String userMobno;
}
