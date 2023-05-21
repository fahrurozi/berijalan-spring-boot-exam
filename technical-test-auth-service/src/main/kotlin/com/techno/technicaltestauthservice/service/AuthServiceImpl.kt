package com.techno.technicaltestauthservice.service

import com.fasterxml.jackson.databind.ObjectMapper
import com.techno.technicaltestauthservice.dto.request.ReqAuthDto
import com.techno.technicaltestauthservice.dto.response.ResAuthDto
import com.techno.technicaltestauthservice.repository.GrantTypeRepository
import com.techno.technicaltestauthservice.repository.UserRepository
import com.techno.technicaltestauthservice.util.JWTGenerator
import org.springframework.stereotype.Service
import org.springframework.util.Base64Utils
import javax.servlet.http.HttpServletRequest

@Service
class AuthServiceImpl(
    val userRepository: UserRepository,
): AuthService {
    override fun getToken(request: ReqAuthDto, username: String): ResAuthDto {
        val user = userRepository.findUsername(username)
        val jwt = JWTGenerator().createJWT(user?.id!!,username)
        return ResAuthDto(
            access_token = jwt[0],
            token_type = "Bearer",
            expires_in = jwt[1].toInt(),
            scope = "resource.WRITE openid resource.READ"
        )
    }

}