package com.sys.mgr.service.Impl;

import com.sys.mgr.service.IHelloService;

/**
 * Created by zhengchenglei on 2018/4/21.
 */
public class HelloService implements IHelloService {

    public String sayHello(String name){
        return "Hello, "+name;
    }
}
