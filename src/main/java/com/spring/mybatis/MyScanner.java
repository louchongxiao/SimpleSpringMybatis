package com.spring.mybatis;

import org.springframework.beans.factory.annotation.AnnotatedBeanDefinition;
import org.springframework.beans.factory.config.BeanDefinition;
import org.springframework.beans.factory.config.BeanDefinitionHolder;
import org.springframework.beans.factory.support.BeanDefinitionRegistry;
import org.springframework.context.annotation.ClassPathBeanDefinitionScanner;

import java.util.Set;

/**
 * 该类是Spring框架中的一个自定义扫描器，继承自ClassPathBeanDefinitionScanner。它的作用是在指定的包下扫描所有的接口，
 * 并将这些接口注册为Spring容器中的Bean。具体实现如下：
 *      1.构造函数MyScanner(BeanDefinitionRegistry registry)：接收一个BeanDefinitionRegistry对象作为参数，用于
 *        注册Bean。
 *      2.方法isCandidateComponent(AnnotatedBeanDefinition beanDefinition)：判断是否为候选组件。该方法重写父类
 *        方法，判断当前扫描到的Bean是否为接口，如果是接口则返回true，否则返回false。
 *      3.方法doScan(String... basePackages)：执行扫描操作。该方法重写父类方法，在指定的包下扫描所有的接口，并将这
 *        些接口注册为Spring容器中的Bean。在注册Bean时，会将Bean的类名设置为MyFactoryBean.class.getName()，并为
 *        Bean的构造器添加一个泛型参数。
 * 综上所述，该类的作用是在指定的包下扫描所有的接口，并将这些接口作为Spring容器中的Bean进行注册。
 */
public class MyScanner extends ClassPathBeanDefinitionScanner {
    public MyScanner(BeanDefinitionRegistry registry) {
        super(registry);
    }

    @Override
    protected boolean isCandidateComponent(AnnotatedBeanDefinition beanDefinition) {
        return beanDefinition.getMetadata().isInterface();
    }

    /**
     * 该函数重写了doScan方法，用于扫描指定包下的所有类，并对扫描到的每个类生成一个BeanDefinitionHolder对象。然后遍历
     * 每个BeanDefinitionHolder对象，获取其BeanDefinition对象，并向其构造器参数值中添加一个泛型参数值，该值为类的全
     * 限定名。接着将BeanDefinition对象的类名设置为MyFactoryBean.class.getName()。最后返回修改后的BeanDefinitionHolder集合。
     */
    @Override
    protected Set<BeanDefinitionHolder> doScan(String... basePackages) {
        Set<BeanDefinitionHolder> beanDefinitionHolders = super.doScan(basePackages);
        for (BeanDefinitionHolder beanDefinitionHolder : beanDefinitionHolders) {
            BeanDefinition beanDefinition = beanDefinitionHolder.getBeanDefinition();
            beanDefinition.getConstructorArgumentValues().addGenericArgumentValue(beanDefinition.getBeanClassName());
            beanDefinition.setBeanClassName(MyFactoryBean.class.getName());
        }
        return beanDefinitionHolders;
    }
}
