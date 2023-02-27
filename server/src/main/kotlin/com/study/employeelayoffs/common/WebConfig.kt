package com.study.employeelayoffs.common

import org.springframework.context.annotation.Configuration
import org.springframework.web.servlet.config.annotation.InterceptorRegistry
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer

@Configuration
class WebConfig : WebMvcConfigurer {

    override fun addInterceptors(registry: InterceptorRegistry) {
        registry.addInterceptor(JwtCookieInterceptor()).addPathPatterns("/api/v1/person/**").addPathPatterns("api/v1/**");
    }
}
