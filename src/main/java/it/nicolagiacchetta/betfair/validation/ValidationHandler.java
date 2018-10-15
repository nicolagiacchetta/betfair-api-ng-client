package it.nicolagiacchetta.betfair.validation;

import it.nicolagiacchetta.betfair.BetfairClientImpl;
import it.nicolagiacchetta.betfair.annotations.Required;

import java.lang.annotation.Annotation;
import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;

import static it.nicolagiacchetta.betfair.utils.StringUtils.isNullOrEmptyString;

public class ValidationHandler implements InvocationHandler {

    private final BetfairClientImpl betfairClient;

    public ValidationHandler(BetfairClientImpl betfairClient) {
        this.betfairClient = betfairClient;
    }

    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        Annotation[][] parameterAnnotations = method.getParameterAnnotations();

        int index = 0;
        for(Object arg : args) {
            for(Annotation[] argAnnotations : parameterAnnotations) {
                for(Annotation annotation : argAnnotations) {
                    if(annotation instanceof Required) {
                        if(isNullOrEmptyString(arg))
                            throw new IllegalArgumentException("Invalid argument '" + method.getParameters()[index].getName() + "': null or empty value not allowed");
                    }
                }
            }
            index++;
        }

        return method.invoke(betfairClient, args);
    }



}
