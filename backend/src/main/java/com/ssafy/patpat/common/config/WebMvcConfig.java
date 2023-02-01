package com.ssafy.patpat.common.config;

//import org.springframework.context.annotation.Configuration;
//import org.springframework.web.servlet.config.annotation.CorsRegistry;
//import org.springframework.web.servlet.config.annotation.EnableWebMvc;
//import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
//
//@Configuration
//@EnableWebMvc
//public class WebMvcConfig implements WebMvcConfigurer {
//
//    @Override
//    public void addCorsMappings(CorsRegistry registry){
//        registry.addMapping("/**")
//                .allowedOrigins("*") // 청을 허용할 출처를 명시, 전체 허용 (가능하다면 목록을 작성한다.
//                .allowedHeaders("*") // 어떤 헤더들을 허용할 것인지
//                .allowedMethods("*")
////                .allowedOrigins("http://localhost:3000",
////                                "http://tableminpark.iptime.org:3000",
////                                "http://tableminpark.iptime.org:80",
////                                "https://tableminpark.iptime.org:3000",
////                                "https://tableminpark.iptime.org:80")
//                .allowedMethods("OPTIONS", "GET", "POST", "PUT", "DELETE");
//    }
//}