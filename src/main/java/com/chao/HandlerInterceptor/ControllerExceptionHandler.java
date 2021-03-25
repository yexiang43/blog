package com.chao.HandlerInterceptor;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.annotation.AnnotationUtils;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;

@ControllerAdvice
public class ControllerExceptionHandler {

    private final Logger logger=LoggerFactory.getLogger(this.getClass());

     @ExceptionHandler(Exception.class)
    public ModelAndView ExceptionHandler(HttpServletRequest request ,Exception e) throws Exception {
        if (AnnotationUtils.findAnnotation(e.getClass(),
                ResponseStatus.class) != null) {
            throw e;
        }
      //记录异常
      logger.error("Request Url : {} Exception : {}",request.getRequestURI(),e);
      ModelAndView mv=new ModelAndView();
      mv.addObject("url",request.getRequestURI());
      mv.addObject("Exception",e);
      mv.setViewName("error/error");

      return mv;
    }

}
