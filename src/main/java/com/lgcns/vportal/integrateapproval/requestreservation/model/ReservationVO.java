package com.lgcns.vportal.integrateapproval.requestreservation.model;

import lombok.Data;

@Data
public class ReservationVO {
  private String visitRequestId;
  private String visitGroupCompanyId;
  private String IdpBuildingID;
  private String IdpAuthorityID;
  private String visitWorkplaceId;
  private String visitWorkplace;
  private String applyMemberDivision;
  private String applyMemberId;
  private String applyMemberName;
  private String applyMemberEmployeeNumber;
  private String applyMemberMobilePhone;
  private String applyMemberCompanyName;
  private String appliyMemberEmail;
  private String hostEmployeeCompanyId;
  private String hostEmployeeNumber;
  private String hostEmployeeName;
  private String fromVisitPeriod;
  private String toVisitPeriod;
  private String visitPurposeCode;
  private String visitPurposeDetail;
  private String applyDate;
  private VisitorVO[] applyMemberList;
}
