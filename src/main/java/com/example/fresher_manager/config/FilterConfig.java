package com.example.fresher_manager.config;

import com.example.fresher_manager.util.CustomRequestResponseLoggingFilter;
import org.springframework.context.annotation.Configuration;

@Configuration
public class FilterConfig {
    public CustomRequestResponseLoggingFilter loggingFilter(){
        return new CustomRequestResponseLoggingFilter();
    }
}
