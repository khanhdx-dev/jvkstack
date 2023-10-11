package com.jvkstackmvn.jvkstack.configs;

import org.aspectj.lang.annotation.Pointcut;

public class CommonJoinPointConfig {

    @Pointcut("execution(* com.jvkstackmvn.jvkstack.repositories.*.*(..))")
    public void dataLayerExecution() {}

    @Pointcut("execution(* com.jvkstackmvn.jvkstack.services.*.*(..))")
    public void businessLayerExecution() {}
}
