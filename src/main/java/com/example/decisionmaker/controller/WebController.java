package com.example.decisionmaker.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller // HTMLを返すためのアノテーション
public class WebController {

    @GetMapping("/") // トップページ（http://localhost:8080/）にアクセスした時
    public String index() {
        return "index"; // templates/index.html を探して表示
    }
}