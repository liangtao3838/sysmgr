var echarts;
var resultData;
var legendData = new Array();
var seriesCategories = new Array();
var seriesData = new Array();
var seriesLinks = new Array();
var optionData;
$(function () {
    graphData();
    optionData=getOptionData();
    if(optionData!=null||typeof optionData === "undefined"){
        monitor();
    }
});

function monitor() {
    var monitortime = $("#monitortime").val();
    $.ajax({
        url: "/syscallrelagui/getsyscount.do",
        dataType: "json",
        type: "post",
        async: false,
        data: {monitortime: monitortime},
        success: function (data) {
            var nodes = data.result.count;
            var content = "";
            $.each(nodes, function (i, item) {
                var arr = item.split(",");
                content += '<a href="/sysservicegui/index.do?nodecode=' + arr[0] + '" style="font-size: 10px;height: 20px;padding-top: 3px;" class="btn btn-primary  btn-lg active">' + arr[0] + '系统</a></br>';
                content += '<span class="label label-success">成功</span><label style="width: 70px;" class="control-label">[' + arr[1] + ']</label>';
                content += '<span class="label label-danger">失败</span><label style="width: 70px;" class="control-label">[' + arr[2] + ']</label>';
                content += '</br>'
            })
            $("#sysrole").html(content);
        },
    });
}
function graphData() {
    $.ajax({
        url: "/syscallrelagui/getsysname.do",
        dataType: "json",
        type: "post",
        async: false,
        data: {},
        success: function (data) {
            resultData = data.result;
            console.log(resultData);
            for(var keys in resultData){
                var key=keys.split("-")[0];
                var status=keys.split("-")[1];
                legendData.push({name:''+key+'',textStyle:{color:'#fff'}});
                seriesCategories.push({name: ''+key+''});
                if(status==1){
                    seriesData.push({name: ''+key+'',symbolSize: 50,draggable: true,category: 1,itemStyle: {normal: {borderColor: '#04f2a7', borderWidth: 6,shadowBlur: 20,shadowColor: '#04f2a7',color: '#001c43',}}})
                }else{
                    seriesData.push({name: ''+key+'',symbolSize: 50,draggable: true,category: 1,itemStyle: {normal: {borderColor: '#04f2a7', borderWidth: 6,shadowBlur: 20,shadowColor: '#04f2a7',color: '#FF0000',}}})
                }
                console.log(resultData[keys].length);
                for(var i = 0;i<resultData[keys].length;i++){  //循环LIST
                    var veh = resultData[keys][i];//获取LIST里面的对象
                    seriesLinks.push({source: ''+veh.nowRouteNode+'',target: ''+veh.nextRouteNode+'',value: '',lineStyle: {normal: {color: {type: 'linear',x: 0,y: 0,x2: 0,y2: 1,colorStops: [{offset: 0, color: '#FF4500'}, {offset: 1, color: '#FF4500'}],globalCoord: false}}}})
                }
            }
        },
    });
}

var getOptionData=function () {
    optionData={
        backgroundColor: '#D1EEEE;',
        tooltip: {},
        animationDurationUpdate: 1000,
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
                    edgeLength: 50
                },
                symbolSize: 50,
                edgeSymbol: ['none', 'arrow'],
                roam: true,
                label: {
                    normal: {
                        show: true
                    }
                },
                edgeSymbolSize: [5,10],
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
    }
    return optionData;
};




require([
    'echarts'
], function (ec) {
    echarts = ec;
    option = optionData;
    console.log(JSON.stringify(optionData));
    testHelper.createChart(echarts, 'right', option);
});
