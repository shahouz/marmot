package com.tom.marmot.proxy;

import com.tom.marmot.annotation.Transaction;
import com.tom.marmot.helper.DatabaseHelper;

import java.lang.reflect.Method;

/**
 * 事务代理
 *
 * @author : tdl
 * @date : 2019/7/10 下午2:32
 **/
public class TransactionProxy implements Proxy {
    private static final ThreadLocal<Boolean> FLAG_HOLDER = new ThreadLocal<Boolean>() {
        @Override
        protected Boolean initialValue() {
            return false;
        }
    };

    @Override
    public Object doProxy(ProxyChain proxyChain) throws Throwable {
        Object result;
        boolean flag = FLAG_HOLDER.get();
        Method method = proxyChain.getTargetMethod();
        if (!flag && method.isAnnotationPresent(Transaction.class)) {
            FLAG_HOLDER.set(true);
            try {
                DatabaseHelper.beginTransaction();
                System.out.println("事务开始");
                result = proxyChain.doProxyChain();
                DatabaseHelper.commitTransaction();
                System.out.println("事务提交");
            } catch (Exception e) {
                DatabaseHelper.rollbackTransaction();
                System.out.println("事务回滚");
                throw e;
            } finally {
                FLAG_HOLDER.remove();
            }
        } else {
            result = proxyChain.doProxyChain();
        }

        return result;
    }
}
