package com.spring.mybatis;

import org.springframework.context.annotation.Import;

import java.lang.annotation.Retention;
import java.lang.annotation.Target;
/**
 * 这是一个Java注解，名称为MyScan。
 *   ·@Retention(RUNTIME)表示该注解的生命周期为运行时，即在运行时可以通过反射获取到该注解。
 *   ·@Target(TYPE)表示该注解可以被应用到类型（类、接口等）上。
 *   ·@Import(MyImportBeanDefinitionRegistrar.class)表示引入MyImportBeanDefinitionRegistrar类作为Spring Bean的定义注册器。
 *   ·value()是该注解的一个属性，其默认值为空字符串。在使用该注解时，可以通过value属性指定一些值。
 * 这个注解的作用是，在使用Spring框架时，通过在配置类上添加@MyScan注解，可以实现自动扫描并注册特定包下的所有组件为Spring Bean。
 * 其中，注解的value属性可以指定要扫描的包路径。
 */

@Retention(java.lang.annotation.RetentionPolicy.RUNTIME)
@Target(java.lang.annotation.ElementType.TYPE)
@Import(MyImportBeanDefinitionRegistrar.class)
public @interface MyScan {
    String value() default "";
}
