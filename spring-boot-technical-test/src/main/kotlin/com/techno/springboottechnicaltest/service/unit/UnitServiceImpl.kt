package com.techno.springboottechnicaltest.service.unit

import com.techno.springboottechnicaltest.dto.request.ReqUnitDto
import com.techno.springboottechnicaltest.dto.response.ResUnitDto
import com.techno.springboottechnicaltest.repository.UnitRepository
import org.slf4j.LoggerFactory
import org.springframework.stereotype.Service
import kotlin.math.log

@Service
class UnitServiceImpl(
    val unitRepository: UnitRepository
): UnitService {
    override fun unitGetAll(): List<ResUnitDto> {
        val unitEntities = unitRepository.findAll()
        val result = mutableListOf<ResUnitDto>()
        for (r in unitEntities){
            result.add(
                ResUnitDto(
                    r.CD_BRAND,
                    r.DESC_BRAND
                )
            )
        }
        return result
    }

    override fun unitGetDataFilter(request: ReqUnitDto): List<ResUnitDto> {
        val log = LoggerFactory.getLogger(this::class.java)
        log.info((request.getListFilterUnitBrand).toString())
        if(request.getListFilterUnitBrand.DESC_BRAND==""){
            log.info((request.getListFilterUnitBrand.DESC_BRAND=="").toString())
            return unitGetAll()
        }
        val unitEntities = unitRepository.findByDescBrand(request.getListFilterUnitBrand.DESC_BRAND)
        val result = mutableListOf<ResUnitDto>()
        for (r in unitEntities){
            result.add(
                ResUnitDto(
                    r.CD_BRAND,
                    r.DESC_BRAND
                )
            )
        }
        return result
    }
}