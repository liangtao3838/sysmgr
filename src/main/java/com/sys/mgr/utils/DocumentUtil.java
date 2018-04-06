package com.sys.mgr.utils;

import com.sys.mgr.model.NodeInfoVo;
import org.dom4j.Document;
import org.dom4j.DocumentHelper;
import org.dom4j.Element;
import org.springframework.util.CollectionUtils;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;


/**
 * Created by liangtao on 2018/4/2.
 */
public class DocumentUtil {

    public static String getXMl(Map<String,List<NodeInfoVo>> map){
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

    private static Element generateNode(String nodeName,Element element){

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

    private static Element generateEdge(List<NodeInfoVo> nodeInfoVos,Element edges){
        if(CollectionUtils.isEmpty(nodeInfoVos) ||  edges == null){
            return null;
        }
        List<Element> listEdges = new ArrayList<Element>();
        for(NodeInfoVo nodeInfoVo:nodeInfoVos){
            Element edge = edges.addElement("edge");
            edge.addAttribute("id","0");
            edge.addAttribute("source",nodeInfoVo.getNextRouteNode());
            edge.addAttribute("target",nodeInfoVo.getNowRouteNode());
            Element edge_attvalues = edge.addElement("attvalues");
            edge_attvalues.setText("");
        }
        return edges;

    }
}
