package com.http.logger;

import java.util.HashMap;
import java.util.Map;
import lombok.extern.slf4j.Slf4j;
import org.springframework.util.StopWatch;
import org.springframework.web.util.ContentCachingRequestWrapper;
import org.springframework.web.util.ContentCachingResponseWrapper;

@Slf4j
public class LoggingRequestImpl implements LoggingRequest {

  enum Type {
    METHOD, HEADER, PARAMETERS, REQUEST_BODY, RESPONSE_BODY, STATUS
  }

  public Map<String, String> getRequest(ContentCachingRequestWrapper requestWrapper) {
    Map<String, String> map = new HashMap<>();
    map.put(Type.METHOD.name(), requestWrapper.getMethod());
    map.put(Type.PARAMETERS.name(), requestWrapper.getQueryString());

    StringBuilder headers = new StringBuilder();
    requestWrapper.getHeaderNames().asIterator().forEachRemaining(headerName ->
        headers.append(headerName).append(": ").append(requestWrapper.getHeader(headerName)).append("\n"));
    map.put(Type.HEADER.name(), headers.toString());

    return map;
  }

  public Map<String, String> getResponse(ContentCachingResponseWrapper responseWrapper) {
    Map<String, String> map = new HashMap<>();
    map.put(Type.STATUS.name(), Integer.toString(responseWrapper.getStatus()));

    StringBuilder headers = new StringBuilder();
    responseWrapper.getHeaderNames().forEach(headerName ->
        headers.append(headerName).append(": ").append(responseWrapper.getHeader(headerName)).append("\n"));
    map.put(Type.HEADER.name(), headers.toString());

    return map;
  }

  @Override
  public void logging(ContentCachingRequestWrapper requestWrapper,
      ContentCachingResponseWrapper responseWrapper, StopWatch stopWatch) {
    Map<String, String> request = this.getRequest(requestWrapper);
    Map<String, String> response = this.getResponse(responseWrapper);

    log.info("HTTP Request: method={}, parameters={}, headers={}",
        request.get(Type.METHOD.name()),
        request.get(Type.PARAMETERS.name()),
        request.get(Type.HEADER.name()));

    log.info("HTTP Response: status={}, headers={}",
        response.get(Type.STATUS.name()),
        response.get(Type.HEADER.name()));

    stopWatch.stop();
    log.info("Request processing time: {} ms", stopWatch.getTotalTimeMillis());
  }
}
