var echarts;
var resultData;
var legendData = new Array();
var seriesCategories = new Array();
var seriesData = new Array();
var seriesLinks = new Array();
var par = window.location.search;
var arr=par.split("=");
var nodecode=arr[arr.length-1];
$(function () {
    monitor();
    graphData();
 });

function monitor(){
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
function graphData() {
    $.ajax({
        url: "/sysservicegui/getsysname.do",
        dataType: "json",
        type: "post",
        async: false,
        data:{nodecode:nodecode},
        success: function (data) {
            resultData = data.result;
            console.log(resultData);
            for(var key in resultData){
                legendData.push({name:''+key+'',textStyle:{color:'#fff'}});
                seriesCategories.push({name: ''+key+''});
                seriesData.push({name: ''+key+'',symbolSize: 100,draggable: true,category: 1,itemStyle: {normal: {borderColor: '#04f2a7', borderWidth: 6,shadowBlur: 20,shadowColor: '#04f2a7',color: '#001c43',}}})
                for(var i = 0;i<resultData[key].length;i++){  //循环LIST
                    var veh = resultData[key][i];//获取LIST里面的对象
                    seriesLinks.push({source: ''+veh.nowRouteNode+'',target: ''+veh.nextRouteNode+'',value: '',lineStyle: {normal: {color: {type: 'linear',x: 0,y: 0,x2: 0,y2: 1,colorStops: [{offset: 0, color: '#e0f55a'}, {offset: 1, color: '#639564'}],globalCoord: false}}}})
                }
            }
        },
    });
}

var optionData={
    backgroundColor: '#1a4377',
    tooltip: {},
    animationDurationUpdate: 1500,
    animationEasingUpdate: 'quinticInOut',
    color:['#83e0ff','#45f5ce','#b158ff'],
    legend: {
        show: true,
        data: legendData
    },
    series: [
        {
            type: 'graph',
            layout: 'force',
            force: {
                repulsion: 1000,
                edgeLength: 150
            },
            symbolSize: 50,
            edgeSymbol: ['none', 'arrow'],
            roam: true,
            label: {
                normal: {
                    show: true
                }
            },
            edgeSymbolSize: [4, 10],
            edgeLabel: {
                normal: {
                    show: true,
                    textStyle: {
                        fontSize: 13
                    },
                    formatter: "{c}"
                }
            },
            lineStyle: {
                normal: {
                    opacity: 0.9,
                    width: 5,
                    curveness: 0
                }
            },
            data: seriesData,
            links:seriesLinks,
            categories:seriesCategories,
        }
    ]
};

require([
    'echarts'
], function (ec) {
    echarts = ec;
    option = optionData;
    console.log(JSON.stringify(optionData));
    testHelper.createChart(echarts, 'right', option);
});
