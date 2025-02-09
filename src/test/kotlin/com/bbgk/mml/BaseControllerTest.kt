package com.bbgk.mml

import com.fasterxml.jackson.databind.ObjectMapper
import org.junit.jupiter.api.extension.ExtendWith
import org.mockito.junit.jupiter.MockitoExtension
import org.springframework.http.MediaType
import org.springframework.test.web.servlet.MockMvc
import org.springframework.test.web.servlet.MvcResult
import org.springframework.test.web.servlet.ResultMatcher
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders
import org.springframework.test.web.servlet.result.MockMvcResultHandlers


@ExtendWith(MockitoExtension::class)
abstract class BaseControllerTest(
        private val mockMvc: MockMvc
): TestConstants() {

    private val objectMapper = ObjectMapper()

    protected fun performGet(uri: String, status: ResultMatcher): MvcResult {
        return mockMvc
                .perform(MockMvcRequestBuilders.get(uri))
                .andDo(MockMvcResultHandlers.print())
                .andExpect(status)
                .andReturn()
    }

    protected fun performPost(uri: String, form: Any, status: ResultMatcher): MvcResult {
        return mockMvc
                .perform(MockMvcRequestBuilders.post(uri)
                        .content(objectMapper.writeValueAsString(form))
                        .contentType(MediaType.APPLICATION_JSON))
                .andDo(MockMvcResultHandlers.print())
                .andExpect(status)
                .andReturn()
    }


    protected fun performPostWithId(uri: String, form: Any, name: String, id: Long, status: ResultMatcher): MvcResult {
        return mockMvc
                .perform(MockMvcRequestBuilders.post(uri)
                        .content(objectMapper.writeValueAsString(form))
                        .contentType(MediaType.APPLICATION_JSON)
                        .param(name, id.toString()))
                .andDo(MockMvcResultHandlers.print())
                .andExpect(status)
                .andReturn()
    }

    protected fun performPut(uri: String, form: Any, status: ResultMatcher): MvcResult {
        return mockMvc
                .perform(MockMvcRequestBuilders.put(uri)
                        .content(objectMapper.writeValueAsString(form))
                        .contentType(MediaType.APPLICATION_JSON))
                .andDo(MockMvcResultHandlers.print())
                .andExpect(status)
                .andReturn()
    }

    protected fun performPatch(uri: String, form: Any, status: ResultMatcher): MvcResult {
        return mockMvc
                .perform(MockMvcRequestBuilders.patch(uri)
                        .content(objectMapper.writeValueAsString(form))
                        .contentType(MediaType.APPLICATION_JSON))
                .andDo(MockMvcResultHandlers.print())
                .andExpect(status)
                .andReturn()
    }

    protected fun performDelete(uri: String, status: ResultMatcher): MvcResult {
        return mockMvc
                .perform(MockMvcRequestBuilders.delete(uri))
                .andDo(MockMvcResultHandlers.print())
                .andExpect(status)
                .andReturn()
    }

    protected fun performDeleteWithId(uri: String, name: String, id: Long, status: ResultMatcher): MvcResult {
        return mockMvc
                .perform(MockMvcRequestBuilders.delete(uri)
                        .param(name, id.toString()))
                .andDo(MockMvcResultHandlers.print())
                .andExpect(status)
                .andReturn()
    }

}

/**
 * 아래 Base(Controller, Service) Test를 만든 이유를 기재함.
 *
 * - 접근 제어자 설정 및 명명 규칙 준수
 * 같은 패키지 내 사용 => protected, 내부에서만 사용 => private 를 사용함.
 * 상수 => 대문자, 스네이크케이스 / 객체 인스턴스 => 소문자 를 사용함.
 *
 * - abstract class 사용 이유와 mockMvc 생성자 주입
 * abstract class는 open class로 간주 됨.
 * Kotlin의 기본 클래스는 final이기 때문에 필드 주입을 사용할 수 없음.
 * 고로 abstract를 사용하고, 생성자 주입을 통해 mockMvc를 사용하도록 구현 함.
 *
 * - 만들게 된 이유?
 * 위에서는 테스트의 기본 기능을 제공하는 용도로 사용됨을 명시하기 위해서와 자식 클래스에서 특정 메서드를 수정하여 사용하기 위해서
 */