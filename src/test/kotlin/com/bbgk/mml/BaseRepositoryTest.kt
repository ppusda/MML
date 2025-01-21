package com.bbgk.mml

import com.linecorp.kotlinjdsl.support.spring.data.jpa.autoconfigure.KotlinJdslAutoConfiguration
import org.junit.jupiter.api.TestInstance
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest
import org.springframework.context.annotation.Import

@DataJpaTest
@Import(KotlinJdslAutoConfiguration::class)
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
abstract class BaseRepositoryTest: TestConstants()