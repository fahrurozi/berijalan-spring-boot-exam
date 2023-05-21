package com.techno.technicaltestauthservice.config

import com.fasterxml.jackson.databind.ObjectMapper
import com.techno.technicaltestauthservice.dto.response.BaseResponseDto
import com.techno.technicaltestauthservice.dto.response.ErrorDescriptionDto
import com.techno.technicaltestauthservice.util.JWTGenerator
import org.springframework.stereotype.Component
import org.springframework.web.servlet.HandlerInterceptor
import org.springframework.web.servlet.ModelAndView
import javax.servlet.http.HttpServletRequest
import javax.servlet.http.HttpServletResponse

@Component
class BearerInterceptor(

): HandlerInterceptor {
    override fun preHandle(request: HttpServletRequest, response: HttpServletResponse, handler: Any): Boolean {
        val authorizationHeader = request.getHeader("Authorization")
        val token = authorizationHeader?.substringAfter("Bearer ")

        val isExpired = JWTGenerator().isExpired(token.toString())
        if(token == null || isExpired){
            val json = ObjectMapper().writeValueAsString(
                mapOf(
                    "status" to "F",
                    "error" to "Signature Not Valid",
                    "OUT_DATA" to emptyList<String>()
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