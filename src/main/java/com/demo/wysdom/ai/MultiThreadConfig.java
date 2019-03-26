package com.demo.wysdom.ai;

import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.ImportResource;
import org.springframework.scheduling.annotation.EnableScheduling;

@Configuration
@ImportResource(value = { "classpath:config/application-task.xml" })
@EnableScheduling
public class MultiThreadConfig {

}
