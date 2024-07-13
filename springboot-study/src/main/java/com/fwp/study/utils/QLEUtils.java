package com.fwp.study.utils;

import com.ql.util.express.DefaultContext;
import com.ql.util.express.ExpressRunner;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

/**
 * //通过name获取function的定义
 * OperatorBase getFunciton(String name);
 * <p>
 * //通过自定义的Operator来实现类似：fun(a, b, c)
 * void addFunction(String name, OperatorBase op);
 * <p>
 * //fun(a, b, c) 绑定 object.function(a, b, c)对象方法
 * void addFunctionOfServiceMethod(String name, Object aServiceObject, String aFunctionName, Class<?>[] aParameterClassTypes, String errorInfo);
 * <p>
 * //fun(a, b, c) 绑定 Class.function(a, b, c)类方法
 * void addFunctionOfClassMethod(String name, String aClassName, String aFunctionName, Class<?>[] aParameterClassTypes, String errorInfo);
 * <p>
 * //给Class增加或者替换method，同时支持 a.fun(b), fun(a, b) 两种方法调用
 * //比如扩展String.class的isBlank方法:"abc".isBlank()和isBlank("abc")都可以调用
 * void addFunctionAndClassMethod(String name, Class<?> bindingClass, OperatorBase op);
 * <p>
 * ExpressRunner runner = new ExpressRunner();
 * runner.addOperatorWithAlias("如果", "if", null);
 * runner.addOperatorWithAlias("或", "||", null);
 * runner.addOperatorWithAlias("且", "&&", null);
 * runner.addOperatorWithAlias("等于", "==", null);
 * runner.addOperatorWithAlias("大于", ">", null);
 * runner.addOperatorWithAlias("小于", "<", null);
 * runner.addOperatorWithAlias("则", "then", null);
 * runner.addOperatorWithAlias("否则", "else", null);
 * runner.addOperatorWithAlias("返回", "return", null);
 * runner.addFunctionOfClassMethod("获取JSON中的值", Test.class.getName(), "getValue", new String[]{"String"}, null);
 * runner.addFunctionOfClassMethod("字符串等于", Test.class.getName(), "equals", new String[]{"String", "String"}, null);
 * <p>
 * DefaultContext<String, Object> context = new DefaultContext<>();
 * String express = "如果 (获取JSON中的值(\"code\") 等于 1) 则 {返回 true} 否则 {返回 false}";
 * Object execute = runner.execute(express, new DefaultContext<>(), null, true, false);
 * System.out.println(execute);
 * <p>
 * // DefaultContext<String, Object> context = new DefaultContext<>();
 * // String express = "如果 ( 字符串等于( 获取JSON中的值(\"message\"), \"success\") ) 则 {返回 true} 否则 {返回 false}";
 * // Object execute = runner.execute(express, context, null, true, false);
 * // System.out.println(execute);
 */
public class QLEUtils {
    public static void main(String[] args) throws Exception {
        Map<String, Object> context = new HashMap<>();
        context.put("${weight}", 2);
        String expression = "${weight}>reids";
        boolean b = executeExpress(context, expression);
        System.out.println("b = " + b);
    }
    public static boolean executeExpress(Map<String, Object> variables, String expression) throws Exception {
        ExpressRunner runner = new ExpressRunner();
        DefaultContext<String, Object> context = new DefaultContext<>();
        expression = expression.replace(" ", "");
        String[] regexes = expression.split("(==|!=|<=|>=|<|>)");
        Object weight = variables.get(regexes[0]);
        String left = regexes[0].replace("${", "").replace("}", "");
        String right = regexes[1];
        context.put(left, weight);
        expression = expression.replace("${", "").replace("}", "");
        if (weight instanceof String) {
            if (expression.contains("==")) {
                return Objects.equals(weight, right);
            } else {
                return false;
            }
        }
        try {
            return (boolean)runner.execute(expression, context, null, false, false);
        }catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    public static String addSingleQuotes(String str) {
        return "'" + str + "'";
    }

    public static boolean equals(String str1, String str2) {
        return Objects.equals(str1, str2);
    }


}
