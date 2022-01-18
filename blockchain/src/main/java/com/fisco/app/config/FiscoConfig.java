package com.fisco.app.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.ImportResource;

/**
 * @Description: 加载配置文件
 */
@ImportResource(locations = "classpath:applicationContext.xml")
@Configuration
public class FiscoConfig {
}
