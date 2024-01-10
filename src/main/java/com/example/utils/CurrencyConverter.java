package com.example.utils;

import com.example.database.dto.CurrencyDto;
import com.example.database.model.Product;
import com.example.exception.NotFoundException;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.Objects;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;

@Configuration
public class CurrencyConverter {

  public static final String DEFAULT_CURRENCY = "EUR";

  @Autowired
  private CurrencyApiClient currencyApiClient;

  public Product convertCurrency(Product product) throws NotFoundException {

    if (DEFAULT_CURRENCY.equals(product.getCurrency())) {
      return product;
    } else {
      final CurrencyDto currencyDto = currencyApiClient.getCurrencyData();
      BigDecimal exchangeRate = null;
      if (Objects.nonNull(currencyDto) && Objects.nonNull(currencyDto.getRates())) {
        exchangeRate = currencyDto.getRates().get(product.getCurrency());
        if (Objects.nonNull(exchangeRate)) {
          product.setPrice(product.getPrice().multiply(exchangeRate));
          product.setCurrency(DEFAULT_CURRENCY);
        } else {
          throw new NotFoundException(
              "Exchange rates not found for currency = " + product.getCurrency());
        }
      } else {
        throw new NotFoundException(
            "Exchange rates not found for currency = " + product.getCurrency());
      }
    }
    return product;
  }
}