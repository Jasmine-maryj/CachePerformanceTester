package com.dev.apigateway.config;

import org.springframework.cache.Cache;
import org.springframework.cache.CacheManager;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.Collection;

@Configuration
public class CacheConfig {

    @Bean
    public CacheManager cacheManager(){
        return new CacheManager() {
            @Override
            public Cache getCache(String name) {
                return null;
            }

            @Override
            public Collection<String> getCacheNames() {
                return null;
            }
        };
    }
}
