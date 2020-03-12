package com.lgcns.vportal.idp.createVistApc.model;

import lombok.Data;

@Data
public class ImportGoodsVO {
  private String visitRequestId;
  private String applyMemberSequence;
  private String importedGoodsSequence;
  private String importedGoodsCode;
  private String importedGoodsPurpose;
  private String importedGoodsDescription;
  private String importedGoodsCounts;
  private String importedGoodsSerialNumber;
  private String importedGoodsWifiYesOrNo;
  private String importedGoodsWifiMacAddress;
  private String importedGoodsPortSealExceptYesOrNo;
  private String importedGoodsPortSealExceptUsb;
  private String importedGoodsPortSealExceptLan;
  private String importedGoodsPortSealExceptSdCard;
  private String importedGoodsPortSealExceptCamera;
  private String importedGoodsPortSealExceptEtc;
  private String importedGoodsPortSealExceptDescription;
}
