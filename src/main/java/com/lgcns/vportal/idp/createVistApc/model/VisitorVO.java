package com.lgcns.vportal.idp.createVistApc.model;

import lombok.Data;

@Data
public class VisitorVO {
  private String visitRequestId;
  private String applyMemberSequence;
  private String applyMemberDivision;
  private String applyMemberId;
  private String applyMemberName;
  private String applyMemberEmployeeNumber;
  private String applyMemberMobilePhone;
  private String applyMemberCompanyName;
  private String applyMemberDepartment;
  private String applyMemberPosition;
  private String applyMemberCarNumber;
  // Email 요청 정보 무시
  // private String applyMemberEmail;
  private String idpUserId;
  private String fromVisitPeriod;
  private String toVisitPeriod;
  private String importedGoodsYesOrNo;
  private ImportGoodsVO[] importedGoodsList;
}
