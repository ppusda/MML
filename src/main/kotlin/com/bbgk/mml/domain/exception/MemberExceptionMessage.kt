package com.bbgk.mml.domain.exception

enum class MemberExceptionMessage(val message: String) {
    NOT_EXIST_MEMBER("존재하지 않는 사용자입니다."),
    NOT_VALIDATE_PASSWORD("비밀번호가 일치하지 않습니다.")
}