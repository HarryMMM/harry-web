package cn.harryai.harryweb.controller;

import cn.harryai.toolkit.io.FileOperateUtil;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

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
        FileOperateUtil.download(response, downloadPath, fileName);

    }

    @PostMapping("upload")
    public void upload(HttpServletRequest request) throws IOException, ServletException {
        FileOperateUtil.upload(request, uploadPath, partName);
    }
}
