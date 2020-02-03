package com.lgcns.vportal.common.model;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import lombok.Data;

@Data
@Component
public class SystemKeyVO {
  @Value("${idp.sifsystemid}")
  private String sifSystemId;
}
