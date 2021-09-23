package com.ihor.demo.servingwebcontent;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

@Controller
public class HelloController {

    @GetMapping("/")
    @ResponseBody
    public String index() throws IOException {
        final Properties properties = new Properties();
        try (InputStream inputStream = getClass()
                .getClassLoader().getResourceAsStream("application.properties")) {
            properties.load(inputStream);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return "Hello "+ properties.getProperty("param2") + "!";
    }

    @GetMapping("/greetings")
    @ResponseBody
    public String greetings(@RequestParam(
            name="name", required=false,
            defaultValue="World") String name){
        return "Hello " + name + "!";
    }
}
