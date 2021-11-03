package com.liQing.service;

import com.liQing.domain.Test;

import java.util.List;

public interface TestService {
    /*
    * 对account表进行查询所有
    * */

    public List<Test> findAllTest();
}
