package hello.aop.pointcut;


import hello.aop.member.MemberServiceImp;
import lombok.extern.slf4j.Slf4j;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.aop.aspectj.AspectJExpressionPointcut;

import java.lang.reflect.Method;

@Slf4j
public class ExecutionTestTwo {
    AspectJExpressionPointcut pointcut = new AspectJExpressionPointcut();
    Method helloMethod;
    Method internal;


    @BeforeEach
    public void init() throws NoSuchMethodException {
        helloMethod = MemberServiceImp.class.getMethod("hello", String.class);
        internal = MemberServiceImp.class.getMethod("internal", String.class);

    }




    @Test
    void argsMatchSix(){
        //public java.lang.String hello.aop.member.MemberServiceImp.hello(java.lang.String)

        pointcut.setExpression(
                "within(hello.aop.member.MemberServiceImp)");
        Assertions.assertThat(pointcut.matches(helloMethod, MemberServiceImp.class)).isTrue();

    }



    @Test
    void argsMatchOne(){
        //public java.lang.String hello.aop.member.MemberServiceImp.hello(java.lang.String)

        pointcut.setExpression(
                "within(hello.aop.member.*Service*)");
        Assertions.assertThat(pointcut.matches(helloMethod, MemberServiceImp.class)).isTrue();

    }


    @Test
    void argsMatchTwo(){
        //public java.lang.String hello.aop.member.MemberServiceImp.hello(java.lang.String)

        pointcut.setExpression(
                "within(hello.aop..*)");
        Assertions.assertThat(pointcut.matches(helloMethod, MemberServiceImp.class)).isTrue();

    }


    @Test
    void argsMatchSuper(){
        //public java.lang.String hello.aop.member.MemberServiceImp.hello(java.lang.String)

        pointcut.setExpression(
                "within(hello.aop.member.MemberService)");
        Assertions.assertThat(pointcut.matches(helloMethod, MemberServiceImp.class)).isFalse();

    }



    @Test
    void argsMatchSuperTwo(){
        //public java.lang.String hello.aop.member.MemberServiceImp.hello(java.lang.String)

        pointcut.setExpression(
                "within(hello.aop.member.MemberService*)");
        Assertions.assertThat(pointcut.matches(helloMethod, MemberServiceImp.class)).isTrue();

    }




}
