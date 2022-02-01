package com.godcoder.myhome1.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/board") // 괄호 안에 경로를 board로 지정함
public class BoardController {

    @GetMapping("/list") // 괄호 안에 경로를 list로 지정함
    public String list() {
        return "board/list";
    }

}
