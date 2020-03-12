package com.lgcns.vportal.idp.createVistApc.model;

import lombok.Data;

@Data
public class ReservationVO {
  private String visitRequestId;
  private String visitGroupCompanyId;
  private String visitWorkplaceId;
  private String visitWorkplace;
  private String idpBuildingId;
  private String idpAuthorityId;
  private String applyMemberDivision;
  private String applyMemberId;
  private String applyMemberName;
  private String applyMemberEmployeeNumber;
  private String applyMemberMobilePhone;
  private String applyMemberCompanyName;
  private String applyMemberDepartment;
  private String applyMemberPosition;
  private String hostEmployeeCompanyId;
  private String hostEmployeeCompanyName;
  private String hostEmployeeId;
  private String hostEmployeeNumber;
  private String hostEmployeeName;
  private String hostEmployeeDepartment;
  private String hostEmployeePosition;
  private String fromVisitPeriod;
  private String toVisitPeriod;
  private String visitPurposeCode;
  private String visitPurposeDetail;
  private String applyDate;
  private String approvalStatus;
  private String approvalMessage;
  private String workplaceContent;
  private VisitorVO[] applyMemberList;
}
