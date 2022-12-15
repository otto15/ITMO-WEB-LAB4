package com.otto.lab4.controller.dto;

import lombok.Value;
import java.util.List;

@Value
public class GetAllHitChecksResponse {
  List<HitCheckDTO> hitChecks;
}