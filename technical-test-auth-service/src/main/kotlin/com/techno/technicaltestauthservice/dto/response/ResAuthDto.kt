package com.techno.technicaltestauthservice.dto.response

data class ResAuthDto(
    val access_token: String?,
    val token_type: String?,
    val expires_in : Int?,
    val scope: String?
)