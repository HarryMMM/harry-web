package cn.harryai.harryweb.config;

import javax.websocket.EncodeException;
import javax.websocket.Encoder;
import javax.websocket.EndpointConfig;

import com.alibaba.fastjson.JSON;

/**
 * @author Harry
 * @since 2019/6/29 13:05
 */
public class ServerEncoder implements Encoder.Text<Object> {
    @Override
    public String encode(Object object) throws EncodeException {

        return JSON.toJSONString(object);
    }

    @Override
    public void init(EndpointConfig endpointConfig) {

    }

    @Override
    public void destroy() {

    }
}
