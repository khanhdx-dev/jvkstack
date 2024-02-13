package com.jvkstackmvn.jvkstack.configs;

import com.jvkstackmvn.jvkstack.commons.utilities.HttpUtils;
import com.jvkstackmvn.jvkstack.domains.entities.SystemLog;
import com.jvkstackmvn.jvkstack.repositories.SystemLogRepository;
import jakarta.servlet.http.HttpServletRequest;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.jetbrains.annotations.NotNull;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.context.request.*;

import java.time.LocalDateTime;

@Aspect
@Configuration
public class MethodExecutionCalculationAspect {
    private Logger logger = LoggerFactory.getLogger(this.getClass());

    @Autowired
    private SystemLogRepository systemLogRepository;

    private String getRemoteAddress() {
        RequestAttributes attributes = RequestContextHolder.getRequestAttributes();
        if (attributes != null) {
            HttpServletRequest request = ((ServletRequestAttributes) attributes).getRequest();
            return request.getRemoteAddr();
        }
        return null;
    }

    @Around("com.jvkstackmvn.jvkstack.configs.CommonJoinPointConfig.businessLayerExecution()")
    public Object aroundService(@NotNull ProceedingJoinPoint joinPoint) throws Throwable {
        LocalDateTime methodStartTime = LocalDateTime.now();
        long start = System.currentTimeMillis();
        Object result = joinPoint.proceed();
        long end = System.currentTimeMillis();
        LocalDateTime methodEndTime = LocalDateTime.now();
        systemLogRepository.save(
                SystemLog.builder()
                        .methodStartTime(methodStartTime)
                        .methodEndTime(methodEndTime)
                        .callerIp(getRemoteAddress())
                        .build()
        );
        logger.info("### Time Taken for processing service by {} is {} milliSeconds",
                joinPoint,
                end - start);
        return result;
    }

    @Around("com.jvkstackmvn.jvkstack.configs.CommonJoinPointConfig.dataLayerExecution()")
    public Object aroundRepo(ProceedingJoinPoint joinPoint) throws Throwable {
        Object result = joinPoint.proceed();
        return result;
    }
}
