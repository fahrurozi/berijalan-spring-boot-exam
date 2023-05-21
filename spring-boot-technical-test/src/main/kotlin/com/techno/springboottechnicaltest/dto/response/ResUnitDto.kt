package com.techno.springboottechnicaltest.dto.response

import com.fasterxml.jackson.annotation.JsonProperty

data class ResUnitDto(
    @field:JsonProperty("CD_BRAND")
    val cdBrand: String?,
    @field:JsonProperty("DESC_BRAND")
    val descBrand: String?
)
