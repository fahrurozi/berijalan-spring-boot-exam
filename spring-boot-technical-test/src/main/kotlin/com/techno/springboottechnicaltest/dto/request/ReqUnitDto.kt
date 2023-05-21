package com.techno.springboottechnicaltest.dto.request

import com.fasterxml.jackson.annotation.JsonProperty
import org.springframework.validation.annotation.Validated
import javax.validation.Valid
import javax.validation.constraints.Pattern
import javax.validation.constraints.Size

data class ReqUnitDto (
    @field:Valid
    val getListFilterUnitBrand: FilterUnitBrandDto
)

data class FilterUnitBrandDto(
    @JsonProperty("DESC_BRAND")
    @field:Pattern(regexp = "^[a-zA-Z0-9]*$", message = "DESC_BRAND must be alphanumeric")
    @field:Size(max = 10, message = "DESC_BRAND length must be less than 10 character")
    val DESC_BRAND: String?
)