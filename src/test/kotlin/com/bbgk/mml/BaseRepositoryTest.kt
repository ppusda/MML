package com.bbgk.mml

import com.bbgk.mml.domain.entity.Member
import com.bbgk.mml.domain.entity.Music
import com.bbgk.mml.domain.util.PageUtils
import com.linecorp.kotlinjdsl.support.spring.data.jpa.autoconfigure.KotlinJdslAutoConfiguration
import org.junit.jupiter.api.TestInstance
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest
import org.springframework.context.annotation.Import
import org.springframework.data.domain.Pageable

@DataJpaTest
@Import(KotlinJdslAutoConfiguration::class)
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
abstract class BaseRepositoryTest {

    // Data config
    protected val PLAYLIST_ID = 1L

    protected val MUSIC_ID = 1L
    protected val musics = listOf(
        Music("title1", "artist1", "url1"),
        Music("title2", "artist2", "url2"),
        Music("different", "artist3", "url3")
    )

    protected val PLAYLIST_MUSIC_ID = 1L

    protected val member = Member("member", "1234")

    // Page Config
    protected val PAGE = 0
    protected val pageable: Pageable = PageUtils.getDefaultPageable(PAGE)

}