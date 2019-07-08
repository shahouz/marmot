package com.tom.marmot.test;

import com.tom.marmot.annotation.Service;

/**
 * 描述
 *
 * @author : tdl
 * @date : 2019/7/1 下午1:52
 **/
@Service
public class TestServiceImpl implements TestService {
    @Override
    public Boolean checkExists() {
        return true;
    }
}
