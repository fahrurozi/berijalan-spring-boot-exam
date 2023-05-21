package com.techno.technicaltestauthservice.config

import com.fasterxml.jackson.databind.ObjectMapper
import com.techno.technicaltestauthservice.dto.response.BaseResponseDto
import com.techno.technicaltestauthservice.dto.response.ErrorDescriptionDto
import com.techno.technicaltestauthservice.repository.ApiKeyRepository
import org.springframework.context.annotation.Bean
import org.springframework.stereotype.Component
import org.springframework.web.servlet.HandlerInterceptor
import org.springframework.web.servlet.ModelAndView
import javax.servlet.http.HttpServletRequest
import javax.servlet.http.HttpServletResponse

@Component
class RequestInterceptor(
    val apiKeyRepository: ApiKeyRepository
): HandlerInterceptor {
    override fun preHandle(request: HttpServletRequest, response: HttpServletResponse, handler: Any): Boolean {
        val apiKey = apiKeyRepository.findApiKey(request.getHeader("API-Key"))
        if(apiKey==null){
            val json = ObjectMapper().writeValueAsString(
                BaseResponseDto(
                "F",
                "invalid_client",
                ErrorDescriptionDto()
            )
            )
            response.writer.write(json)
            response.contentType="application/json"
            response.characterEncoding = "UTF-8"
            response.status = 401
            return false
        }
        return true
    }

    override fun postHandle(
        request: HttpServletRequest,
        response: HttpServletResponse,
        handler: Any,
        modelAndView: ModelAndView?,
    ) {

    }
}