package com.techno.technicaltestauthservice.controller

import com.techno.technicaltestauthservice.dto.request.ReqAuthDto
import com.techno.technicaltestauthservice.dto.response.BaseResponseDto
import com.techno.technicaltestauthservice.dto.response.ErrorDescriptionDto
import com.techno.technicaltestauthservice.repository.GrantTypeRepository
import com.techno.technicaltestauthservice.repository.UserRepository
import com.techno.technicaltestauthservice.service.AuthService
import com.techno.technicaltestauthservice.util.JWTGenerator
import org.slf4j.LoggerFactory
import org.springframework.http.HttpHeaders
import org.springframework.http.ResponseEntity
import org.springframework.util.Base64Utils
import org.springframework.web.bind.annotation.*
import javax.servlet.http.HttpServletRequest

@RestController
@RequestMapping("/apiservice/oauth")
class OauthController(
    val authService: AuthService,
    val grantTypeRepository: GrantTypeRepository,
    val userRepository: UserRepository
) {
    val log = LoggerFactory.getLogger(this::class.java)
    @PostMapping("/token")
    fun token(@RequestBody request: ReqAuthDto, headers: HttpServletRequest): ResponseEntity<BaseResponseDto<Any>>{
        val authHeader = headers.getHeader(HttpHeaders.AUTHORIZATION)
        log.info(authHeader.toString())
        val credentials = String(Base64Utils.decodeFromString(authHeader.substring(6))).split(":")
        val username = credentials[0]
        val grantType = grantTypeRepository.findGrantType(request.grant_type)
        log.info(grantType.toString())
        log.info(request.grant_type)
        if(grantType==null){
            return ResponseEntity.ok(
                BaseResponseDto(
                    "F",
                    "invalid_client",
                    ErrorDescriptionDto()
                )
            )

        }
        val data = authService.getToken(request, username)

        return ResponseEntity.ok(
            BaseResponseDto(
                "T",
                "Success",
                data
            )
        )
    }

    @GetMapping("test")
    fun testToken(@RequestParam("token") token: String): String{
        val isExpired = JWTGenerator(userRepository).isExpired(token)

        return isExpired.toString()
    }
}