package com.techno.technicaltestauthservice.service

import com.techno.technicaltestauthservice.dto.request.ReqAuthDto
import com.techno.technicaltestauthservice.dto.response.ResAuthDto
import javax.servlet.http.HttpServletRequest

interface AuthService {
    fun getToken(request: ReqAuthDto, username: String): ResAuthDto
}