package com.liQing.service.impl;

import com.liQing.dao.TestMapper;
import com.liQing.domain.Test;
import com.liQing.service.TestService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TestServiceImpl implements TestService {

    @Autowired
    private TestMapper testMapper;

    @Override
    public List<Test> findAllTest() {
        List<Test> allTest = testMapper.findAllTest();
        return allTest;
    }
}
