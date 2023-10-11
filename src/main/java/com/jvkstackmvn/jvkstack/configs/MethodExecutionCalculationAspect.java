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
import org.springframework.context.annotation.Configuration;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import java.time.LocalDateTime;
import java.time.ZoneOffset;

@Aspect
@Configuration
public class MethodExecutionCalculationAspect {
    private Logger logger = LoggerFactory.getLogger(this.getClass());

    @Autowired
    private SystemLogRepository systemLogRepository;

    @Around("com.jvkstackmvn.jvkstack.configs.CommonJoinPointConfig.businessLayerExecution()")
    public Object aroundService(@NotNull ProceedingJoinPoint joinPoint) throws Throwable {
        HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder.currentRequestAttributes()).getRequest();
        LocalDateTime methodStartTime = LocalDateTime.now();
        Object result = joinPoint.proceed();
        LocalDateTime methodEndTime = LocalDateTime.now();
        systemLogRepository.save(
                SystemLog.builder()
                        .methodStartTime(methodStartTime)
                        .methodEndTime(methodEndTime)
                        .callerIp(HttpUtils.getRequestIP(request))
                        .build()
        );
        logger.info("Time Taken for processing service by {} is {} seconds",
                joinPoint,
                (methodEndTime.toEpochSecond(ZoneOffset.UTC) - methodStartTime.toEpochSecond(ZoneOffset.UTC)));
        return result;
    }

    @Around("com.jvkstackmvn.jvkstack.configs.CommonJoinPointConfig.dataLayerExecution()")
    public Object aroundRepo(ProceedingJoinPoint joinPoint) throws Throwable {
        Object result = joinPoint.proceed();
        return result;
    }
}
