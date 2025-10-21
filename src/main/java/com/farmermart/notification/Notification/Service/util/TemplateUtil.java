package com.farmermart.notification.Notification.Service.util;

import org.springframework.core.io.ClassPathResource;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.nio.file.Files;
import java.util.Map;

@Component
public class TemplateUtil {

    public String loadTemplate(String filename, Map<String,String> placeHolder) throws IOException {
        ClassPathResource classPathResource = new ClassPathResource("templates/" + filename);
        String content = Files.readString(classPathResource.getFile().toPath());

        for(Map.Entry<String,String> entry:placeHolder.entrySet()){
            content = content.replace("{{"+ entry.getKey()+"}}", entry.getValue());
        }
        return content;
    }
}
