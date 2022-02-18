package com.zbkbl.demo.aop;

import com.zbkbl.demo.annotation.Activity;
import lombok.extern.slf4j.Slf4j;
import org.aopalliance.intercept.MethodInterceptor;
import org.aopalliance.intercept.MethodInvocation;
import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.math.NumberUtils;
import org.springframework.core.annotation.AnnotatedElementUtils;
import org.springframework.expression.EvaluationContext;
import org.springframework.expression.Expression;
import org.springframework.expression.common.TemplateParserContext;
import org.springframework.expression.spel.standard.SpelExpressionParser;
import org.springframework.expression.spel.support.StandardEvaluationContext;
import org.springframework.stereotype.Component;

import java.lang.reflect.Parameter;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

/**
 * @Title: InterfaceAopEvent
 * @Description:
 * @author: LiuYang
 * @date: 2022/2/18 4:11 下午
 */
@Slf4j
@Component("interfaceAopEvent")
public class InterfaceAopEvent implements MethodInterceptor {

    private final SpelExpressionParser parser = new SpelExpressionParser();
    private final TemplateParserContext templateParserContext = new TemplateParserContext();

    /**SPEL表达式标识符*/
    public final String SPEL_FLAG = "#";

    @Override
    public Object invoke(MethodInvocation invocation) throws Throwable {
        // 当前方法获取注解
        Activity activity = invocation.getMethod().getAnnotation(Activity.class);
        if (activity == null){
            // 向上(父类或父接口)获取注解
            activity = AnnotatedElementUtils.findMergedAnnotation(invocation.getMethod(), Activity.class);
        }
        if (activity == null){
            log.warn("don't find any annotation which named activity!");
            return invocation.proceed();
        }
        EvaluationContext context = new StandardEvaluationContext();
        Map<String, Object> paramMap = this.getParamNameAndValues(invocation, context);

        String activityEnum = activity.activityEnum();
        String logEventEnum = activity.logEventEnum();

        long userId = getUserId(activity.userId(), context);
        String bizId = generateKeyBySpEl(activity.bizId(), context);

        Object o = null;
        try {
            o = invocation.proceed();
            log.info("userId:{}, logEventEnum:{}. activityEnum:{}, bizId:{}, result:{}, param:{}", userId, logEventEnum, activityEnum, bizId,  o, paramMap);
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
    private Map<String, Object> getParamNameAndValues(MethodInvocation pjp, EvaluationContext context) {
        Map<String, Object> map = new HashMap<>();

        String[] parameterNames = Arrays.stream(pjp.getMethod().getParameters()).map(Parameter::getName).toArray(String[]::new);
        if (parameterNames.length <= 0) {
            return map;
        }

        Object[] values = pjp.getArguments();
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