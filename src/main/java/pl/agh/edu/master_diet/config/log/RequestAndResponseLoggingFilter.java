package pl.agh.edu.master_diet.config.log;

import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.filter.OncePerRequestFilter;
import org.springframework.web.util.ContentCachingRequestWrapper;
import org.springframework.web.util.ContentCachingResponseWrapper;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.stream.Stream;

@Slf4j
public class RequestAndResponseLoggingFilter extends OncePerRequestFilter {

    private static final String START_END_INDICATOR = "====================";
    private static final int MAX_REQUEST_ID = 10000;

    private static final List<MediaType> VISIBLE_TYPES = Arrays.asList(
            MediaType.valueOf("text/*"),
            MediaType.APPLICATION_FORM_URLENCODED,
            MediaType.APPLICATION_JSON,
            MediaType.APPLICATION_XML,
            MediaType.valueOf("application/*+json"),
            MediaType.valueOf("application/*+xml"),
            MediaType.MULTIPART_FORM_DATA
    );

    @Override
    protected void doFilterInternal(HttpServletRequest request,
                                    HttpServletResponse response,
                                    FilterChain filterChain) throws ServletException, IOException {
        if (isAsyncDispatch(request)) {
            filterChain.doFilter(request, response);
        } else {
            doFilterWrapped(wrapRequest(request), wrapResponse(response), filterChain);
        }
    }

    protected void doFilterWrapped(ContentCachingRequestWrapper request,
                                   ContentCachingResponseWrapper response,
                                   FilterChain filterChain) throws ServletException, IOException {
        int requestLoggingId = Double.valueOf(Math.random() * MAX_REQUEST_ID).intValue();

        try {
            beforeRequest(request, response, requestLoggingId);
            filterChain.doFilter(request, response);
        } finally {
            afterRequest(request, response, requestLoggingId);
            response.copyBodyToResponse();
        }
    }

    protected void beforeRequest(ContentCachingRequestWrapper request,
                                 ContentCachingResponseWrapper response, int requestLoggingId) {
        final String prefix = createPrefix(requestLoggingId);

        if (log.isInfoEnabled()) {
            logRequestHeader(request, prefix);
        }
    }

    protected void afterRequest(ContentCachingRequestWrapper request,
                                ContentCachingResponseWrapper response, int requestLoggingId) {
        final String prefix = createPrefix(requestLoggingId);

        if (log.isInfoEnabled()) {
            logRequestBody(request, prefix);
            logResponse(response, prefix);
        }
    }

    private static String createPrefix(int requestLoggingId) {
        return String.format("[%s]", requestLoggingId);
    }

    private static void logRequestHeader(ContentCachingRequestWrapper request, String prefix) {
        String queryString = request.getQueryString();
        log.info("{} {} REQUEST HEADER START {}", prefix, START_END_INDICATOR, START_END_INDICATOR);
        log.info("{} IP ADDRESS: {} ", prefix, request.getRemoteAddr());

        if (queryString == null) {
            log.info("{} {} {}", prefix, request.getMethod(), request.getRequestURI());
        } else {
            log.info("{} {} {}?{}", prefix, request.getMethod(), request.getRequestURI(), queryString);
        }

        Collections.list(request.getHeaderNames())
                .forEach(headerName ->
                        Collections.list(request.getHeaders(headerName))
                                .forEach(headerValue ->
                                        log.info("{} {}: {}", prefix, headerName, headerValue)));

        log.info("{} {} REQUEST HEADER END  {} ", prefix, START_END_INDICATOR, START_END_INDICATOR);
    }

    private static void logRequestBody(ContentCachingRequestWrapper request, String prefix) {
        byte[] content = request.getContentAsByteArray();
        if (content.length > 0) {
            log.info("{} {} REQUEST BODY START  {} ", prefix, START_END_INDICATOR, START_END_INDICATOR);
            logContent(content, request.getContentType(), request.getCharacterEncoding(), prefix);
            log.info("{} {} REQUEST BODY END    {} ", prefix, START_END_INDICATOR, START_END_INDICATOR);
        }
    }

    private static void logResponse(ContentCachingResponseWrapper response, String prefix) {
        int status = response.getStatus();

        log.info("{} {} RESPONSE HEADER START  {} ", prefix, START_END_INDICATOR, START_END_INDICATOR);
        log.info("{} {} {}", prefix, status, HttpStatus.valueOf(status).getReasonPhrase());
        response.getHeaderNames().forEach(headerName ->
                response.getHeaders(headerName).forEach(headerValue ->
                        log.info("{} {}: {}", prefix, headerName, headerValue)));
        log.info("{} {} RESPONSE HEADER END    {} ", prefix, START_END_INDICATOR, START_END_INDICATOR);

        byte[] content = response.getContentAsByteArray();
        if (content.length > 0) {
            log.info("{} {} RESPONSE BODY START  {} ", prefix, START_END_INDICATOR, START_END_INDICATOR);
            logContent(content, response.getContentType(), response.getCharacterEncoding(), prefix);
            log.info("{} {} RESPONSE BODY END    {} ", prefix, START_END_INDICATOR, START_END_INDICATOR);
        }
    }

    private static void logContent(byte[] content, String contentType, String contentEncoding, String prefix) {
        MediaType mediaType = MediaType.valueOf(contentType);
        boolean visible = VISIBLE_TYPES.stream().anyMatch(visibleType -> visibleType.includes(mediaType));
        if (visible) {
            try {
                String contentString = new String(content, contentEncoding);
                Stream.of(contentString.split("\r\n|\r|\n"))
                        .forEach(line -> log.info("{}", line));
            } catch (UnsupportedEncodingException e) {
                log.info("{} [{} bytes content]", prefix, content.length);
            }
        } else {
            log.info("{} [{} bytes content]", prefix, content.length);
        }
    }

    private static ContentCachingRequestWrapper wrapRequest(HttpServletRequest request) {
        if (request instanceof ContentCachingRequestWrapper) {
            return (ContentCachingRequestWrapper) request;
        } else {
            return new ContentCachingRequestWrapper(request);
        }
    }

    private static ContentCachingResponseWrapper wrapResponse(HttpServletResponse response) {
        if (response instanceof ContentCachingResponseWrapper) {
            return (ContentCachingResponseWrapper) response;
        } else {
            return new ContentCachingResponseWrapper(response);
        }
    }


}
