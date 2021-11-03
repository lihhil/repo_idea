package com.liQing.controller;

import com.liQing.domain.Test;
import com.liQing.service.TestService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController  //@Controller + @ResponseBody
@RequestMapping("/test")
public class TestController {

    @Autowired
    private TestService testService;


    @RequestMapping("/findAllTest")
    public List<Test> findAllTest(){
        List<Test> allTest = testService.findAllTest();
        return allTest;
    }
}
