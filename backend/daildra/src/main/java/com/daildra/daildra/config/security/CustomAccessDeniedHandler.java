//package com.daildra.daildra.config.security;
//
//import java.io.IOException;
//
//import jakarta.servlet.http.HttpServletRequest;
//import jakarta.servlet.http.HttpServletResponse;
//import org.slf4j.Logger;
//import org.slf4j.LoggerFactory;
//import org.springframework.security.access.AccessDeniedException;
//import org.springframework.security.web.access.AccessDeniedHandler;
//import org.springframework.stereotype.Component;
//
///**
// * 권한이 없는 예외가 발생했을 경우 핸들링하는 클래스
// */
//@Component
//public class CustomAccessDeniedHandler implements AccessDeniedHandler {
//
//    private final Logger LOGGER = LoggerFactory.getLogger(CustomAccessDeniedHandler.class);
//
//    @Override
//    public void handle(HttpServletRequest request, HttpServletResponse response,
//                       AccessDeniedException exception) throws IOException {
//        LOGGER.info("[handle] 권한없음으로 401에러 발생");
//        response.sendError(HttpServletResponse.SC_UNAUTHORIZED);
//    }
//}