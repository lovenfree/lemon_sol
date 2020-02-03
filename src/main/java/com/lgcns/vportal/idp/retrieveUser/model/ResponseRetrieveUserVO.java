package com.lgcns.vportal.idp.retrieveUser.model;

import lombok.Data;

import java.util.Date;

@Data
public class ResponseRetrieveUserVO {
  private Number userId;
  private String userNm;
  private Number comId;
  private String comNm;
  private Number siteId;
  private Number deptId;
  private String deptNm;
  private String userEmpno;
  private String userBirth;
  private String userUseYn;
  private String userSt;
  private String userTypeCd;
  private String posnNm;
  private String copcomNm;
  private String tmanEmpno;
  private String userEngFstNm;
  private String userEngNm;
  private String userMobno;
  private String userEmail;
  private String comTelno;
  private String wkplcInfo;
  private String jikchekCd;
  private String boaniId;
  private String userPotoLoct;
  private String ifCopcomIdSt;
}
