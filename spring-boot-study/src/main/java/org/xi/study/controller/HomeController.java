package org.xi.study.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;

@RestController
@RequestMapping("/")
public class HomeController {

    @RequestMapping("")
    public String index(String name, @RequestParam(value = "sessionId", required = false) String sessionId) {
        return "hello " + name;
    }
    @RequestMapping("/upload-folder")
    public String uploadFolder(MultipartFile[] folder) {
        String basePath = "C:/chj/upload/";
        if (folder == null || folder.length == 0) {
            return "不能为空";
        }
        for (MultipartFile file : folder) {
            String filePath = basePath + file.getOriginalFilename();
            if (filePath.lastIndexOf('/') > 0) {
                String dirPath = filePath.substring(0, filePath.lastIndexOf('/'));
                File dir = new File(dirPath);
                if (!dir.exists()) {
                    dir.mkdirs();
                }
            }
            File dest = new File(filePath);
            try {
                file.transferTo(dest);
            } catch (IllegalStateException | IOException e) {
                e.printStackTrace();
            }
        }
        return "success";
    }
}
