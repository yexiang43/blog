package com.chao.Aspect;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;

/**
 * 用切面 记录日志
 */
@Aspect
@Component
public class logAspect {

    private final Logger logger=LoggerFactory.getLogger(this.getClass());

     @Pointcut("execution(* com.chao.Controller.*.*(..))")
    public void log(){}

    @Before("log()")
    public void dofore(JoinPoint joinPoint)
    {
        //获得request
        ServletRequestAttributes attributes= (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
        HttpServletRequest request=attributes.getRequest();

        //获得要记录的内容
        String url=request.getRequestURI();
        String ip=request.getRemoteAddr();
        String classMethod= joinPoint.getSignature().getDeclaringTypeName()+"."+joinPoint.getSignature().getName();
        Object[] args=joinPoint.getArgs();
        RequestLog requestLog=new RequestLog(url,ip,classMethod,args);

        logger.info("Request : {}" ,requestLog);
    }

    @After("log()")
    public void doAfter()
    {
       // logger.info("------doAfter------");
    }

    @AfterReturning(returning = "obj",pointcut = "log()")
    public void Return(Object obj)
    {
       logger.info("Return : {}",obj);
    }

    @Data
    @AllArgsConstructor
    @NoArgsConstructor
    public class RequestLog
    {
        private String url;
        private String ip;
        private String classMethod;
        private Object[] args;
    }
}
