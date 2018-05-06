package com.sys.mgr.controller;

import com.alibaba.fastjson.JSONObject;
import com.sys.mgr.model.*;
import com.sys.mgr.service.DataExportCommonProcessor;
import com.sys.mgr.service.TradeDetailService;
import com.sys.mgr.utils.ExcelDataExportUtil;
import com.sys.mgr.utils.JsonResponse;
import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletResponse;
import java.net.URLDecoder;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by liangtao on 2018/3/25.
 */
@RequestMapping("tradedetail")
@Controller
public class TradeDetailController {

    private final  static Logger log = LoggerFactory.getLogger(TradeDetailController.class);

    @Autowired
    TradeDetailService tradeDetailService;

    @RequestMapping("index")
    public ModelAndView index(){
        return new ModelAndView("tradedetail");
    }


    @RequestMapping(value = "list",produces= "text/plain;charset=UTF-8")
    @ResponseBody
    public String getList(
            @RequestParam(value = "syscallname") String syscallname,
            @RequestParam(value = "qqxt") String qqxt,
            @RequestParam(value = "zt") String zt,
            @RequestParam(value = "startDate") String startDate,
            @RequestParam(value = "endDate") String endDate,
            @RequestParam(value = "page",  defaultValue = "0") Integer offset,
            @RequestParam(value = "rows",  defaultValue = "10") Integer rows){
        long tid = System.nanoTime();
        try{
            List<TradeDetail> list =  tradeDetailService.getList(tid,syscallname,qqxt,zt,startDate,endDate,offset,rows);
            Map<String,Object> result = new HashMap<String,Object>();
            long count=tradeDetailService.getCount(tid,syscallname,qqxt,zt,startDate,endDate);
            result.put("rows",list);
            result.put("total",count);
            return new JsonResponse(result).toJSON();
        }catch (Exception e){
            log.error("tid:{} 获取交易明细异常",tid,e);
            return JsonResponse.errorResponse(-1,"error").toJSON();
        }
    }


    @RequestMapping(value = "getxml",produces= "text/plain;charset=UTF-8")
    @ResponseBody
    public String getXml(
            @RequestParam(value = "id") Long id,
            @RequestParam(value = "type") String type){
        long tid = System.nanoTime();
        if(id==null||StringUtils.isEmpty(type)){
            return JsonResponse.errorResponse(-1,"参数非法").toJSON();
        }
        try{
            String data =  tradeDetailService.getXMl(id,type);
            return new JsonResponse(data).toJSON();
        }catch (Exception e){
            log.error("tid:{} 获取交易相应明细异常,id:{},type",tid,id,type,e);
            return JsonResponse.errorResponse(-1,"error").toJSON();
        }
    }

    @RequestMapping(value = "exportTradeDetail.do",produces= "text/plain;charset=UTF-8")
    public void exportOrderRealtimeStatus(@RequestParam(value = "jkmc") String jkmc,
                                          @RequestParam(value = "qqxt") String qqxt,
                                          @RequestParam(value = "zt") String zt,
                                          @RequestParam(value = "startDate") String startDate,
                                          @RequestParam(value = "endDate") String endDate,
                                          HttpServletResponse response) throws Exception {
        final ExceptRequest request = new ExceptRequest();
        request.setJkmc(jkmc);
        request.setEndDate(endDate);
        request.setStartDate(startDate);
        request.setQqxt(qqxt);
        request.setZt(zt);
        List<String>title = new ArrayList<String>();
        title.add("接口名称");
        title.add("请求系统");
        title.add("目标系统");
        title.add("目标接口名称");
        title.add("阶段位置");
        title.add("系统异常状态");
        title.add("业务异常状态");
        title.add("录入日期");
        ExcelDataExportUtil.exportDataUtil(title,new DataExportCommonProcessor(){
            Integer startRow = 0;
            Integer pageSize = 1000;
            @Override
            public Object[][] getExportData() {
                if(startRow == null){
                    startRow = request.getStartRow();
                    request.setPageSize(pageSize);
                    request.setPageSize(pageSize);
                }else{
                    request.setStartRow(startRow);
                    request.setPageSize(pageSize);
                }
                if(startRow == null){
                    throw new RuntimeException("startRow can't be null");
                }
                ExportResponse resp = tradeDetailService.getExport(request);
                List<TradeDetail> ExceptsData = resp.getList();
                if(CollectionUtils.isEmpty(ExceptsData)){
                    return null;
                }
                Object[][] data = new Object[ExceptsData.size()][8];
                for(int i = 0;i < ExceptsData.size();i++){
                    data[i][0] = ExceptsData.get(i).getJkmc();
                    data[i][1] = ExceptsData.get(i).getQqxt();
                    data[i][2] = ExceptsData.get(i).getMbxt();
                    data[i][3] = ExceptsData.get(i).getMbjk();
                    data[i][4] = ExceptsData.get(i).getJdwz();
                    data[i][5] = ExceptsData.get(i).getZt();
                    data[i][6] = ExceptsData.get(i).getZt();
                    data[i][7] = ExceptsData.get(i).getLrrq();
                }
                startRow = startRow + data.length;
                return data;
            }
            @Override
            public Object[][] getExportData(Integer startRow, Integer pageSize) {
                return new Object[0][];
            }
            @Override
            public void resetPos() {
                startRow = 0;
                pageSize = 1000;
            }
        },null,response);
    }

