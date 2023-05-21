package com.techno.springboottechnicaltest.exception

import com.techno.springboottechnicaltest.dto.response.BaseResponseDto
import org.springframework.core.Ordered
import org.springframework.core.annotation.Order
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.MethodArgumentNotValidException
import org.springframework.web.bind.annotation.ControllerAdvice
import org.springframework.web.bind.annotation.ExceptionHandler

@ControllerAdvice
@Order(Ordered.HIGHEST_PRECEDENCE)
class ErrorHandler {

    @ExceptionHandler(MethodArgumentNotValidException::class)
    fun handleArgumentNotValidException(exception: MethodArgumentNotValidException): ResponseEntity<Any>{
        val errors = mutableListOf<String>()
        exception.bindingResult.fieldErrors.forEach{
            errors.add(it.defaultMessage!!)
        }
        val result = BaseResponseDto("F", "Invalid Input",errors )
        return ResponseEntity.badRequest().body(result)
    }

    @ExceptionHandler(CustomException::class)
    fun handleCustomException(exception: RuntimeException): ResponseEntity<Any>{
        val result = BaseResponseDto("F", "Something went wrong",exception.message )
        return ResponseEntity.badRequest().body(result)
    }
}