package com.xwzjqyhr.mongodb.controller;


import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.util.HashMap;

@RestController
@RequestMapping("/excel")
public class ExcelUploadController {
    @RequestMapping(method = RequestMethod.POST)
    public void excelUpload(MultipartFile file) {

        String realPath = "L:\\mongo-excel";

        File dir = new File(realPath);
        if(!dir.exists()) dir.mkdir();
        System.out.println(realPath);

        if(file == null) {
            System.out.println("文件为空");
        }
        try {
            assert file != null;
            String oldName = file.getOriginalFilename();

            System.out.println("oldName = " + oldName);

            assert oldName != null;
            file.transferTo(new File(realPath,oldName));


        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}

