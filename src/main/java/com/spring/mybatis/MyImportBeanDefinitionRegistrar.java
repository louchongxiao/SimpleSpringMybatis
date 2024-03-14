package com.spring.mybatis;

import org.springframework.beans.factory.support.BeanDefinitionRegistry;
import org.springframework.context.annotation.ImportBeanDefinitionRegistrar;
import org.springframework.core.type.AnnotationMetadata;
import org.springframework.core.type.classreading.MetadataReader;
import org.springframework.core.type.classreading.MetadataReaderFactory;
import org.springframework.core.type.filter.TypeFilter;

import java.io.IOException;
import java.util.Map;

/**
 * 该类实现了ImportBeanDefinitionRegistrar接口，用于在Spring上下文中注册Bean定义。主要功能是通过扫描指定
 * 路径下的类，将它们注册为Spring的Bean。具体实现步骤如下：
 *      1.获取MyScan注解的属性值，即要扫描的路径。
 *      2.创建MyScanner实例，并将其与BeanDefinitionRegistry注册表关联。
 *      3.添加一个TypeFilter，该过滤器会包含所有类。
 *      4.调用myScanner.scan(path)方法，开始扫描指定路径下的类并注册为Bean。
 */
public class MyImportBeanDefinitionRegistrar implements ImportBeanDefinitionRegistrar {
    // 实现接口方法
    @Override
    public void registerBeanDefinitions(AnnotationMetadata importingClassMetadata, BeanDefinitionRegistry registry) {
        Map<String, Object> annotationAttributes = importingClassMetadata.getAnnotationAttributes(MyScan.class.getName());
        String path = (String) annotationAttributes.get("value");
        MyScanner myScanner = new MyScanner(registry);
        myScanner.addIncludeFilter(new TypeFilter() {
            @Override
            public boolean match(MetadataReader metadataReader, MetadataReaderFactory metadataReaderFactory) throws IOException {
                return true;
            }
        });

        myScanner.scan(path);
    }
}
