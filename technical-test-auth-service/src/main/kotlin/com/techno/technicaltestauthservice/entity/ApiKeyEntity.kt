package com.techno.technicaltestauthservice.entity

import javax.persistence.*

@Entity
@Table(name="tbl_apikey")
data class ApiKeyEntity(
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    val id: Int? = null,

    @Column(unique = true)
    val apikey: String? = "",
)
