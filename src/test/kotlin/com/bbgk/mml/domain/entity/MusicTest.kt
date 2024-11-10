package com.bbgk.mml.domain.entity

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

class MusicTest {

    @Test
    fun `음악 수정 시 비어있더라도 그 값으로 업데이트 됩니다`() {
        // given
        val music = Music("title", "artist", "url")

        // when
        music.update("updatedTitle","", "")

        // then
        assertThat(music.title).isEqualTo("updatedTitle")
        assertThat(music.artist).isEqualTo("")
        assertThat(music.url).isEqualTo("")
    }
}