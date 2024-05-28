package com.task.logger;

import java.util.HashMap;
import java.util.Map;
import org.springframework.web.util.ContentCachingRequestWrapper;

public abstract class Logging implements LoggingRequest{
  enum Type{
    METHOD,HEADER,PARAMETRS,
    REQUEST_BODY,RESPONSE_BODY, STATUS
  }

  public Map<String,String> getRequest(ContentCachingRequestWrapper requestWrapper){
    Map<String,String> map = new HashMap<>();
    String requestInfo = String.format("%s %s %s",requestWrapper.getRequestId(), requestWrapper.get)
  }
}
