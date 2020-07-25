package com.omarze.aspect;


import com.omarze.annotation.PostTrigger;
import com.omarze.event.ServiceEvent;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.stereotype.Component;

import java.lang.reflect.Method;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

/**
 * created by julian
 */
@Slf4j
@Aspect
@Component
@RequiredArgsConstructor
public class ServiceEventPostProcessor {


    private final ApplicationEventPublisher eventPublisher;



    @Around("execution(* com.omarze.services..*(..)) && @annotation(com.omarze.annotation.PostTrigger)")
    public Object processCommandExecution(ProceedingJoinPoint joinPoint) throws Throwable {
        Object result = joinPoint.proceed();
        Optional<List<ServiceEvent>> possibleEvents = retrieveEvent(joinPoint);

        if (possibleEvents.isPresent()) {
            List<ServiceEvent> events = possibleEvents.get();
            events.forEach(e -> {
                e.setData(result);
                eventPublisher.publishEvent(e);
            });
        }

        return result;
    }


    private Optional<List<ServiceEvent>> retrieveEvent(ProceedingJoinPoint joinPoint) throws Exception {
        MethodSignature signature = (MethodSignature)joinPoint.getSignature();
        Method method = signature.getMethod();

        PostTrigger postTrigger = method.getAnnotation(PostTrigger.class);
        if (postTrigger == null) {
            return Optional.empty();
        }

        Class<? extends ServiceEvent>[] triggerEventClasses = postTrigger.value();
        List<ServiceEvent> eventList = Arrays
            .asList(triggerEventClasses)
            .stream().map(c -> {
                try {
                    return c.newInstance();
                } catch (Exception e) {
                    log.error(e.getMessage(), e);
                    return null;
                }
            })
            .filter(c -> c != null)
            .collect(Collectors.toList());

        return Optional.of(eventList);
    }



}
