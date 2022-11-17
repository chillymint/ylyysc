package com.nb.yygh.hosp.config;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.context.annotation.Configuration;

/**
 * @author yunfu.ye
 * @date 2022/11/17 15:10
 * @desciption:
 */
@Configuration
@MapperScan("com.nb.yygh.hosp.mapper")
public class HospConfig {
}
