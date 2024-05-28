package com.http.filter;

import com.http.logger.LoggingRequest;
import com.http.logger.LoggingRequestImpl;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.StopWatch;
import org.springframework.web.filter.OncePerRequestFilter;
import org.springframework.web.util.ContentCachingRequestWrapper;
import org.springframework.web.util.ContentCachingResponseWrapper;

public class LoggingRequestFilter extends OncePerRequestFilter {

  private final LoggingRequest loggingRequest;

  @Autowired
  public LoggingRequestFilter(LoggingRequest loggingRequest){
    this.loggingRequest = loggingRequest;
  }
  @Override
  protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response,
      FilterChain filterChain) throws ServletException, IOException {
    ContentCachingRequestWrapper requestWrapper = new ContentCachingRequestWrapper(request);
    ContentCachingResponseWrapper responseWrapper = new ContentCachingResponseWrapper(response);
    StopWatch stopWatch = new StopWatch();
    stopWatch.start();

    try {
      filterChain.doFilter(requestWrapper, responseWrapper);
    } finally {
      loggingRequest.logging(requestWrapper, responseWrapper, stopWatch);
      responseWrapper.copyBodyToResponse();
    }
  }
}
