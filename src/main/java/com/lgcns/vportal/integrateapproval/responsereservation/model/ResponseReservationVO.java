package com.lgcns.vportal.integrateapproval.responsereservation.model;

import com.lgcns.vportal.integrateapproval.requestreservation.model.ReservationVO;
import lombok.Data;

@Data
public class ResponseReservationVO {
  private String visitRequestID;
  private String visitGroupCompanyID;
  private String visitWorkplaceID;
  private String visitWorkplace;
  private String approvalStatus;
  private String approvalMessage;
}
