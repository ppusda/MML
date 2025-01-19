package com.bbgk.mml.domain.repository

import com.bbgk.mml.BaseRepositoryTest
import com.bbgk.mml.domain.entity.Music
import org.assertj.core.api.Assertions
import org.junit.jupiter.api.BeforeAll
import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired

class CustomMusicRepositoryImplTest: BaseRepositoryTest() {

    @Autowired
    private lateinit var musicRepository: MusicRepository


    @BeforeAll
    fun beforeAll() {
        val musics = mutableListOf(
            Music("title1", "artist1", "url1"),
            Music("title2", "artist2", "url2")
        )
        musicRepository.saveAll(musics)
    }

    @Test
    fun `제목 혹은 아티스트 내 키워드가 포함되어 있는 음악 목록을 반환한다`() {
        // given - beforeAll

        // when
        val findMusics = musicRepository.findMusicsByKeyword("title")

        // then
        for (findMusic in findMusics) {
            Assertions.assertThat(findMusic.title).contains("title")
        }
    }

}