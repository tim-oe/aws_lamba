package org.tec.lambda.config;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.context.annotation.Configuration;

@Configuration
@MapperScan(basePackages = {"org.tec.lambda.mybatis.mapper"})
public class MybatisConfig {
// TODO
}
