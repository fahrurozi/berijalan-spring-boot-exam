package com.techno.springboottechnicaltest.service.unit

import com.techno.springboottechnicaltest.dto.request.ReqUnitDto
import com.techno.springboottechnicaltest.dto.response.ResUnitDto

interface UnitService {
    fun unitGetAll(): List<ResUnitDto>
    fun unitGetDataFilter(request: ReqUnitDto): List<ResUnitDto>
}