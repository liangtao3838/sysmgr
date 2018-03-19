package com.sys.mgr.utils;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by liangtao on 2018/3/19.
 */
public class CommonUtil {

    private static final Logger log = LoggerFactory.getLogger(CommonUtil.class);

    public static List<Long> splitId(long  tid, String ids){
        try{
            List<Long> result = new ArrayList<Long>();
            String[] list = ids.split(",");
            for(int i=0; i < list.length; i++){
                result.add(Long.parseLong(list[i]));
            }
            return result;
        } catch (Exception e){
            log.error("tid:{},解析id集合错误",tid);
            return null;
        }
    }
}
