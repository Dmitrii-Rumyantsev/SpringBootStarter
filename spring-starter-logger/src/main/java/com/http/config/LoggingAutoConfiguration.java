package com.http.config;

import com.http.filter.LoggingRequestFilter;
import com.http.logger.LoggingRequest;
import com.http.logger.LoggingRequestImpl;
import org.springframework.boot.autoconfigure.AutoConfiguration;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;

@AutoConfiguration
@EnableConfigurationProperties(LoggingProperties.class)
@ConditionalOnProperty(prefix = "spring-starter-logger", name = "enabled", havingValue = "true", matchIfMissing = true)
public class LoggingAutoConfiguration {

  @Bean
  @ConditionalOnMissingBean
  public LoggingRequest loggingRequest(){
    return new LoggingRequestImpl();
  }

  @Bean
  public LoggingRequestFilter loggingRequestFilter(LoggingRequest loggingRequest){
    return new LoggingRequestFilter(loggingRequest);
  }
}
