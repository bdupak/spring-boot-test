package com.example.utils;

import com.example.database.dto.CurrencyDto;
import com.example.database.entity.Product;
import com.example.exception.NotFoundException;
import java.math.BigDecimal;
import java.math.MathContext;
import java.math.RoundingMode;
import java.util.Objects;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class CurrencyConverter {

  public static final String DEFAULT_CURRENCY = "EUR";

  @Autowired
  private CurrencyApiClient currencyClient;

  public Product convertCurrency(Product product) throws NotFoundException {

    if (DEFAULT_CURRENCY.equals(product.getCurrency())) {
      return product;
    } else {
      Logger logger = LoggerFactory.getLogger(CurrencyConverter.class);
      long nanoTime = System.nanoTime();
      logger.info("currency api starting ");
      final CurrencyDto currencyDto = currencyClient.getCurrencyRate();
      logger.info("currency api ended {}", System.nanoTime() - nanoTime);
      BigDecimal exchangeRate = null;
      if (Objects.nonNull(currencyDto) && Objects.nonNull(currencyDto.getRates())) {
        exchangeRate = currencyDto.getRates().get(product.getCurrency());
        if (Objects.nonNull(exchangeRate)) {
          final int precision = product.getPrice().precision() + 4;
          final MathContext mathContext = new MathContext(precision, RoundingMode.HALF_UP);
          final BigDecimal newPrice =
              product.getPrice().divide(exchangeRate, mathContext);
          product.setPrice(newPrice);
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
