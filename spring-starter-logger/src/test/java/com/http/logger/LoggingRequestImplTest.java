package com.http.logger;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.Collections;
import java.util.List;
import java.util.Map;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.util.StopWatch;
import org.springframework.web.util.ContentCachingRequestWrapper;
import org.springframework.web.util.ContentCachingResponseWrapper;

class LoggingRequestImplTest {

  @InjectMocks
  private LoggingRequestImpl loggingRequestImpl;

  @Mock
  private ContentCachingRequestWrapper requestWrapper;

  @Mock
  private ContentCachingResponseWrapper responseWrapper;

  @Mock
  private StopWatch stopWatch;

  @BeforeEach
  void setUp() {
    MockitoAnnotations.openMocks(this);
  }

  @Test
  void testGetRequest() {
    when(requestWrapper.getMethod()).thenReturn("GET");
    when(requestWrapper.getContentAsString()).thenReturn("param=value");
    when(requestWrapper.getHeaderNames()).thenReturn(Collections.enumeration(
        List.of("Content-Type", "Authorization")));

    when(requestWrapper.getHeader("Content-Type")).thenReturn("application/json");
    when(requestWrapper.getHeader("Authorization")).thenReturn("Bearer token");

    Map<String, String> requestMap = loggingRequestImpl.getRequest(requestWrapper);

    assertEquals("GET", requestMap.get("METHOD"));
    assertEquals("param=value", requestMap.get("PARAMETERS"));
    assertEquals("Content-Type: application/json\nAuthorization: Bearer token\n",
        requestMap.get("HEADER"));
  }

  @Test
  void testGetResponse() {
    when(responseWrapper.getStatus()).thenReturn(200);
    when(responseWrapper.getHeaderNames()).thenReturn(List.of("Content-Type", "Authorization"));

    when(responseWrapper.getHeader("Content-Type")).thenReturn("application/json");
    when(responseWrapper.getHeader("Authorization")).thenReturn("Bearer token");

    Map<String, String> responseMap = loggingRequestImpl.getResponse(responseWrapper);

    assertEquals("200", responseMap.get("STATUS"));
    assertEquals("Content-Type: application/json\nAuthorization: Bearer token\n",
        responseMap.get("HEADER"));
  }

  @Test
  void testLogging() {
    when(requestWrapper.getMethod()).thenReturn("GET");
    when(requestWrapper.getContentAsString()).thenReturn("param=value");
    when(requestWrapper.getHeaderNames()).thenReturn(
        Collections.enumeration(List.of("Content-Type", "Authorization")));

    when(requestWrapper.getHeader("Content-Type")).thenReturn("application/json");
    when(requestWrapper.getHeader("Authorization")).thenReturn("Bearer token");

    when(responseWrapper.getStatus()).thenReturn(200);
    when(responseWrapper.getHeaderNames()).thenReturn(List.of("Content-Type", "Authorization"));

    when(responseWrapper.getHeader("Content-Type")).thenReturn("application/json");
    when(responseWrapper.getHeader("Authorization")).thenReturn("Bearer token");

    doNothing().when(stopWatch).start();
    doNothing().when(stopWatch).stop();
    when(stopWatch.getTotalTimeMillis()).thenReturn(100L);

    loggingRequestImpl.logging(requestWrapper, responseWrapper, stopWatch);

    verify(requestWrapper, times(1)).getMethod();
    verify(requestWrapper, times(1)).getContentAsString();
    verify(requestWrapper, times(1)).getHeaderNames();
    verify(responseWrapper, times(1)).getStatus();
    verify(responseWrapper, times(1)).getHeaderNames();
    verify(stopWatch, times(1)).stop();
  }
}