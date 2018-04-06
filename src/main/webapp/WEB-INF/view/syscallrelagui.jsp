<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@include file="../../common.jsp" %>
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <title>Insert title here</title>
    <style type="text/css">
        html, body, #main {
            height: 100%;
            width: 100%;
            margin: 0;
            padding: 0
        }
    </style>
    <script type="text/javascript" src="/echarts/js/echarts.min.js"></script>
    <script type="text/javascript" src="/echarts/js/echarts-gl.min.js"></script>
    <script type="text/javascript" src="/echarts/js/ecStat.min.js"></script>
    <script type="text/javascript" src="/echarts/js/dataTool.min.js"></script>
    <script type="text/javascript" src="/echarts/js/simplex.js"></script>
    <script type="text/javascript" src="/echarts/js/jquery-3.3.1.min.js"></script>
</head>
<body style="height: 100%; margin: 0">
<div id="container" style="height: 100%; background: black;" ></div>


<script type="text/javascript">
    var dom = document.getElementById("container");
    var myChart = echarts.init(dom);
    var app = {};
    option = null;
    app.title = '力引导布局';

    myChart.showLoading();
    $.get('/syscallrelagui/getsysname.do', function (xml) {
        myChart.hideLoading();

        var graph = echarts.dataTool.gexf.parse(xml);
        var categories = [];
        for (var i = 0; i < 9; i++) {
            categories[i] = {
                name: '类目' + i
            };
        }
        graph.nodes.forEach(function (node) {
            node.itemStyle = null;
            node.symbolSize = 80;
            node.value = node.symbolSize;
            node.category = node.attributes.modularity_class;
            // Use random x, y
            node.x = node.y = null;
            node.draggable = true;
            node.onclick = function(){
                alert("aaa");
            }
        });
        option = {
            title: {
                text: 'Les Miserables',
                subtext: 'Default layout',
                top: 'bottom',
                left: 'right'
            },
            tooltip: {},
            legend: [{
                // selectedMode: 'single',
                data: categories.map(function (a) {
                    return a.name;
                })
            }],
            animation: false,
            series : [
                {
                    name: 'Les Miserables',
                    type: 'graph',
                    layout: 'force',
                    data: graph.nodes,
                    links: graph.links,
                    categories: categories,
                    roam: true,
                    label: {
                        normal: {
                            position: 'right'
                        }
                    },
                    force: {
                        repulsion: 4000
                    }
                }
            ]
        };

        myChart.setOption(option);
    }, 'xml');;
    if (option && typeof option === "object") {
        myChart.setOption(option, true);
    }
</script>
</body>
</html>