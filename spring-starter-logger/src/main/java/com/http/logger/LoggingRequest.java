package com.http.logger;

import org.springframework.util.StopWatch;
import org.springframework.web.util.ContentCachingRequestWrapper;
import org.springframework.web.util.ContentCachingResponseWrapper;

public interface LoggingRequest {
  void logging(ContentCachingRequestWrapper requestWrapper, ContentCachingResponseWrapper responseWrapper, StopWatch stopWatch);
}
