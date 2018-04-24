package com.sys.mgr.service.Impl;

import com.sys.mgr.dao.SysServiceDao;
import com.sys.mgr.model.NodeInfo;
import com.sys.mgr.model.SysService;
import com.sys.mgr.service.NodeInfoService;
import com.sys.mgr.utils.NetUtil;
import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.List;
import java.util.Timer;
import java.util.TimerTask;

/**
 * Created by zhengchenglei on 2018/4/21.
 */
@Service
public class SystemProTaskService {


    @Value("${systemProSwitch}")
    private String systemProSwitch;
    @Value("${systemProFrequency}")
    private Integer systemProFrequency;
    @Value("${serviceProSwitch}")
    private String serviceProSwitch;
    @Value("${serviceProFrequency}")
    private Integer serviceProFrequency;
    @Autowired
    private NodeInfoService nodeInfoService;
    @Autowired
    private SysServiceDao sysServiceDao;



    public SystemProTaskService() {


        if (StringUtils.isNotEmpty(systemProSwitch) && "1".equals(systemProSwitch)) {
            new Timer().schedule(new TimerTask() {
                @Override
                public void run() {
                    int startPage = 0, pageSize = 20;
                    long tid = System.currentTimeMillis();
                    while (true) {
                        List<NodeInfo> nodeInfos = nodeInfoService.getList(tid, startPage, pageSize);
                        if (CollectionUtils.isEmpty(nodeInfos)) {
                            break;
                        }

                        for (NodeInfo nodeInfo : nodeInfos) {
                            try {
                                int returnCode = NetUtil.getHttpResponse(nodeInfo.getCallAddr());
                                if (returnCode != 200) {
                                    nodeInfo.setStatus("2");
                                } else {
                                    nodeInfo.setStatus("1");
                                }
                                nodeInfoService.update(nodeInfo);
                            } catch (IOException e) {
                                e.printStackTrace();
                                nodeInfo.setStatus("2");
                                nodeInfoService.update(nodeInfo);
                            }
                        }

                    }
                }
            }, 5, systemProFrequency == null ? 5000 : systemProFrequency * 1000);
        }

        if (StringUtils.isNotEmpty(serviceProSwitch) && "1".equals(serviceProSwitch)) {
            new Timer().schedule(new TimerTask() {
                @Override
                public void run() {
                    int startPage = 0, pageSize = 20;
                    long tid = System.currentTimeMillis();
                    while (true) {
                        List<SysService> sysServices = sysServiceDao.getList(startPage, pageSize);
                        if (CollectionUtils.isEmpty(sysServices)) {
                            break;
                        }

                        for (SysService sysService : sysServices) {
                            try {
                                int returnCode = NetUtil.getHttpResponse(sysService.getServiceAddr());
                                if (returnCode != 200) {
                                    sysService.setStatus("2");
                                } else {
                                    sysService.setStatus("1");
                                }
                                sysServiceDao.update(sysService);
                            } catch (IOException e) {
                                e.printStackTrace();
                                sysService.setStatus("2");
                                sysServiceDao.update(sysService);
                            }
                        }

                    }
                }
            }, 5, serviceProFrequency == null ? 5000 : serviceProFrequency * 1000);
        }

    }











}
