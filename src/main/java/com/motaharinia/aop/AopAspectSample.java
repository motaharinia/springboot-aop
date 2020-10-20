package com.motaharinia.aop;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;

//https://docs.spring.io/spring/docs/2.0.x/reference/aop.html
//https://www.baeldung.com/spring-aop-pointcut-tutorial

@Component
@Aspect
public class AopAspectSample {


    @Pointcut("@annotation(org.springframework.web.bind.annotation.PostMapping)")
    public void controllerPostMethods() {};

    @Pointcut("@annotation(org.springframework.web.bind.annotation.GetMapping)")
    public void controllerGetMethods() {};

    @Pointcut("@annotation(org.springframework.web.bind.annotation.PutMapping)")
    public void controllerPutMethods() {};

    @Pointcut("@annotation(org.springframework.web.bind.annotation.DeleteMapping)")
    public void controllerDeleteMethods() {};

    @Pointcut("controllerPostMethods() || controllerGetMethods() || controllerPutMethods() || controllerDeleteMethods()")
    public void controllerMethods() {}

    @Pointcut("execution(* *..find*(Integer,..))")
    public void firstIntegerParamFindMethods() {}



    @Before("controllerMethods()")
    public void logControllerMethods(JoinPoint jp) {
        String className = jp.getSignature().getDeclaringType().getName();
        String methodName = jp.getSignature().getName();
        System.out.println("Before controllerMethods class.method:"+ className+ "."+ methodName);
    }

    @AfterReturning(value = "firstIntegerParamFindMethods()", returning = "model")
    public void logFirstIntegerParamFindMethods(JoinPoint jp, Object model) throws Throwable {
        String className = jp.getSignature().getDeclaringType().getSimpleName();
        String methodName = jp.getSignature().getName();
        System.out.println("AfterReturning logFirstIntegerParamFindMethods class.method:"+ className+ "."+ methodName + " model:"+model);
    }
}
