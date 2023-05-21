package com.techno.technicaltestauthservice.config

import org.springframework.context.annotation.Configuration
import org.springframework.web.servlet.config.annotation.InterceptorRegistry
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer

@Configuration
class PathMatchingConfigurationAdapter(
    val requestInterceptor: RequestInterceptor,
    val basicAuthInterceptor: BasicAuthInterceptor,
    val requestClientInterceptor: RequestClientInterceptor,
    val bearerInterceptor: BearerInterceptor
): WebMvcConfigurer {
    override fun addInterceptors(registry: InterceptorRegistry) {
        registry.addInterceptor(requestInterceptor).excludePathPatterns("/apiservice/oauth/test")
        registry.addInterceptor(basicAuthInterceptor).excludePathPatterns("/apiservice/oauth/test")
        registry.addInterceptor(requestClientInterceptor).addPathPatterns("/apiservice/unit/getBrand")
        registry.addInterceptor(bearerInterceptor).addPathPatterns("/apiservice/unit/getBrand")
    }

}