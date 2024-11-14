package com.bbgk.mml.member.controller

import org.springframework.stereotype.Controller
import org.springframework.ui.Model
import org.springframework.web.bind.annotation.GetMapping

@Controller
class MemberViewController(
) {

    @GetMapping("/login")
    fun index(model: Model): String {
        return "login"
    }
}