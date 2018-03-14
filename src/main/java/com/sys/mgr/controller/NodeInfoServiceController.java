package com.sys.mgr.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

/**
 * Created by liangtao on 2018/3/14.
 */
@Controller
@RequestMapping("/nodeinfo")
public class NodeInfoServiceController {

    private final static Logger log = LoggerFactory.getLogger(NodeInfoServiceController.class);

    @RequestMapping("/index")
    public ModelAndView index(){
        return new ModelAndView("nodemgr");
    }



    @RequestMapping("add")
    @ResponseBody
    public String add(){
        return "ok";
    }
}
