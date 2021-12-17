package com.example.ioanyt.Controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.util.regex.Matcher;
import java.util.regex.Pattern;


@RestController
@RequestMapping("/data")
public class HttpContent {
    @GetMapping("/url")
    public Data getHttpData(@RequestParam("url") String url) throws IOException {
        URL url1 = new URL(url);
        String s="";
        Data data =new Data();
        try {
            InputStream inputStream = url1.openStream();
            byte[] b = new byte[8];
            while (inputStream.read(b) != -1) {
                s = s + new String(b);
            }
        }catch (Exception e){
            e.printStackTrace();
            data.setContent("Content Not Found");
            return data;
        }
        Pattern p = Pattern.compile("<body(.+?)</body>", Pattern.DOTALL);
        Matcher matcher = p.matcher(s);
        matcher.find();


        data.setContent(matcher.group(1));
        return data;
    }
}
