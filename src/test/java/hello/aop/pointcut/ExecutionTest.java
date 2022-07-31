package hello.aop.pointcut;


import hello.aop.member.MemberServiceImp;
import lombok.extern.slf4j.Slf4j;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.aop.aspectj.AspectJExpressionPointcut;

import java.lang.reflect.Method;
import static org.assertj.core.api.Assertions.*;

@Slf4j
public class ExecutionTest {
    AspectJExpressionPointcut pointcut = new AspectJExpressionPointcut();
    Method helloMethod;
    Method internal;


    @BeforeEach
    public void init() throws NoSuchMethodException {
        helloMethod = MemberServiceImp.class.getMethod("hello", String.class);
        internal = MemberServiceImp.class.getMethod("internal", String.class);

    }



    @Test
    void printMethod(){
        //public java.lang.String hello.aop.member.MemberServiceImp.hello(java.lang.String)
        log.info("helloMethod = {}", helloMethod);
    }


    @Test
    void exactMatch(){
        //public java.lang.String hello.aop.member.MemberServiceImp.hello(java.lang.String)
        pointcut.setExpression("execution(public String hello.aop.member.MemberServiceImp.hello(String))");
        Assertions.assertThat(pointcut.matches(helloMethod, MemberServiceImp.class)).isTrue();

    }


    @Test
    void allMatch(){
        pointcut.setExpression(
                "execution(* *(..))");
        Assertions.assertThat(pointcut.matches(helloMethod, MemberServiceImp.class)).isTrue();

    }


    @Test
    void nameMatch(){
        pointcut.setExpression(
                "execution(* hello(..))");
        Assertions.assertThat(pointcut.matches(helloMethod, MemberServiceImp.class)).isTrue();

    }

    @Test
    void nameMatchTwo(){
        pointcut.setExpression(
                "execution(* *el*(..))");
        Assertions.assertThat(pointcut.matches(helloMethod, MemberServiceImp.class)).isTrue();

    }


    @Test
    void nameMatchFail(){
        pointcut.setExpression(
                "execution(* nono(..))");
        Assertions.assertThat(pointcut.matches(helloMethod, MemberServiceImp.class)).isTrue();

    }

    @Test
    void packageMatch(){
        pointcut.setExpression(
                "execution(* hello.aop.member.MemberServiceImp.hello(..))");
        Assertions.assertThat(pointcut.matches(helloMethod, MemberServiceImp.class)).isTrue();
    }


    @Test
    void packageMatchTwo(){
        pointcut.setExpression(
                "execution(* hello.aop.member.*.*(..))");
        Assertions.assertThat(pointcut.matches(helloMethod, MemberServiceImp.class)).isTrue();
    }



    @Test
    void packageMatchThree(){
        pointcut.setExpression(
                "execution(* hello.aop..*.*(..))");
        Assertions.assertThat(pointcut.matches(helloMethod, MemberServiceImp.class)).isTrue();
    }



    @Test
    void typeExactMatch(){
        pointcut.setExpression(
                "execution(* hello.aop.member.MemberServiceImp.*(..))");
        Assertions.assertThat(pointcut.matches(helloMethod, MemberServiceImp.class)).isTrue();
    }


    @Test
    void typeExactMatchSuper(){
        pointcut.setExpression(
                "execution(* hello.aop.member.MemberService.*(..))");
        Assertions.assertThat(pointcut.matches(helloMethod, MemberServiceImp.class)).isTrue();
    }


    @Test
    void typeExactMatchSuperInternal(){
        pointcut.setExpression(
                "execution(* hello.aop.member.MemberService.*(..))");
        Assertions.assertThat(pointcut.matches(internal, MemberServiceImp.class)).isFalse();
    }


    @Test
    void typeExactMatchSuperInternalSecond(){
        pointcut.setExpression(
                "execution(* hello.aop.member.MemberServiceImp.*(..))");
        Assertions.assertThat(pointcut.matches(internal, MemberServiceImp.class)).isTrue();
    }


    //String
    //(string)
    @Test
    void argsMatch(){
        pointcut.setExpression(
                "execution(* *(String))");
        Assertions.assertThat(pointcut.matches(helloMethod, MemberServiceImp.class)).isTrue();

    }

    @Test
    void argsMatchVoid(){
        pointcut.setExpression(
                "execution(* *())");
        Assertions.assertThat(pointcut.matches(helloMethod, MemberServiceImp.class)).isFalse();

    }

    @Test
    void argsMatchTwo(){
        pointcut.setExpression(
                "execution(* *(*))");
        Assertions.assertThat(pointcut.matches(helloMethod, MemberServiceImp.class)).isTrue();

    }


    @Test
    void argsMatchThree(){
        pointcut.setExpression(
                "execution(* *(*))");
        Assertions.assertThat(pointcut.matches(helloMethod, MemberServiceImp.class)).isTrue();

    }



    @Test
    void argsMatchFour(){
        pointcut.setExpression(
                "execution(* *(String, ..))");
        Assertions.assertThat(pointcut.matches(helloMethod, MemberServiceImp.class)).isTrue();

    }



    @Test
    void argsMatchFive(){
        pointcut.setExpression(
                "execution(* *(String, String))");
        Assertions.assertThat(pointcut.matches(helloMethod, MemberServiceImp.class)).isFalse()
        ;

    }


    @Test
    void argsMatchSix(){
        pointcut.setExpression(
                "execution(* *(String, *))");
        Assertions.assertThat(pointcut.matches(helloMethod, MemberServiceImp.class)).isFalse();

    }




}
