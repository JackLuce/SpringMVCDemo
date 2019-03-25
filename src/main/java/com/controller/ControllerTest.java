package com.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.ArrayList;
import java.util.List;

@Controller
@RequestMapping("/")
public class ControllerTest {

    @RequestMapping("/hello")
    public String test(Model model){
        List<String> strings = new ArrayList<>();
        strings.add("hello");
        strings.add("word");
        model.addAttribute("strings",strings);
        return "success";
    }
}
