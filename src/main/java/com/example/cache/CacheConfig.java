package com.example.cache;

import com.google.common.cache.CacheBuilder;
import java.util.concurrent.TimeUnit;
import org.springframework.cache.Cache;
import org.springframework.cache.CacheManager;
import org.springframework.cache.concurrent.ConcurrentMapCache;
import org.springframework.cache.concurrent.ConcurrentMapCacheManager;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;

@Component
public class CacheConfig {

  @Bean
  public CacheManager cacheManager() {
    return new ConcurrentMapCacheManager() {
      @Override
      protected Cache createConcurrentMapCache(String name) {
        return new ConcurrentMapCache(
            name,
            CacheBuilder.newBuilder()
                .maximumSize(256)
                .concurrencyLevel(Runtime.getRuntime().availableProcessors())
                .expireAfterWrite(24, TimeUnit.HOURS).build().asMap(),
            false);
      }
    };
  }
}
