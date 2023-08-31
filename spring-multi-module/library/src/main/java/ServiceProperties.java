package com.example.multimodule.service;

import org.springframework.boot.context.properties.ConfigurationProperties;

@ConfigurationProperties("service")
public class ServiceProperties {
    
        /**
        * A message for the service.
        */
        private String message = "default message";
    
        public String getMessage() {
            return this.message;
        }
    
        public void setMessage(String message) {
            this.message = message;
        }
}