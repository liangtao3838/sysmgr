package com.sys.mgr.utils;

import org.dom4j.Document;
import org.dom4j.DocumentHelper;
import org.dom4j.Element;


/**
 * Created by liangtao on 2018/4/2.
 */
public class Documenttest {

    public static void main(String[] args){

        Document document = DocumentHelper.createDocument();
        Element root = document.addElement("gexf","http://www.gexf.net/1.2draft");
        root.addAttribute("xmlns:viz","http://www.gexf.net/1.2draft/viz");
        root.addAttribute("xmlns:xsi","http://www.w3.org/2001/XMLSchema-instance");
        root.addAttribute("xsi:schemaLocation","http://www.gexf.net/1.2draft http://www.gexf.net/1.2draft/gexf.xsd");
        root.addAttribute("version", "2.0");
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
        Element node = nodes.addElement("node");
        node.addAttribute("id","0");
        node.addAttribute("label","Myriel");
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
        viz_position.addAttribute("x","-266.82776");
        viz_position.addAttribute("y","299.6904");
        viz_position.addAttribute("z","0.0");
        viz_position.setText("");
        Element viz_color = attvalues.addElement("viz:color","http://www.gexf.net/1.2draft/viz");
        viz_color.addAttribute("r","235");
        viz_color.addAttribute("g","81");
        viz_color.addAttribute("b","72");
        viz_color.setText("");
        //graph--><edges>
        Element edges = graph.addElement("edges");
        Element edge = edges.addElement("edge");
        edge.addAttribute("id","0");
        edge.addAttribute("source","1");
        edge.addAttribute("target","0");
        Element edge_attvalues = edge.addElement("attvalues");
        edge_attvalues.setText("");
        System.out.print(document.asXML());
    }
}
