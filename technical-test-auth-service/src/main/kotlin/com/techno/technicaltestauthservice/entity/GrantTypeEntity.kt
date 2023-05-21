package com.techno.technicaltestauthservice.entity

import javax.persistence.*

@Entity
@Table(name = "tbl_grant_type")
data class GrantTypeEntity(
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    val id: Int? = null,

    @Column(unique = true)
    val grant_type:String? = ""
)