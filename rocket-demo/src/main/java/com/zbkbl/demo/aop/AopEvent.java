package com.zbkbl.demo.aop;

import com.google.common.collect.Sets;
import com.zbkbl.demo.annotation.Activity;
import com.zbkbl.demo.annotation.FilterLogPoint;
import com.zbkbl.demo.annotation.RepeatActivity;
import com.zbkbl.demo.po.IDResp;
import com.zbkbl.demo.util.RankCacheContext;
import com.zbkbl.demo.util.ThreadLocalContext;
import com.zbkbl.demo.vo.StudentVo;
import com.zbkbl.demo.vo.UserVo;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.ArrayUtils;
import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.math.NumberUtils;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.Signature;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.reflect.CodeSignature;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.context.annotation.EnableAspectJAutoProxy;
import org.springframework.core.annotation.AnnotatedElementUtils;
import org.springframework.core.annotation.Order;
import org.springframework.expression.EvaluationContext;
import org.springframework.expression.Expression;
import org.springframework.expression.common.TemplateParserContext;
import org.springframework.expression.spel.standard.SpelExpressionParser;
import org.springframework.expression.spel.support.StandardEvaluationContext;
import org.springframework.stereotype.Component;

import java.lang.annotation.Annotation;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Map;
import java.util.Objects;
import java.util.Set;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * @author liuyang
 * @description
 * @date 2020/05/19 10:21
 **/

@Slf4j
@Aspect
@Component
@EnableAspectJAutoProxy
public class AopEvent {

    private final SpelExpressionParser parser = new SpelExpressionParser();
    private final TemplateParserContext templateParserContext = new TemplateParserContext();

    /**SPEL表达式标识符*/
    public final String SPEL_FLAG = "#";

//    @Around("execution(* com.zbkbl.demo..*.*(..)) && @annotation(filterLogPoint))")
//    @Around("execution(* com.zbkbl.demo..AopTestService.test(..)) || execution(* com.zbkbl.demo..AopTestService.execute(..))")
    @Around("@annotation(activity)")
    public Object rcdFilterLog(ProceedingJoinPoint jp, Activity activity) throws Throwable {
        log.info("begin aspect rcdFilterLog ....");
        EvaluationContext context = new StandardEvaluationContext();
        Map<String, Object> paramMap = this.getParamNameAndValues(jp, context);

        String activityEnum = activity.activityEnum();
        String logEventEnum = activity.logEventEnum();

        long userId = getUserId(activity.userId(), context);
        String bizId = generateKeyBySpEl(activity.bizId(), context);

        Object o = null;
        try {
            log.info("userId:{}, logEventEnum:{}. activityEnum:{}, bizId:{}, result:{}, param:{}", userId, logEventEnum, activityEnum, bizId, null, paramMap );
            o = jp.proceed();
            log.info("userId:{}, logEventEnum:{}. activityEnum:{}, bizId:{}, result:{}, param:{}", userId, logEventEnum, activityEnum, bizId,  o, (Object[]) null);
            return o;
        } catch (Throwable throwable) {
            log.error("userId:{}, logEventEnum:{}. activityEnum:{}, bizId:{}, paramMap:{}, result:{}, e:{}", userId, logEventEnum, activityEnum, bizId, paramMap, o, throwable);
            log.error("error",throwable);
        }
        return o;
    }

    /**
     * 获取userId
     */
    private long getUserId(String userIdExpression, EvaluationContext context) {
        long userId = 0;

        String userIdStr = generateKeyBySpEl(userIdExpression, context);
        if (StringUtils.isNotEmpty(userIdStr) && NumberUtils.isParsable(userIdStr)) {
            userId = Long.parseLong(userIdStr);
        }

//        //如果获取同参失败，再从同参获取一次
//        if (userId == 0){
//            userId = FlatCommonParamUtil.getUserId();
//        }
        return userId;
    }

    /**
     * 获取形参列表及参数值
     */
    private Map<String, Object> getParamNameAndValues(ProceedingJoinPoint pjp, EvaluationContext context) {
        Map<String, Object> map = new HashMap<>();

        String[] parameterNames = ((CodeSignature) pjp.getSignature()).getParameterNames();
        if (parameterNames == null || parameterNames.length <= 0) {
            return map;
        }

        Object[] values = pjp.getArgs();
        if (values == null || values.length <= 0 || parameterNames.length != values.length) {
            return map;
        }

        for (int i = 0; i < parameterNames.length; i++) {
            map.put(parameterNames[i], values[i]);
            context.setVariable(parameterNames[i], values[i]);
        }
        return map;
    }

    /**
     * 解析Spel表达式，动态获取参数
     */
    private String generateKeyBySpEl(String template, EvaluationContext context) {
        if (StringUtils.isEmpty(template) || !template.contains(SPEL_FLAG)){
            return template;
        }

        try {
            Expression expression = parser.parseExpression(template, templateParserContext);
            return Objects.requireNonNull(expression.getValue(context)).toString();
        }catch (Exception e){
            log.error("generateKeyBySpEl parse template error" ,e );
        }
        return null;
    }
}
