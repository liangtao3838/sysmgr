package com.sys.mgr.controller;

import com.sys.mgr.model.NodeInfoVo;
import com.sys.mgr.service.SysCallRelaGuiService;
import com.sys.mgr.utils.CommonUtil;
import com.sys.mgr.utils.JsonResponse;
import org.dom4j.Document;
import org.dom4j.DocumentHelper;
import org.dom4j.Element;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.util.CollectionUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by liangtao on 2018/3/20.
 */
@RequestMapping("/syscallrelagui")
@Controller
public class SysCallRelaGuiController {

    private final static Logger log = LoggerFactory.getLogger(SysCallRelaGuiController.class);

    @Autowired
    SysCallRelaGuiService sysCallRelaGuiService;

    @RequestMapping("/index")
    public ModelAndView index(){
        return new ModelAndView("syscallrelagui");
    }


    @RequestMapping("/getsysname")
    @ResponseBody
    public String getSysName(){
        long tid = System.nanoTime();
        try {
            List<NodeInfoVo> nodeInfoVos = sysCallRelaGuiService.getSysName();

            Map<String,List<NodeInfoVo>> map = CommonUtil.dataConvert(nodeInfoVos);

            String result = getXMl(map);
            return result;
        }catch (Exception e){
            log.error("tid:{} 获取系统调用关系系统名称异常",tid,e);
            return null;
        }
    }


    @RequestMapping("/getsyscount")
    @ResponseBody
    public JsonResponse getsyscount(){
        long tid = System.nanoTime();
        try {
            List<String> sysname = sysCallRelaGuiService.getSysNameXXX();
            List<String> count = new ArrayList<String>();
            if(!CollectionUtils.isEmpty(sysname)){
                for(String str:sysname){
                    Integer succNum = sysCallRelaGuiService.getSysSuccCount(str);
                    Integer failNum = sysCallRelaGuiService.getSysFailCount(str);
                    count.add(str+","+succNum+","+failNum);
               }
            }
            Map<String,Object> resultMap = new HashMap<String, Object>();
            resultMap.put("count",count);
            return new JsonResponse(resultMap);
        }catch (Exception e){
            log.error("tid:{} 获取系统调用关系系统名称异常",tid,e);
            return new JsonResponse("error");
        }
    }

    private String getXMl(Map<String,List<NodeInfoVo>> map){
        //生成xml
        Document document = DocumentHelper.createDocument();
        Element root = document.addElement("gexf","http://www.gexf.net/1.2draft");
        root.addAttribute("version", "2.0");
        root.addAttribute("xmlns:viz","http://www.gexf.net/1.2draft/viz");
        root.addAttribute("xmlns:xsi","http://www.w3.org/2001/XMLSchema-instance");
        root.addAttribute("xsi:schemaLocation","http://www.gexf.net/1.2draft http://www.gexf.net/1.2draft/gexf.xsd");

        //meta 标签
        Element metas = root.addElement("meta");
        metas.addAttribute("lastmodifieddate","2014-01-30");
        Element creator = metas.addElement("creator");
        creator.setText("Gephi 0.8.1");
        Element description = metas.addElement("description");
        description.setText("");
        //graph
        Element graph = root.addElement("graph");
        graph.addAttribute("defaultedgetype","undirected");
        graph.addAttribute("mode","static");
        //graph-->attributes
        Element attributes = graph.addElement("attributes");
        attributes.addAttribute("class","node");
        attributes.addAttribute("mode","static");
        Element attribute = attributes.addElement("attribute");
        attribute.addAttribute("id","modularity_class");
        attribute.addAttribute("title","Modularity class");
        attribute.addAttribute("type","integer");
        attribute.setText("");
        //graph--><nodes>
        Element nodes = graph.addElement("nodes");
        Element edges = graph.addElement("edges");
        if(map == null || map.isEmpty()){
            return document.asXML();
        }
        List<Element> elements = new ArrayList<Element>();
        for(Map.Entry<String,List<NodeInfoVo>> entry : map.entrySet()){
           generateNode(entry.getKey(),nodes);
           generateEdge(entry.getValue(),edges);
        }
        return document.asXML();
    }

    private Element generateNode(String nodeName,Element element){

        Element node = element.addElement("node");
        node.addAttribute("id",nodeName);
        node.addAttribute("label",nodeName);
        ////graph--><nodes>-->node-->....
        Element attvalues = node.addElement("attvalues");
        Element attvalue = attvalues.addElement("attvalue");
        attvalue.addAttribute("for","modularity_class");
        attvalue.addAttribute("value","0");
        attvalue.setText("");
        Element viz_size = attvalues.addElement("viz:size","http://www.gexf.net/1.2draft/viz");
        viz_size.addAttribute("value","28.685715");
        viz_size.setText("");

        Element viz_position = attvalues.addElement("viz:position","http://www.gexf.net/1.2draft/viz");
        viz_position.addAttribute("x",CommonUtil.getRandomValue());
        viz_position.addAttribute("y",CommonUtil.getRandomValue());
        viz_position.addAttribute("z",CommonUtil.getRandomValue());
        viz_position.setText("");

        Element viz_color = attvalues.addElement("viz:color","http://www.gexf.net/1.2draft/viz");
        viz_color.addAttribute("r","235");
        viz_color.addAttribute("g","81");
        viz_color.addAttribute("b","72");
        viz_color.setText("");

        return node;
    }

    private Element generateEdge(List<NodeInfoVo> nodeInfoVos,Element edges){
        if(CollectionUtils.isEmpty(nodeInfoVos) ||  edges == null){
            return null;
        }
        List<Element> listEdges = new ArrayList<Element>();
        for(NodeInfoVo nodeInfoVo:nodeInfoVos){
            Element edge = edges.addElement("edge");
            edge.addAttribute("id","0");
            edge.addAttribute("source",nodeInfoVo.getNowRouteNode());
            edge.addAttribute("target",nodeInfoVo.getNextRouteNode());
            Element edge_attvalues = edge.addElement("attvalues");
            edge_attvalues.setText("");
        }
        return edges;

    }

}
