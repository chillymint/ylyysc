package com.nb.yygh.hosp;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.stereotype.Component;

/**
 * @author yunfu.ye
 * @date 2022/11/17 14:06
 * @desciption:
 */
@SpringBootApplication
@ComponentScan(basePackages = {"com.nb"})
public class ServiceHospApplication {
    public static void main(String[] args) {
        SpringApplication.run(ServiceHospApplication.class, args);
    }
}
