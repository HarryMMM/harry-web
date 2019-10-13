package cn.harryai.harryweb.controller;

import cn.harryai.harryweb.model.dto.ResponseDTO;
import cn.harryai.harryweb.service.TaskLimitService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletResponse;

/**
 * @author Harry
 * @since 2019/10/13 22:06
 **/
@Controller
@RequestMapping("/task")
public class TaskLimitContorller {
    @Autowired
    private TaskLimitService taskLimitService;

    @GetMapping("saveAll")
    public void saveAll(HttpServletResponse response, String session) {
          taskLimitService.exec(response, session);
    }
}
