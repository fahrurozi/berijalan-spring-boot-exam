package com.techno.technicaltestauthservice.repository

import com.techno.technicaltestauthservice.entity.ApiKeyEntity
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.jpa.repository.Query

interface ApiKeyRepository:JpaRepository<ApiKeyEntity, Int> {
    @Query(value = "SELECT * FROM tbl_apikey where apikey = ?", nativeQuery = true)
    fun findApiKey(apikey: String?): ApiKeyEntity?
}