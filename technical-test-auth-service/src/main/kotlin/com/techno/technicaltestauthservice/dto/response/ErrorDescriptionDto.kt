package com.techno.technicaltestauthservice.dto.response

import com.fasterxml.jackson.annotation.JsonProperty

data class ErrorDescriptionDto(
    @field:JsonProperty("error_description")
    val errorDescription:String = "Client authentication failed (e.g. unknown client, no client authentication included, or unsupported authentication method). The authorization server MAY return an HTTP 401 (Unauthorized) status code to indicate which HTTP authentication schemes are unsuported."
)
