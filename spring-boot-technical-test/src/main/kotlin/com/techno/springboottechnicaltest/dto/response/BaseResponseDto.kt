package com.techno.springboottechnicaltest.dto.response

import com.fasterxml.jackson.annotation.JsonProperty

data class BaseResponseDto<Any>(
    @field:JsonProperty("OUT_STAT")
    val status:String,
    @field:JsonProperty("OUT_MESS")
    val message:String,
    @field:JsonProperty("OUT_DATA")
    val data: Any?
)
