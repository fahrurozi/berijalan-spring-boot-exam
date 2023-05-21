package com.techno.technicaltestauthservice.config

import com.fasterxml.jackson.databind.ObjectMapper
import com.techno.technicaltestauthservice.dto.response.BaseResponseDto
import com.techno.technicaltestauthservice.dto.response.ErrorDescriptionDto
import com.techno.technicaltestauthservice.repository.UserRepository
import org.springframework.http.HttpHeaders
import org.springframework.stereotype.Component
import org.springframework.util.Base64Utils
import org.springframework.web.servlet.HandlerInterceptor
import org.springframework.web.servlet.ModelAndView
import java.lang.Exception
import javax.servlet.http.HttpServletRequest
import javax.servlet.http.HttpServletResponse

@Component
class BasicAuthInterceptor(
    val userRepository: UserRepository
): HandlerInterceptor {
    override fun preHandle(request: HttpServletRequest, response: HttpServletResponse, handler: Any): Boolean {
        val authHeader = request.getHeader(HttpHeaders.AUTHORIZATION)
        if (authHeader != null && authHeader.startsWith("Basic ")) {
            val credentials = String(Base64Utils.decodeFromString(authHeader.substring(6))).split(":")
            val username = credentials[0]
            val password = credentials[1]

            if(!isUserValid(username, password)){
                val json = ObjectMapper().writeValueAsString(BaseResponseDto(
                    "F",
                    "invalid_client",
                    ErrorDescriptionDto()
                ))
                response.writer.write(json)
                response.contentType = "application/json"
                response.characterEncoding = "UTF-8"
                response.status = 401
                return false
            }
        }
        return true
    }

    private fun isUserValid(username: String, password: String): Boolean {
        try{
            val user = userRepository.findUsername(username)
            if(user==null){
                return false
            }
            if(!user!!.password.equals(password)){
                return false
            }
            return true
        }catch (e: Exception){
            return false
        }
    }

    override fun postHandle(
        request: HttpServletRequest,
        response: HttpServletResponse,
        handler: Any,
        modelAndView: ModelAndView?,
    ) {

    }

}