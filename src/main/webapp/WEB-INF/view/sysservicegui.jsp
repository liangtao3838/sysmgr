<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@include file="../../common.jsp" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <meta name="description" content="ECharts">
    <script src="<%=basePath%>/echarts/build/dist/echarts.js"></script>
    <script src="<%=basePath%>/js/common/jquery.js"></script>
</head>
<body>
<div id="main" style="width:1000px;  height:500px;">
    <div id="left" style="float:left ;  width:50%;  height:100%;">
        <span>监控周期</span>
        <select style="width:50px;">
            <option value ="hour">小时</option>
            <option value ="minutes">五分钟</option>
            <option value ="day">天</option>
        </select>
        <div id="sysrole" style="margin-top: 100px;">
        </div>
    </div>
    <div id="right" style="float:left ;  width:50%; height:100%;"></div>
</div>

<script type="text/javascript">

    $(function () {
        $.ajax({
            url: "/sysservicegui/getsyscount.do",
            dataType: "json",
            type: "post",
            async: false,
            success : function(data){
                var nodes=data.result.count;
                var content = "";
                $.each(nodes, function(i, item){
                    var arr = item.split(",");
                    content += '<span>'+arr[0]+'系统</span></br>';
                    content += '<input type="text" readonly value="'+arr[1]+'">';
                    content += '<input type="text" readonly value="'+arr[2]+'">';
                    content += '</br>'
                })
                $("#sysrole").html(content);
            },
        });
    });

    var getNodes = function(){
        var result = [];
        var temp = {};
        $.ajax({
            url: "/sysservicegui/getsysname.do",
            dataType: "json",
            type: "post",
            async: false,
            success : function(data){
                var nodes=data.result.sysname;
                $.each(nodes, function(i, item){
                    if(i==0){
                        temp = {category:0, name:item, value:2, label:item};
                    }else{
                        temp = {category:1, name:item, value:0, label:item};
                    }
                    result.push(temp);
                })
            },
        });
        return result;
    };

    var getLinks = function(){
        var result = [];
        var temp = {};
        $.ajax({
            url: "/sysservicegui/getsysname.do",
            type: "post",
            contentType: "json",
            async: false,
            success : function(data){
                var nodes=data.result.sysname;
                $.each(nodes, function(i, item){
                    if(i>0){
                        temp =  {source :item, target :data.result.sysname[0] , weight : 1, name: '依赖'};
                        result.push(temp);
                    }
                })
            },
        });
        return result;
    };

    // 路径配置
    require.config({
        paths: {
            echarts: '/echarts/build/dist'
        }
    });

    // 使用
    require(
            [
                'echarts',
                'echarts/chart/force',
                'echarts/chart/chord'// 使用柱状图就加载bar模块，按需加载
            ],
            DrawEChart
    );

    function DrawEChart(ec) {
        //--- 折柱 ---
        myChart = ec.init(document.getElementById('right'));
        //图表显示提示信息
        myChart.showLoading({
            text: "站点关系图正在努力加载..."
        });
        myChart.hideLoading();
        myChart.setOption({
            title : {
                text: '服务关系：企业服务总线',
                x:'center',
                y:'left'
            },
            tooltip : {
                trigger: 'item',
                formatter: '{a} : {b}'
            },
            series : [
                {
                    type:'force',
                    name : "服务关系",
                    ribbonType: false,
                    itemStyle: {
                        normal: {
                            label: {
                                show: true,
                                textStyle: {
                                    color: '#333'
                                }
                            },
                            nodeStyle : {
                                brushType : 'both',
                                borderColor : 'rgba(255,215,0,0.4)',
                                borderWidth : 1
                            },
                            linkStyle: {
                                type: 'curve'
                            }
                        },
                        emphasis: {
                            label: {
                                show: false
                            },
                            nodeStyle : {
                            },
                            linkStyle : {}
                        }
                    },
                    useWorker: false,
                    minRadius : 15,
                    maxRadius : 25,
                    gravity: 1.1,
                    scaling: 1.1,
                    roam : 'move',
                    nodes : getNodes(),
                    links : getLinks()
                }
            ]
        });
    }

</script>
</body>
</html>






