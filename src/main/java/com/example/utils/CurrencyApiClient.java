package com.example.utils;

import com.example.database.dto.CurrencyDto;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;

@FeignClient(name="CurrencyRatesClient", url="${fixer.uri}")
public interface CurrencyApiClient {

  @GetMapping()
  @Cacheable("currency")
  CurrencyDto getCurrencyRate();
}
