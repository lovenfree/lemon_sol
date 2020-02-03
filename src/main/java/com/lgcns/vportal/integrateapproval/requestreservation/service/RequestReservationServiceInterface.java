package com.lgcns.vportal.integrateapproval.requestreservation.service;

import com.lgcns.vportal.integrateapproval.requestreservation.model.RequestReservationVO;
import feign.Response;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.validation.Valid;

@FeignClient(name = "integrate-approval-request-reservation", url = "http://localhost:1080", decode404 = false)
public interface RequestReservationServiceInterface {
  @RequestMapping(value = "/v1/register", method = RequestMethod.POST)
  Response TestCall(@RequestBody @Valid RequestReservationVO request);
}
