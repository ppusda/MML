package com.bbgk.mml.controller

import com.bbgk.mml.domain.entity.Member
import com.fasterxml.jackson.databind.ObjectMapper
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.http.MediaType
import org.springframework.test.web.servlet.MockMvc
import org.springframework.test.web.servlet.MvcResult
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders
import org.springframework.test.web.servlet.result.MockMvcResultHandlers

@SpringBootTest
@AutoConfigureMockMvc
abstract class BaseControllerTest {

    @Autowired
    protected lateinit var mockMvc: MockMvc

    val objectMapper = ObjectMapper()
    val member = Member("Test", "test")
    var uid = 1L


    protected fun performGet(uri: String): MvcResult {
        return mockMvc
                .perform(MockMvcRequestBuilders.get(uri))
                .andDo(MockMvcResultHandlers.print())
                .andReturn()
    }

    protected fun performPost(uri: String, form: Any): MvcResult {
        return mockMvc
                .perform(MockMvcRequestBuilders.post(uri)
                        .content(objectMapper.writeValueAsString(form))
                        .contentType(MediaType.APPLICATION_JSON))
                .andDo(MockMvcResultHandlers.print())
                .andReturn()
    }


    protected fun performPostWithId(uri: String, form: Any, name: String, id: Long): MvcResult {
        return mockMvc
                .perform(MockMvcRequestBuilders.post(uri)
                        .content(objectMapper.writeValueAsString(form))
                        .contentType(MediaType.APPLICATION_JSON)
                        .param(name, id.toString()))
                .andDo(MockMvcResultHandlers.print())
                .andReturn()
    }

    protected fun performPut(uri: String, form: Any): MvcResult {
        return mockMvc
                .perform(MockMvcRequestBuilders.put(uri)
                        .content(objectMapper.writeValueAsString(form))
                        .contentType(MediaType.APPLICATION_JSON))
                .andDo(MockMvcResultHandlers.print())
                .andReturn()
    }

    protected fun performPatch(uri: String, form: Any): MvcResult {
        return mockMvc
                .perform(MockMvcRequestBuilders.patch(uri)
                        .content(objectMapper.writeValueAsString(form))
                        .contentType(MediaType.APPLICATION_JSON))
                .andDo(MockMvcResultHandlers.print())
                .andReturn()
    }

    protected fun performDelete(uri: String): MvcResult {
        return mockMvc
                .perform(MockMvcRequestBuilders.delete(uri))
                .andDo(MockMvcResultHandlers.print())
                .andReturn()
    }

    protected fun performDeleteWithId(uri: String, name: String, id: Long): MvcResult {
        return mockMvc
                .perform(MockMvcRequestBuilders.post(uri)
                        .param(name, id.toString()))
                .andDo(MockMvcResultHandlers.print())
                .andReturn()
    }

}

