package com.techno.technicaltestauthservice.repository

import com.techno.technicaltestauthservice.entity.UserEntity
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.jpa.repository.Modifying
import org.springframework.data.jpa.repository.Query

interface UserRepository: JpaRepository<UserEntity, Int> {
    @Query(value = "SELECT * FROM tbl_user WHERE username = ?", nativeQuery = true)
    fun findUsername(username: String?): UserEntity?
}