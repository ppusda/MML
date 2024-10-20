package com.bbgk.mml.domain.exception

import org.springframework.http.HttpStatus

abstract class MmlException(
        httpStatus: HttpStatus,
        message: String,
): RuntimeException(message) {
    val httpStatus: HttpStatus = httpStatus
}

class MmlBadRequestException(message: String): MmlException(
        httpStatus = HttpStatus.BAD_REQUEST,
        message = message
)

class MmlInternalServerErrorException(message: String): MmlException(
        httpStatus = HttpStatus.INTERNAL_SERVER_ERROR,
        message = message
)