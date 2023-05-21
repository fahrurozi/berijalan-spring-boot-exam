package com.techno.technicaltestauthservice.entity

import javax.persistence.*

@Entity
@Table(name = "tbl_user")
data class UserEntity(
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    val id: Int? = null,

    @Column(unique = true)
    val username: String? = "",

    val password: String? = "",

    var token: String? = ""
)
