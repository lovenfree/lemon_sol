package com.lgcns.vportal.integrateapproval.requestreservation.model;

import lombok.Data;

@Data
public class ImportGoodsVO {
  private String importedGoodsSequence;
  private String importedGoodsCode;
  private String importedGoodsPurpose;
  private String importedGoodsDescription;
  private String importedGoodsCounts;
  private String importedGoodsSerialNumber;
  private String importedGoodsWifiYesOrNo;
  private String importedGoodsWifiMacAddress;
  private String importedGoodsLanYesOrNo;
}
