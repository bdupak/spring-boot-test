package com.example.utils;

import com.example.database.dto.CurrencyDto;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;

@Configuration
@PropertySource("classpath:application.properties")
public class CurrencyApiClient {

  @Value("${fixer.uri}")
  private String fixerUri;
  @Value("${fixer.apiKey}")
  private String apiKey;

  public CurrencyDto getCurrencyData() {
    final String uri = fixerUri + apiKey;
    final RestTemplate restTemplate = new RestTemplate();
    final ResponseEntity<CurrencyDto> response = restTemplate.getForEntity(uri, CurrencyDto.class);
    return response.getBody();
  }
}
