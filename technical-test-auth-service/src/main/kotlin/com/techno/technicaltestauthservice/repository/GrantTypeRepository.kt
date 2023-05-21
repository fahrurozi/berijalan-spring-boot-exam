package com.techno.technicaltestauthservice.repository

import com.techno.technicaltestauthservice.entity.GrantTypeEntity
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.jpa.repository.Query

interface GrantTypeRepository: JpaRepository<GrantTypeEntity, Int> {
    @Query(value = "SELECT * FROM tbl_grant_type where grant_type = ?", nativeQuery = true)
    fun findGrantType(grandType: String?): GrantTypeEntity?
}