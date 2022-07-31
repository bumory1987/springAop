package hello.aop.pointcut;

import hello.aop.member.MemberServiceImp;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.aop.aspectj.AspectJExpressionPointcut;

import java.lang.reflect.Method;
import static org.assertj.core.api.Assertions.*;


public class ArgTest {

    Method helloMethod;
    Method internal;


    @BeforeEach
    public void init() throws NoSuchMethodException {
        helloMethod = MemberServiceImp.class.getMethod("hello", String.class);
        internal = MemberServiceImp.class.getMethod("internal", String.class);

    }

    private AspectJExpressionPointcut pointcut(String expression){
        AspectJExpressionPointcut pointcut = new AspectJExpressionPointcut();
        pointcut.setExpression(expression);
        return pointcut;
    }

    @Test
    void args(){
        assertThat(pointcut("args(String)")
                .matches(helloMethod, MemberServiceImp.class)).isTrue();

        assertThat(pointcut("args(Object)")
                .matches(helloMethod, MemberServiceImp.class)).isTrue();

        assertThat(pointcut("args()")
                .matches(helloMethod, MemberServiceImp.class)).isTrue();

        assertThat(pointcut("args(..)")
                .matches(helloMethod, MemberServiceImp.class)).isTrue();

        assertThat(pointcut("args(*)")
                .matches(helloMethod, MemberServiceImp.class)).isTrue();

        assertThat(pointcut("args(String, ..)")
                .matches(helloMethod, MemberServiceImp.class)).isTrue();
    }


    @Test
    void argsVsExecution(){
        assertThat(pointcut("args(String)")
                .matches(helloMethod, MemberServiceImp.class)).isTrue();

        assertThat(pointcut("args(java.io.Serializable)")
                .matches(helloMethod, MemberServiceImp.class)).isTrue();

        assertThat(pointcut("args(Object)")
                .matches(helloMethod, MemberServiceImp.class)).isTrue();


        assertThat(pointcut("execution(* *(String))")
                .matches(helloMethod, MemberServiceImp.class)).isTrue();


        assertThat(pointcut("execution(* *(java.io.Serializable))")
                .matches(helloMethod, MemberServiceImp.class)).isFalse();


        assertThat(pointcut("execution(* *(Object))")
                .matches(helloMethod, MemberServiceImp.class)).isFalse();

    }



}
