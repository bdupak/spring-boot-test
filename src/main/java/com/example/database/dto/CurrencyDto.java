package com.example.database.dto;

import java.math.BigDecimal;
import java.util.Date;
import java.util.Map;
import lombok.Data;

@Data
public class CurrencyDto {
  private Boolean success;
  private String base;
  private Date date;
  private Map<String, BigDecimal> rates;
}
