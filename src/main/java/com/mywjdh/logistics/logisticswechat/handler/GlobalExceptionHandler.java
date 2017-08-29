package com.mywjdh.logistics.logisticswechat.handler;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

//@ControllerAdvice(annotations=RestController.class)
//@ControllerAdvice(basePackages={"com.xxx","com.ooo"})
@ControllerAdvice
public class GlobalExceptionHandler {
    @ExceptionHandler(RuntimeException.class)
    //    @ExceptionHandler(value={RuntimeException.class,MyRuntimeException.class})
    //    @ExceptionHandler//处理所有异常
    @ResponseBody //在返回自定义相应类的情况下必须有，这是@ControllerAdvice注解的规定
    public ExceptionResponse exceptionHandler(RuntimeException e, HttpServletResponse response) {
    	ExceptionResponse resp = new ExceptionResponse();
        resp.setCode(300);
        resp.setMsg("exception-Handler");
        resp.setError(e.getMessage());
        return resp;
    }
}