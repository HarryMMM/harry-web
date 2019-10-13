package cn.harryai.harryweb.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;


/**
 * @author Harry
 * @since 2019/10/13 21:08
 **/
@Controller
public class IndexController {
    @GetMapping("index")
    public String index() {
        return "index2.html";
    }


}
