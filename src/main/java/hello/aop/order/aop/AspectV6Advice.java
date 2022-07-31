package hello.aop.order.aop;

import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.*;


@Slf4j
@Aspect
public class AspectV6Advice {


    @Before("hello.aop.order.aop.PointCuts.OrderAndService()")
    public void doBefore(JoinPoint joinPoint) {
        log.info("[before] {}", joinPoint.getSignature());
    }

    @AfterReturning(value = "hello.aop.order.aop.PointCuts.OrderAndService()",
            returning = "result")
    public void doReturn(JoinPoint joinPoint, Object result) {
        log.info("[return] {} , return ={}", joinPoint.getSignature(),
                result);
    }


    @AfterReturning(value = "hello.aop.order.aop.PointCuts.allOrder()",
            returning = "result")
    public void doReturnSecond(JoinPoint joinPoint, Integer result) {
        log.info("[return2] {} , return ={}", joinPoint.getSignature(),
                result);
    }

    @AfterThrowing(value = "hello.aop.order.aop.PointCuts.OrderAndService()",
            throwing = "ex")
    public void doThrow(JoinPoint joinPoint, Exception ex) {
        log.info("[Exception] {}", ex);
    }

    @After(value = "hello.aop.order.aop.PointCuts.OrderAndService()")
    public void doFinally(JoinPoint joinPoint) {
        log.info("[After] {} ", joinPoint.getSignature());
    }



}
