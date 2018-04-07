<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@include file="../../common.jsp" %>
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <title>Insert title here</title>
    <script type="text/javascript" src="/echarts/js/echarts.min.js"></script>
    <script type="text/javascript" src="/echarts/js/echarts-gl.min.js"></script>
    <script type="text/javascript" src="/echarts/js/ecStat.min.js"></script>
    <script type="text/javascript" src="/echarts/js/dataTool.min.js"></script>
    <script type="text/javascript" src="/echarts/js/simplex.js"></script>
    <script type="text/javascript" src="/echarts/js/jquery-3.3.1.min.js"></script>
</head>
<body style="height: 100%; margin: 0">
<div id="main">
    <div id="left" style="float:left ;  width:40%;  height:100%;">
        <span>监控周期</span>
        <select id="monitortime" style="width:70px;">
            <option value ="hour">小时</option>
            <option value ="minutes">五分钟</option>
            <option value ="day">天</option>
        </select>
        <div id="sysrole" style="margin-top: 100px;">
        </div>
    </div>
    <div id="right" style="float:left ; background-color: #0f0f0f; width:60%; height:100%;"></div>
</div>
<script type="text/javascript">

    $(function () {
        var monitortime = $("#monitortime").val();
        $.ajax({
            url: "/syscallrelagui/getsyscount.do",
            dataType: "json",
            type: "post",
            async: false,
            data:{monitortime:monitortime},
            success : function(data){
                var nodes=data.result.count;
                var content = "";
                $.each(nodes, function(i, item){
                    var arr = item.split(",");
                    content += '<span><a href="/sysservicegui/index.do?nodecode='+arr[0]+'">'+arr[0]+'系统</a></span></br>';
                    content += '<input type="text" readonly value="'+arr[1]+'">';
                    content += '<input type="text" readonly value="'+arr[2]+'">';
                    content += '</br>'
                })
                $("#sysrole").html(content);
            },
        });
    });

    var dom = document.getElementById("right");
    var myChart = echarts.init(dom);
    var app = {};
    option = null;
    app.title = '力引导布局';
    myChart.showLoading();
    $.get('/syscallrelagui/getsysname.do', function (xml) {
        myChart.hideLoading();
        var graph = echarts.dataTool.gexf.parse(xml);
        var categories = [];
        for (var i = 0; i < graph.nodes.length; i++) {
            categories[i] = {
                name: graph.nodes[i].name
            };
        }
        graph.nodes.forEach(function (node) {
            node.itemStyle = null;
            node.symbolSize = 50;
            node.value = node.symbolSize;
            node.category = node.attributes.modularity_class;
            node.x = node.y = null;
            node.draggable = true;
            node.onclick = function(){
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
                        repulsion: 1000
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