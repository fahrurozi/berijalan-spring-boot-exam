package com.techno.springboottechnicaltest.repository

import com.techno.springboottechnicaltest.entity.UnitEntity
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.jpa.repository.Query

interface UnitRepository: JpaRepository<UnitEntity, Int>{
    @Query(value = "SELECT * FROM tbl_unit WHERE LOWER(DESC_BRAND) LIKE CONCAT('%', LOWER(?), '%')", nativeQuery = true)
    fun findByDescBrand(descBrand: String?): List<UnitEntity>
}
