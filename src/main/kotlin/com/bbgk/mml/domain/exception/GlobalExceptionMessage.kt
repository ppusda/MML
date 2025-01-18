package com.bbgk.mml.domain.exception

enum class GlobalExceptionMessage(val message: String) {
    REQUIRED_ARGUMENT("필수 값입니다."),
    CANNOT_INITIALIZE_UTILS("유틸 클래스는 인스턴스화 할 수 없습니다.")
}