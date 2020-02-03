package com.lgcns.vportal.common.Feign.common;

import feign.Retryer;
import org.springframework.context.annotation.Bean;

public class FeignRetryer {

  private int period;
  private int maxPeriod;
  private int maxAttempts;

  public FeignRetryer(int period, int maxPeriod, int maxAttempts) {
    this.period = period;
    this.maxPeriod = maxPeriod;
    this.maxAttempts = maxAttempts;
  }

  @Bean
  public Retryer retryer() {
    return new Retryer.Default(period, maxPeriod, maxAttempts);
  }
}
