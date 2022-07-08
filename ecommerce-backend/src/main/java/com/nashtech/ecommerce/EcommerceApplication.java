package com.nashtech.ecommerce;

import com.cloudinary.Cloudinary;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import java.util.HashMap;
import java.util.Map;

@SpringBootApplication
public class EcommerceApplication {

    @Value("${clouddinary.cloud-name}")
    public  String cloudName;
    @Value("${clouddinary.api-key}")
    private String apiKey;
    @Value("${clouddinary.api-secret}")
    private String apiSecret;

	public static void main(String[] args) {
		SpringApplication.run(EcommerceApplication.class, args);
	}

	@Bean
	public ModelMapper modelMapper() {
		return new ModelMapper();
	}
	@Bean
    public WebMvcConfigurer corsConfigurer() {
        return new WebMvcConfigurer() {
            @Override
            public void addCorsMappings(CorsRegistry registry) {
                registry.addMapping("/**").allowedOrigins("*").allowedHeaders("*");
            }
        };
    }
    @Bean
    public Cloudinary cloudinary() {
        Map<String, String> valueMap = new HashMap<>();
        valueMap.put("cloud_name", cloudName);
        valueMap.put("api_key", apiKey);
        valueMap.put("api_secret", apiSecret);
        return new Cloudinary(valueMap);
    }
}
