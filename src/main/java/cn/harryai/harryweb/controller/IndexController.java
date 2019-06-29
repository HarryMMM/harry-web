package cn.harryai.harryweb.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.concurrent.CopyOnWriteArraySet;

@Controller
public class IndexController {
    @GetMapping("index")
    public String index(){
        return "index2.html";
    }


}
