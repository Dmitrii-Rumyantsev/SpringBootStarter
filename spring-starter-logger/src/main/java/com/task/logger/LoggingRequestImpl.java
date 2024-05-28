package com.task.logger;

import java.util.HashMap;
import java.util.Map;
import jdk.jfr.internal.util.StopWatch;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.springframework.web.util.ContentCachingRequestWrapper;
import org.springframework.web.util.ContentCachingResponseWrapper;

@Slf4j
public class LoggingRequestImpl implements LoggingRequest{


  @Override
  public void logging(ContentCachingRequestWrapper requestWrapper,
      ContentCachingResponseWrapper responseWrapper, StopWatch stopWatch) {
    Map<String, String> request = this.getRe  ;

  }
}
