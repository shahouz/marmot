package com.tom.marmot.test;

import com.tom.marmot.annotation.Service;
import com.tom.marmot.annotation.Transaction;

/**
 * 测试用服务类
 *
 * @author : tdl
 * @date : 2019/7/1 下午1:52
 **/
@Service
public class TestServiceImpl implements TestService {
    @Override
    @Transaction
    public Boolean checkExists() {
        return true;
    }
}
