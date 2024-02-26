package ru.lakeevda.lesson8.aspect;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.stereotype.Component;

import java.util.Arrays;

@Aspect
@Component
public class UserAction {

    @Pointcut("@annotation(TrackUserAction)")
    public void trackUserAction() {}

    @After("trackUserAction()")
    public void afterCallingTrackedMethod(JoinPoint joinPoint) {
        MethodSignature signature = (MethodSignature) joinPoint.getSignature();
        String className = signature.getDeclaringType().getSimpleName();
        String methodName = signature.getMethod().getName();
        String args = Arrays.toString(joinPoint.getArgs());

        System.out.println("Класс: " + className + ". Метод: " + methodName + ". Аргументы: " + args);
    }
}
