package com.bbgk.mml

import com.bbgk.mml.domain.entity.Member
import com.linecorp.kotlinjdsl.support.spring.data.jpa.autoconfigure.KotlinJdslAutoConfiguration
import org.junit.jupiter.api.TestInstance
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest
import org.springframework.context.annotation.Import
import org.springframework.data.domain.PageRequest
import org.springframework.data.domain.Pageable

@DataJpaTest
@Import(KotlinJdslAutoConfiguration::class)
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
abstract class BaseRepositoryTest {

    // Id config
    protected val PLAYLIST_ID = 1L
    protected val MUSIC_ID = 1L
    protected val PLAYLIST_MUSIC_ID = 1L

    // Page Config
    private val PAGE_NUMBER = 0
    private val PAGE_SIZE = 5
    protected val DATA_SIZE = 5
    protected val pageable: Pageable = PageRequest.of(PAGE_NUMBER, PAGE_SIZE)

    // Member Config
    protected val member = Member("testMember", "1234")

}