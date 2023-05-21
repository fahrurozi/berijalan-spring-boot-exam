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
class RequestClientInterceptor(
    val apiKeyRepository: ApiKeyRepository
): HandlerInterceptor {
    override fun preHandle(request: HttpServletRequest, response: HttpServletResponse, handler: Any): Boolean {
        val otherHeaderStatus = checkOtherHeader(request)
        if(!otherHeaderStatus){
            val json = ObjectMapper().writeValueAsString(
                mapOf("OUT_STAT" to "F", "OUT_MESS" to "You don't have permission to access the API!")
            )
            response.addHeader("X-Content-Type-Options", "nosniff")
            response.addHeader("X-XSS-Protection", "1; mode=block")
            response.addHeader("Strict-Transport-Security", "max-age=31536000; includeSubDomains; preload")
            response.addHeader("X-Frame-Options", "SAMEORIGIN")
            response.writer.write(json)
            response.contentType="application/json"
            response.characterEncoding = "UTF-8"
            response.status = 401
            return false
        }
        return true
    }

    fun checkOtherHeader(request: HttpServletRequest): Boolean{
        val xContentTypeOptions = request.getHeader("X-Content-Type-Options")
        val xXSSProtection = request.getHeader("X-XSS-Protection")
        val strictTransportSecurity = request.getHeader("Strict-Transport-Security")
        val xFrameOptions = request.getHeader("X-Frame-Options")
        if (xContentTypeOptions == null || xXSSProtection == null || strictTransportSecurity == null || xFrameOptions == null){
            return false
        }
        if (xContentTypeOptions != "nosniff" || xXSSProtection != "1; mode=block" || strictTransportSecurity != "max-age=31536000; includeSubDomains; preload" || xFrameOptions != "SAMEORIGIN"){
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