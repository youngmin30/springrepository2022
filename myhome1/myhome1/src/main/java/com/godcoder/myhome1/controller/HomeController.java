package com.godcoder.myhome1.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class HomeController {

    @GetMapping
    public String index() { // index() 괄호 안에 경로 지정 안했음
        return "index"; // templates에 있는 index.html을 의미함. templates는 타임리프 파일이 들어 있는 곳임

    }
}
