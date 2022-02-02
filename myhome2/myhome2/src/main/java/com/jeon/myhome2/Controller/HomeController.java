package com.jeon.myhome2.Controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class HomeController {

    @GetMapping() // 첫 페이지는 우선 경로 없이.
    public String index() {
        return "index"; // template 안의 index.html로 가게 함.
    }

}
