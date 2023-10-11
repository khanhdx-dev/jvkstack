package com.jvkstackmvn.jvkstack.configs;//package com.demo.jvkstack.configs;
//
//import com.demo.jvkstack.domains.entities.EntityA;
//import org.aspectj.lang.JoinPoint;
//import org.aspectj.lang.annotation.After;
//import org.aspectj.lang.annotation.AfterReturning;
//import org.aspectj.lang.annotation.Aspect;
//import org.slf4j.Logger;
//import org.slf4j.LoggerFactory;
//import org.springframework.context.annotation.Configuration;
//
//@Aspect
//@Configuration
//public class AfterAopAspect {
//    private Logger logger = LoggerFactory.getLogger(this.getClass());
//
//    @AfterReturning(value = "execution(* com.demo.jvkstack.configs.*(..))", returning = "result")
//    public void afterReturning(JoinPoint joinPoint, EntityA result){
//        logger.info("{} returned with value {}", joinPoint, result);
//    }
//
//    @After(value = "execution(* com.demo.jvkstack.*(..))")
//    public void after(JoinPoint joinPoint) {
//        logger.info("after execution of {}", joinPoint);
//    }
//}
