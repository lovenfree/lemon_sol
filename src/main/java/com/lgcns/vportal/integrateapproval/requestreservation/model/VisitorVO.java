package com.lgcns.vportal.integrateapproval.requestreservation.model;

import lombok.Data;

@Data
public class VisitorVO {
  private String applyMemberSequence;
  private String applyMemberDivision;
  private String applyMemberId;
  private String applyMemberName;
  private String applyMemberEmployeeNumber;
  private String applyMemberMobilePhone;
  private String applyMemberCompanyName;
  private String applyMemberCarNumber;
  private String applyMemberEmail;
  private String fromVisitPeriod;
  private String toVisitPeriod;
  private String importedGoodsYesOrNo;
  private ImportGoodsVO[] importedGoodsList;
}
