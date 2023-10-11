package com.jvkstackmvn.jvkstack.configs;//package com.demo.jvkstack.configs;
//
//import org.aspectj.lang.JoinPoint;
//import org.aspectj.lang.annotation.Aspect;
//import org.aspectj.lang.annotation.Before;
//import org.slf4j.Logger;
//import org.slf4j.LoggerFactory;
//import org.springframework.context.annotation.Configuration;
//
//@Aspect
//@Configuration
//public class UserAccessAspect {
//    private Logger logger = LoggerFactory.getLogger(this.getClass());
//
//
//    @Before("execution(* com.demo.jvkstack.*(..))")
//    public void before(JoinPoint joinPoint){
//        logger.info("Check for user access");
//        logger.info("Allow user {}", joinPoint);
//    }
//}
