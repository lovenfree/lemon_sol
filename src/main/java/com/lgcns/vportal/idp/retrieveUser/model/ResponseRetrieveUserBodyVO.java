package com.lgcns.vportal.idp.retrieveUser.model;

import lombok.Data;

@Data
public class ResponseRetrieveUserBodyVO {
  private Number successCnt;
  private Number failCnt;
  private ResponseRetrieveUserListVO[] userInfoList;
}
