package com.techno.springboottechnicaltest.controller

import com.techno.springboottechnicaltest.dto.request.ReqUnitDto
import com.techno.springboottechnicaltest.dto.response.BaseResponseDto
import com.techno.springboottechnicaltest.dto.response.ResUnitDto
import com.techno.springboottechnicaltest.service.unit.UnitService
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController
import javax.validation.Valid

@RestController
@RequestMapping("unit")
class UnitController(
    val unitService : UnitService
) {
    @PostMapping("getBrand")
    fun getBrand(@Valid @RequestBody request: ReqUnitDto):ResponseEntity<BaseResponseDto<List<ResUnitDto>>>{
        val result = unitService.unitGetDataFilter(request)
        return ResponseEntity.ok(
            BaseResponseDto(
                "T",
                "Success",
                result
            )
        )
    }
}