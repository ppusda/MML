package com.bbgk.mml.domain.advice

import com.bbgk.mml.domain.exception.MmlException
import org.slf4j.LoggerFactory
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.MethodArgumentNotValidException
import org.springframework.web.bind.annotation.ExceptionHandler
import org.springframework.web.bind.annotation.RestControllerAdvice

@RestControllerAdvice
class ApiControllerAdvice {

    val log = LoggerFactory.getLogger(ApiControllerAdvice::class.java)

    @ExceptionHandler
    fun handleException(e: MmlException): ResponseEntity<String> {
        log.info(e.message, e)
        return ResponseEntity.status(e.httpStatus).body(e.message)
    }

    @ExceptionHandler
    fun handleException(e: MethodArgumentNotValidException): ResponseEntity<String> {
        log.info(e.message, e)

        val fieldErrors = e.bindingResult.fieldErrors
        val message = "[${fieldErrors[0].field} / ${fieldErrors[0].defaultMessage}]"

        return ResponseEntity.badRequest().body(message)
    }

}