package com.karoliskursevicius.myfavartist.main;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.MediaType;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.web.client.RestTemplate;

import java.util.List;

/**
 * @author Karolis Kursevičius
 */
@Configuration
public class RestTemplateConfig {
    @Bean
    public RestTemplate iTunesRestTemplate() {
        MappingJackson2HttpMessageConverter converter = new MappingJackson2HttpMessageConverter();
        converter.setSupportedMediaTypes(List.of(MediaType.ALL));
        RestTemplate restTemplate = new RestTemplate();
        restTemplate.setMessageConverters(List.of(converter));
        return restTemplate;
    }
}
