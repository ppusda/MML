package com.bbgk.mml.domain.exception

enum class MusicListExceptionMessage(val message: String) {
    NOT_EXIST_MUSIC("존재하지 않는 음악입니다."),
    NOT_EXIST_PLAYLIST("존재하지 않는 플레이리스트입니다."),
    NOT_EXIST_PLAYLIST_MUSIC("존재하지 않는 재생목록 내 음악입니다."),
    ALREADY_EXIST_PLAYLIST_MUSIC("이미 재생목록 내 존재하는 음악입니다.")
}