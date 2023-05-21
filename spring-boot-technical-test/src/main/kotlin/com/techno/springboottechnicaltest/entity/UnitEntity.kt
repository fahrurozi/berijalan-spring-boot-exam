package com.techno.springboottechnicaltest.entity

import javax.persistence.*

@Entity
@Table(name="tbl_unit")
data class UnitEntity (
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(length = 3)
    var CD_BRAND: String? = null,

    @Column(unique = true)
    val DESC_BRAND: String? = ""
){
    init {
        // Initialize id with leading zeros if not already set
        CD_BRAND?.let {
            if (it.length < 3) {
                CD_BRAND = it.padStart(3, '0')
            }
        }
    }
}



