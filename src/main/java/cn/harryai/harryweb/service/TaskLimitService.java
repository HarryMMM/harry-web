package cn.harryai.harryweb.service;

import cn.harryai.harryweb.model.dto.ResponseDTO;

import javax.servlet.http.HttpServletResponse;

/**
 * @author Harry
 * @since 2019/10/13 22:06
 **/

public interface TaskLimitService {

    ResponseDTO exec(HttpServletResponse response, String session);
}
