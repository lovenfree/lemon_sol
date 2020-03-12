package com.lgcns.vportal.idp.retrieveUser.model;

import lombok.Data;

@Data
public class ResponseRetrieveUserRootVO {
  private ResponseRetrieveUserHeaderVO header;
  private ResponseRetrieveUserBodyVO body;
}
