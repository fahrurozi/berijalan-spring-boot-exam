package com.techno.technicaltestauthservice.controller

import com.techno.technicaltestauthservice.dto.response.BaseResponseDto
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.*
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController
import org.springframework.web.client.RestTemplate


@RestController
@RequestMapping("apiservice/unit")
class UnitController {
    private val restTemplate: RestTemplate

    @Autowired
    constructor(restTemplate: RestTemplate) {
        this.restTemplate = restTemplate
    }

    @PostMapping("getBrand")
    fun getBrand(@RequestBody request: String): ResponseEntity<BaseResponseDto<*>> {
        val apiUrl = "http://localhost:8081/unit/getBrand"

        val headers = HttpHeaders()
        headers.contentType = MediaType.APPLICATION_JSON

        val requestEntity = HttpEntity(request, headers)

        val response = restTemplate.exchange(apiUrl, HttpMethod.POST, requestEntity, BaseResponseDto::class.java)

        return response

    }
}