package com.demo.wysdom.ai;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.core.task.TaskExecutor;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;

import com.demo.wysdom.ai.controller.WikiRestAPI;
import com.demo.wysdom.ai.model.Item;
import com.demo.wysdom.ai.model.SearchSuggestion;

@SpringBootApplication
public class WikiQueryApplication {

	
	@Bean
    public TaskExecutor taskExecutor() {
        ThreadPoolTaskExecutor executor = new ThreadPoolTaskExecutor();
        executor.setCorePoolSize(5);
        executor.setMaxPoolSize(10);
        executor.setQueueCapacity(25);
        executor.setKeepAliveSeconds(60);
        return executor;
    }
	
	public static void main(String[] args) {
		SpringApplication.run(WikiQueryApplication.class, args);
		SearchSuggestion result = new WikiRestAPI().openSearch("China,,,");
		
		for(Item page : result.getSections().getItems()) {
			System.out.println("Item : " + page.getText());
			System.out.println("Link : " + page.getUrl());
		}
	}

}
