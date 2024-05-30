package com.http.filter;

import static org.mockito.Mockito.any;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

import com.http.logger.LoggingRequest;
import jakarta.servlet.FilterChain;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.mock.web.MockHttpServletRequest;
import org.springframework.mock.web.MockHttpServletResponse;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.util.ContentCachingRequestWrapper;
import org.springframework.web.util.ContentCachingResponseWrapper;


class LoggingRequestFilterTest {

  @Mock
  private LoggingRequest loggingRequest;

  @InjectMocks
  private LoggingRequestFilter loggingRequestFilter;

  @BeforeEach
  void setUp() {
    MockitoAnnotations.openMocks(this);
    MockMvcBuilders.standaloneSetup()
        .addFilter(loggingRequestFilter)
        .build();
  }

  @Test
  void testDoFilterInternal() throws Exception {
    FilterChain filterChain = mock(FilterChain.class);
    MockHttpServletRequest request = new MockHttpServletRequest();
    MockHttpServletResponse response = new MockHttpServletResponse();
    loggingRequestFilter.doFilterInternal(request, response, filterChain);
    verify(filterChain, times(1)).doFilter(any(ContentCachingRequestWrapper.class),
        any(ContentCachingResponseWrapper.class));
    verify(loggingRequest, times(1)).logging(any(ContentCachingRequestWrapper.class),
        any(ContentCachingResponseWrapper.class), any());
  }

  @Test
  void testDoFilterInternalWithRequestBody() throws Exception {
    FilterChain filterChain = mock(FilterChain.class);
    MockHttpServletRequest request = new MockHttpServletRequest();
    request.setContent("test body".getBytes());
    MockHttpServletResponse response = new MockHttpServletResponse();
    loggingRequestFilter.doFilterInternal(request, response, filterChain);
    verify(filterChain, times(1)).doFilter(any(ContentCachingRequestWrapper.class),
        any(ContentCachingResponseWrapper.class));
    verify(loggingRequest, times(1)).logging(any(ContentCachingRequestWrapper.class),
        any(ContentCachingResponseWrapper.class), any());
  }

  @Test
  void testDoFilterInternalWithResponseContent() throws Exception {
    FilterChain filterChain = mock(FilterChain.class);
    MockHttpServletRequest request = new MockHttpServletRequest();
    MockHttpServletResponse response = new MockHttpServletResponse();
    ContentCachingResponseWrapper responseWrapper = new ContentCachingResponseWrapper(response);
    responseWrapper.getWriter().write("test response body");
    loggingRequestFilter.doFilterInternal(request, responseWrapper, filterChain);
    verify(filterChain, times(1)).doFilter(any(ContentCachingRequestWrapper.class),
        any(ContentCachingResponseWrapper.class));
    verify(loggingRequest, times(1)).logging(any(ContentCachingRequestWrapper.class),
        any(ContentCachingResponseWrapper.class), any());
  }
}