package com.lgcns.vportal.integrateapproval.responsereservation.model;

import com.lgcns.vportal.integrateapproval.requestreservation.model.ReservationVO;
import lombok.Data;

@Data
public class ResponseReservationVO {
  private String visitRequestId;
  private String visitGroupCompanyId;
  private String visitWorkplaceId;
  private String visitWorkplace;
  private String approvalStatus;
  private String approvalMessage;
}
