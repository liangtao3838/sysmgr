<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@include file="../../common.jsp" %>
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <title>服务监控</title>
    <script src="/dist/echarts.js"></script>
    <script src="/static/lib/esl.js"></script>
    <script src="/static/lib/config.js"></script>
    <script src="/static/lib/jquery.min.js"></script>
    <script src="/static/lib/facePrint.js"></script>
    <script src="/static/lib/testHelper.js"></script>
    <script src="/js/gui/serviceindex.js"></script>
    <link rel="stylesheet" href="/static/lib/reset.css">
    <link rel="stylesheet" href="/bootstrap/bootstrap-3.3.5/css/bootstrap.css">
    <link rel="stylesheet" href="/bootstrap/bootstrap-3.3.5/css/bootstrap-theme.css">
</head>
<body style="height: 100%; margin: 0;background-color: #0f0f0f;">
<div id="main">
    <div id="left" style="float:left ; background: #D1EEEE; width:40%;  height:100%;padding-top: 10px;">
        <span style="font-size: 15px;" class="label label-info">监控周期</span>
        <select id="monitortime" onblur="monitor()"  style="height: 25px;border-radius:25px;border: 0px">
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

    /*function monitor(){
        var monitortime = $("#monitortime").val();
        $.ajax({
            url: "/sysservicegui/getsyscount.do",
            dataType: "json",
            type: "post",
            data:{nodecode:nodecode,monitortime:monitortime},
            async: false,
            success : function(data){
                var nodes=data.result.count;
                var content = "";
                $.each(nodes, function(i, item){
                    var arr = item.split(",");
                    content += '<span>'+arr[0]+'系统</span></br>';
                    content += '<span>成功</span><input type="text" style="border: 0px;background-color: #D1EEEE" readonly value="[' + arr[1] + ']">';
                    content += '<span>失败</span><input type="text" style="border: 0px;background-color: #D1EEEE" readonly value="[' + arr[2] + ']">';
                    content += '</br>'
                })
                $("#sysrole").html(content);
            },
        });
    }
    $(function () {
        monitor();
    });*/

    /*var dom = document.getElementById("right");
    var myChart = echarts.init(dom);
    var app = {};
    option = null;
    app.title = '力引导布局';
    myChart.showLoading();
    $.get('/sysservicegui/getsysname.do?nodecode='+nodecode+'', function (xml) {
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
            node.value = "";
            node.category = node.attributes.modularity_class;
            node.x = node.y = null;
            node.draggable = true;
            node.onclick = function(){
            }
        });
        option = {
            title: {
                text: '',
                subtext: '',
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
                    name: '',
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
    }*/
</script>
</body>
</html>