    @RequestMapping(value = "exportTradeWeek.do",produces= "text/plain;charset=UTF-8")
    public void exportWeek(@RequestParam(value = "startWeek") String startWeek,
                           @RequestParam(value = "endWeek") String endWeek,
                           HttpServletResponse response) throws Exception {
        final ExceptAnalyseRequest request = new ExceptAnalyseRequest();
        request.setStartDate(startWeek);
        request.setEndDate(endWeek);
        List<String>title = new ArrayList<String>();
        title.add("系统编码");
        title.add("调用成功数");
        title.add("调用失败数");
        title.add("被调成功数");
        title.add("被调失败数");
        ExcelDataExportUtil.exportDataUtil(title,new DataExportCommonProcessor(){
            Integer startRow = 0;
            Integer pageSize = 1000;
            @Override
            public Object[][] getExportData() {
                if(startRow == null){
                    startRow = request.getStartRow();
                    request.setPageSize(pageSize);
                    request.setPageSize(pageSize);
                }else{
                    request.setStartRow(startRow);
                    request.setPageSize(pageSize);
                }
                if(startRow == null){
                    throw new RuntimeException("startRow can't be null");
                }
                ExportAnalyseResponse resp = tradeDetailService.getExportWeek(request);
                List<TradeDetailAnalyse> ExceptsDataWeek = resp.getList();
                if(CollectionUtils.isEmpty(ExceptsDataWeek)){
                    return null;
                }
                Object[][] data = new Object[ExceptsDataWeek.size()][5];
                for(int i = 0;i < ExceptsDataWeek.size();i++){
                    data[i][0] = ExceptsDataWeek.get(i).getNodeCode();
                    data[i][1] = ExceptsDataWeek.get(i).getDySuccNum();
                    data[i][2] = ExceptsDataWeek.get(i).getDyFailNum();
                    data[i][3] = ExceptsDataWeek.get(i).getBdSuccNum();
                    data[i][4] = ExceptsDataWeek.get(i).getBdFailNum();
                }
                startRow = startRow + data.length;
                return data;
            }
            @Override
            public Object[][] getExportData(Integer startRow, Integer pageSize) {
                return new Object[0][];
            }
            @Override
            public void resetPos() {
                startRow = 0;
                pageSize = 1000;
            }
        },null,response);
    }

    @RequestMapping(value = "exportTradeMonth.do",produces= "text/plain;charset=UTF-8")
    public void exportMonth(@RequestParam(value = "startMonth") String startMonth,
                            @RequestParam(value = "endMonth") String endMonth,
                            HttpServletResponse response) throws Exception {
        final ExceptAnalyseRequest request = new ExceptAnalyseRequest();
        request.setStartDate(startMonth);
        request.setEndDate(endMonth);
        List<String>title = new ArrayList<String>();
        title.add("服务编码");
        title.add("调用成功数");
        title.add("调用失败数");
        ExcelDataExportUtil.exportDataUtil(title,new DataExportCommonProcessor(){
            Integer startRow = 0;
            Integer pageSize = 1000;
            @Override
            public Object[][] getExportData() {
                if(startRow == null){
                    startRow = request.getStartRow();
                    request.setPageSize(pageSize);
                    request.setPageSize(pageSize);
                }else{
                    request.setStartRow(startRow);
                    request.setPageSize(pageSize);
                }
                if(startRow == null){
                    throw new RuntimeException("startRow can't be null");
                }
                ExportAnalyseResponse resp = tradeDetailService.getExportMonth(request);
                List<TradeDetailAnalyse> ExceptsDataMonth = resp.getList();
                if(CollectionUtils.isEmpty(ExceptsDataMonth)){
                    return null;
                }
                Object[][] data = new Object[ExceptsDataMonth.size()][3];
                    for(int i = 0;i < ExceptsDataMonth.size();i++){
                    data[i][0] = ExceptsDataMonth.get(i).getNodeCode();
                    data[i][1] = ExceptsDataMonth.get(i).getDySuccNum();
                    data[i][2] = ExceptsDataMonth.get(i).getDyFailNum();
                }
                startRow = startRow + data.length;
                return data;
            }
            @Override
            public Object[][] getExportData(Integer startRow, Integer pageSize) {
                return new Object[0][];
            }
            @Override
            public void resetPos() {
                startRow = 0;
                pageSize = 1000;
            }
        },null,response);
    }
}
