package com.omarze.aspect;


import com.julianduru.security.Auth;
import com.omarze.entities.BackOfficeUser;
import com.omarze.security.AuthContext;
import com.omarze.security.AuthenticationWrapper;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.stereotype.Component;

/**
 * created by julian
 */
@Aspect
@Component
public class AuthInjectAspect {



    @Around("execution(* com.omarze..*(..)) && @annotation(com.omarze.security.annotation.IsBackOfficeUser)")
    public Object processBackOfficeUserProtectedRepositoryCall(ProceedingJoinPoint joinPoint) throws Throwable {
        if (
            !AuthenticationWrapper
                .of(Auth.getContext())
                .hasAuthority(BackOfficeUser.ROLE_ID)) {

            Auth.setContext(AuthContext.backOfficeUser());
        }

        return joinPoint.proceed();
    }


}

