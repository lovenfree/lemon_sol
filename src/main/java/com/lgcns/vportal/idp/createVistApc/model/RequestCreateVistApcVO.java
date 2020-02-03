package com.lgcns.vportal.idp.createVistApc.model;

import lombok.Data;

import java.util.Date;

@Data
public class RequestCreateVistApcVO {
  private String ifVistId;
  private Number comId;
  private Number siteId;
  private Number userTypeId;
  private String vistStrDt;
  private String vistEndDt;
  private String vistDeptId;
  private Number apltId;
  private Number apprId;
  private Date apltDtm;
  private Date apprDtm;
  private String vistPursCd;
  private String vistPursRsn;
  private String vistCarNo;
  private String caryItemCd;
  private String caryItemRsn;
  private String caryItemOut;
  private Number vstrId;
  private String vstrNm;
  private String vstrBirth;
  private String vstrComNm;
  private String vstrDeptNm;
  private String vstrPosnNm;
  private String vstrMobno;
}
