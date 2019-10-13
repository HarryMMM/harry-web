package cn.harryai.harryweb.controller;


import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import cn.harryai.toolkit.io.FileOperateUtils;

/**
 * @author Harry
 * @since 2019/10/13 21:09
 **/
@RestController
@RequestMapping("/file")
public class FileController {
    @Value("${upload.partName}")
    private String partName;
    @Value("${download.directory}")
    private String downloadPath;
    @Value("${upload.directory}")
    private String uploadPath;

    @GetMapping("download")
    public void download(String fileName, HttpServletResponse response) throws IOException {
        FileOperateUtils.download(response, downloadPath, fileName);

    }

    @PostMapping("upload")
    public void upload(HttpServletRequest request) throws IOException, ServletException {
        FileOperateUtils.upload(request, uploadPath, partName);
    }
}
