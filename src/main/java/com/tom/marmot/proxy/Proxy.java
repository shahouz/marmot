package com.tom.marmot.proxy;

/**
 * 描述
 *
 * @author : tdl
 * @date : 2019/7/4 上午11:41
 **/
public interface Proxy {
    Object doProxy(ProxyChain proxyChain) throws Throwable;
}
