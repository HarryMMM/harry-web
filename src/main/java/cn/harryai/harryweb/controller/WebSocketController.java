package cn.harryai.harryweb.controller;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.CopyOnWriteArraySet;

import javax.websocket.EncodeException;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import cn.harryai.harryweb.model.User;

/**
 * @author Harry
 * @since 2019/10/13 21:09
 **/
@RestController
public class WebSocketController {

    @GetMapping("send")
    public void send() {
        CopyOnWriteArraySet<WebSocker> webSocketSet = WebSocker.webSocketSet;
        webSocketSet.forEach(webSocker -> {
            User user = new User();
            user.setAge(11);
            user.setName("harry");
            User user2 = new User();
            user2.setAge(13);
            user2.setName("heiyou");
            List<User> userList = new ArrayList<>();
            userList.add(user);
            userList.add(user2);
            try {
                webSocker.getSession().getBasicRemote().sendObject(userList);
                // webSocker.getSession().getBasicRemote().sendText("hello");
            } catch (IOException | EncodeException e) {
                e.printStackTrace();
            }
        });
    }
}
