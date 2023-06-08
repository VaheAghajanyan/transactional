package org.example;

import org.example.config.ProductConfig;
import org.example.service.ProductService;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import java.util.Arrays;

/**
 * Hello world!
 */
public class App {
    public static void main(String[] args) {
        ConfigurableApplicationContext applicationContext = new AnnotationConfigApplicationContext(ProductConfig.class);
        applicationContext.registerShutdownHook();

        ProductService productService = applicationContext.getBean("productService", ProductService.class);
        productService.saveProduct();

        applicationContext.close();
    }
}
