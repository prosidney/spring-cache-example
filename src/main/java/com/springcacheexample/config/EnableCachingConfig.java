package com.springcacheexample.config;

import java.io.File;

import org.springframework.cache.annotation.EnableCaching;
import org.springframework.cache.ehcache.EhCacheCacheManager;
import org.springframework.cache.ehcache.EhCacheManagerFactoryBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.FileSystemResource;

import com.springcacheexample.service.Service;

@Configuration
@EnableCaching
public class EnableCachingConfig {
	
	
	@Bean
	public Service anotherService() {
		return new Service();
	}
	
	@Bean
    public EhCacheCacheManager cacheManager() {
		EhCacheCacheManager ehCacheCacheManager = new EhCacheCacheManager();
		ehCacheCacheManager.setCacheManager(ehCache().getObject());
		
		
		return ehCacheCacheManager;
    }
	
	@Bean
	public EhCacheManagerFactoryBean ehCache(){
		EhCacheManagerFactoryBean cacheManagerFactoryBean = new EhCacheManagerFactoryBean();
		cacheManagerFactoryBean.setShared(true);
		
		//InputStream resourceAsStream = EnableCachingConfig.class.getResourceAsStream("/ehcache.xml");
		File file = new File("C:\\Users\\Acer\\git\\spring-cache-example\\src\\main\\resources\\ehcache.xml");
		cacheManagerFactoryBean.setConfigLocation(new FileSystemResource(file));

		return cacheManagerFactoryBean;
	}
	
	
/*	@Bean
    public CacheManager cacheManager() {
        SimpleCacheManager cacheManager = new SimpleCacheManager();
        cacheManager.setCaches(Arrays.asList(new ConcurrentMapCache("test")));
        return cacheManager;
    }	*/
}
