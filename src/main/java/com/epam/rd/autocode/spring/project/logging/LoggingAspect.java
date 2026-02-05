package com.epam.rd.autocode.spring.project.logging;

import com.epam.rd.autocode.spring.project.model.User;
import jakarta.servlet.http.HttpServletRequest;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

@Aspect
@Component
public class LoggingAspect {

    private static final Logger logger = LoggerFactory.getLogger(LoggingAspect.class);

    @Before("execution(* com.epam.rd.autocode.spring.project.service.*.*(..))")
    public void logServiceAccess(JoinPoint joinPoint) {
        logger.info("Service Call: {}", joinPoint.getSignature().getName());
    }

    @Before("execution(* com.epam.rd.autocode.spring.project.controller.CustomErrorController.handleError(..))")
    public void logErrorAccess(JoinPoint joinPoint) {
        Object[] args = joinPoint.getArgs();

        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        String userId = "N/A";
        String username = "Anonymous";

        if (auth != null && auth.isAuthenticated() && !(auth instanceof AnonymousAuthenticationToken)) {
            Object principal = auth.getPrincipal();

            if (principal instanceof User user) {
                userId = String.valueOf(user.getId());
                username = user.getEmail();
            } else if (principal instanceof UserDetails userDetails) {
                username = userDetails.getUsername();
            }
        }

        for (Object arg : args) {
            if (arg instanceof HttpServletRequest request) {
                String ip = request.getRemoteAddr();
                String method = request.getMethod();
                Object errorUri = request.getAttribute("jakarta.servlet.error.request_uri");
                String uri = (errorUri != null) ? errorUri.toString() : request.getRequestURI();

                logger.error("ERROR: User [ID: {}, Email: {}] with IP {} attempted to access {} [Method: {}]",
                        userId, username, ip, uri, method);
            }
        }
    }

    @Before("execution(* com.epam.rd.autocode.spring.project.exception.handler.GlobalExceptionHandler.*(..))")
    public void logExceptionDebug(JoinPoint joinPoint) {
        Object[] args = joinPoint.getArgs();
        String exceptionName = (args.length > 0) ? args[0].getClass().getSimpleName() : "Unknown";
        String message = (args.length > 0 && args[0] instanceof Throwable) ? ((Throwable)args[0]).getMessage() : "No message";

        logger.debug("DEBUG: ExceptionHandler triggered. Method: {}, Error: {}, Message: {}",
                joinPoint.getSignature().getName(),
                exceptionName,
                message);
    }
}